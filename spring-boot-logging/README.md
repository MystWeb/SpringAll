# Spring Boot - 日志

## 1、日志框架

 小张；开发一个大型系统；

​		1、System.out.println("")；将关键数据打印在控制台；去掉？写在一个文件？

​		2、框架来记录系统的一些运行时信息；日志框架 ；  zhanglogging.jar；

​		3、高大上的几个功能？异步模式？自动归档？xxxx？  zhanglogging-good.jar？

​			异步模式：异步记录与收集日志。

​			自动归档：每天的日志记录压缩成一个文件，进行分类。

​		4、将以前框架卸下来？换上新的框架，重新修改之前相关的API；zhanglogging-prefect.jar；

​		5、JDBC---数据库驱动；

​			写了一个统一的接口层；日志门面（日志的一个抽象层）；logging-abstract.jar；

​			给项目中导入具体的日志实现就行了；我们之前的日志框架都是实现的抽象层；



**市面上的日志框架；**

JUL、JCL、Jboss-logging、logback、log4j、log4j2、slf4j....

| 日志门面  （日志的抽象层）                                   | 日志实现                                                 |
| ------------------------------------------------------------ | -------------------------------------------------------- |
| ~~JCL（Jakarta  Commons Logging）~~    `SLF4j（Simple  Logging` Facade for Java）    **~~jboss-logging~~** | `Log4j`  JUL（java.util.logging）  Log4j2  **`Logback`** |

左边选一个门面（抽象层）、右边来选一个实现；

日志门面：  SLF4J；

日志实现：Logback；



SpringBoot：底层是Spring框架，Spring框架默认是用JCL；

SpringBoot选用 SLF4j和logback；



## 2、SLF4j使用

### 1、如何在系统中使用SLF4j   https://www.slf4j.org

以后开发的时候，日志记录方法的调用，不应该来直接调用日志的实现类，而是调用日志抽象层里面的方法；

给系统里面导入slf4j的jar和  logback的实现jar

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {
  public static void main(String[] args) {
    Logger logger = LoggerFactory.getLogger(HelloWorld.class);
    logger.info("Hello World");
  }
}
```

图示

![](images/1.png)

每一个日志的实现框架都有自己的配置文件。使用slf4j以后，**配置文件还是做成日志实现框架自己本身的配置文件；**

### 2、遗留问题

（slf4j+logback）: Spring（commons-logging）、Hibernate（jboss-logging）、MyBatis、xxxx

统一日志记录，即使是别的框架和我一起统一使用slf4j进行输出？

![](images/2.png)

**如何让系统中所有的日志都统一到slf4j**

1、将系统中其他日志框架先排除出去；

2、用中间包来替换原有的日志框架；

3、我们导入slf4j其他的实现

## 3、SpringBoot日志关系

```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
		</dependency>
```

SpringBoot使用它来做日志功能；

```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-logging</artifactId>
		</dependency>
```

底层依赖关系

![](images/3.png)

总结：

​	1）、SpringBoot底层也是使用slf4j+logback的方式进行日志记录

​	2）、SpringBoot也把其他的日志都替换成了slf4j；

​	3）、中间替换包

```java
@SuppressWarnings("rawtypes")
public abstract class LogFactory {

    static String UNSUPPORTED_OPERATION_IN_JCL_OVER_SLF4J = "http://www.slf4j.org/codes.html#unsupported_operation_in_jcl_over_slf4j";

    static LogFactory logFactory = new SLF4JLogFactory();
```

![](images/4.png)

​	4）、如果我们要引入其他框架？一定要把这个框架的默认日志依赖移除掉？

​			Spring框架用的是commons-logging；

```xml
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-core</artifactId>
			<exclusions>
				<exclusion>
					<groupId>commons-logging</groupId>
					<artifactId>commons-logging</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
```

**SpringBoot能自动适配所有的日志，而且底层使用slf4j+logback的方式记录日志，引入其他框架的时候，只需要把这个框架依赖的日志框架排除掉即可**

## 4、日志使用；

### 1、默认配置

- Springboot使用Commons Logging作为内部日志接口，默认使用logback，但是实现方式是开放的
- 依据项目可以配置使用Java Util Logging, Log4J, Log4J2 和 Logback作为日志输出实例。

##### 日志输出级别

| 级别  | 描述                                             |
| :---- | :----------------------------------------------- |
| ALL   | 所有级别，包括定制级别。                         |
| DEBUG | 指明细致的事件信息，对调试应用最有用。           |
| ERROR | 指明错误事件，但应用可能还能继续运行。           |
| FATAL | 指明非常严重的错误事件，可能会导致应用终止执行。 |
| INFO  | 指明描述信息，从粗粒度上描述了应用运行过程。     |
| OFF   | 最高级别，用于关闭日志。                         |
| TRACE | 比 DEBUG 级别的粒度更细。                        |
| WARN  | 指明潜在的有害状况。                             |

#### 级别是如何工作的？

在一个级别为 `q` 的 logger 对象中，一个级别为 `p` 的日志请求在 p >= q 的情况下是开启的。该规则是 Log4j 的核心，它假设级别是有序的。对于标准级别，其顺序为：ALL < DEBUG < INFO < WARN < ERROR < FATAL < OFF。

下面的例子展示了如何过滤 DEBUG 和 INFO 级别的日志。改程序使用 logger 对象的 `setLevel(Level.X)` 方法设置期望的日志级别：

该例子会打印出除过 DEBUG 和 INFO 级别外的所有信息：

```java
	//记录器
	private static org.apache.Log4j.Logger logger = Logger.getLogger(getClass());
	@Test
	public void contextLoads() {
		//System.out.println();

		//日志的级别；
		//由低到高   trace<debug<info<warn<error
		//可以调整输出的日志级别；日志就只会在这个级别及以后的高级别生效
		logger.trace("这是trace日志...");
		logger.debug("这是debug日志...");
		//logging.level.com.study=trace
		//SpringBoot默认给我们使用的是info级别的，没有指定级别的就用SpringBoot默认规定的级别；root级别
		logger.info("这是info日志...");
		logger.warn("这是warn日志...");
		logger.error("这是error日志...");
        
        logger.trace("Trace Message!");
      	logger.debug("Debug Message!");
      	logger.info("Info Message!");
      	logger.warn("Warn Message!");
      	logger.error("Error Message!");
      	logger.fatal("Fatal Message!");
	}
```

```
    日志输出格式：
		%d表示日期时间，
		%thread表示线程名，
		%-5level：级别从左显示5个字符宽度
		%logger{50} 表示logger名字最长50个字符，否则按照句点分割。 
		%msg：日志消息，
		%n是换行符
    -->
    %d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n
```

SpringBoot修改日志的默认配置

```properties
# 日志输出级别
#logging.level.com.study=trace

# 不指定路径在当前项目下生成springboot.log日志
# 可以指定完整的路径
#logging.file=D:/springboot.log

# file与path只可以配置其一

# 在当前磁盘的根路径下创建spring文件夹和里面的log文件夹；使用 spring.log 作为默认文件
#logging.path=/spring/log

# 在控制台输出的日志的格式
#logging.pattern.console=%d{yyyy-MM-dd} [%thread] %-5level %logger{50} - %msg%n

# 指定文件中日志输出的格式
#logging.pattern.file=%d{yyyy-MM-dd} === [%thread] === %-5level === %logger{50} ==== %msg%n

# Spring boot使用log4j2
logging.config=classpath:log4j2.xml
```

| logging.file | logging.path | Example  | Description                        |
| ------------ | ------------ | -------- | ---------------------------------- |
| (none)       | (none)       |          | 只在控制台输出                     |
| 指定文件名   | (none)       | my.log   | 输出日志到my.log文件               |
| (none)       | 指定目录     | /var/log | 输出到指定目录的 spring.log 文件中 |

### 2、指定配置

给类路径下放上每个日志框架自己的配置文件即可；SpringBoot就不使用他默认配置的了

| Logging System          | Customization                                                |
| ----------------------- | ------------------------------------------------------------ |
| Logback                 | `logback-spring.xml`, `logback-spring.groovy`, `logback.xml` or `logback.groovy` |
| Log4j2                  | `log4j2-spring.xml` or `log4j2.xml`                          |
| JDK (Java Util Logging) | `logging.properties`                                         |

logback.xml：直接就被日志框架识别了；

**logback-spring.xml**：日志框架就不直接加载日志的配置项，由SpringBoot解析日志配置，可以使用SpringBoot的高级Profile功能

```xml
<springProfile name="staging">
    <!-- configuration to be enabled when the "staging" profile is active -->
  	可以指定某段配置只在某个环境下生效
</springProfile>

```

##### 配置Springboot使用logback作为日志输出器

| name                                              | 功能                                       | 备注               |
| :------------------------------------------------ | :----------------------------------------- | :----------------- |
| `log.level`                                       | 日志的根级别                               | 全局               |
| `appender`                                        | 可以在控制台、文件或者数据库显示和存放日志 |                    |
| `<logger name="org.apache.kafka" level="ERROR"/>` | 某个包下的日志级别                         | 针对某个包下起作用 |

```xml
<appender name="stdout" class="ch.qos.logback.core.ConsoleAppender">
        <!--
        日志输出格式：
			%d表示日期时间，
			%thread表示线程名，
			%-5level：级别从左显示5个字符宽度
			%contextName：日志上下文名称
			%logger{50} 表示logger名字最长50个字符，否则按照句点分割。 
			%msg：日志消息，
			%n是换行符
        -->
        <layout class="ch.qos.logback.classic.PatternLayout">
            <springProfile name="dev">
                <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} ----> [%thread] ---> %-5level %logger{50} - %msg%n</pattern>
            </springProfile>
            <springProfile name="!dev">
                <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} ==== [%thread] ==== %-5level %logger{50} - %msg%n</pattern>
            </springProfile>
        </layout>
    </appender>
```

推荐使用的log back日志配置

```xml 
<?xml version="1.0" encoding="UTF-8"?>
<configuration scan="true" scanPeriod="30 seconds">
    <contextName>LogBack-Spring</contextName>

    <springProperty scope="context" name="log.level" source="logging.level.root" defaultValue="DEBUG"/>
    <springProperty scope="context" name="log.path" source="logging.path" defaultValue="/var/log/mybatis"/>
    <springProperty scope="context" name="log.file" source="logging.file" defaultValue="mybatis"/>

    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder charset="UTF-8">
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} %-5level %contextName [%thread] %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${log.path}/${log.file}.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${log.path}/${log.file}.%d{yyyy-MM-dd}.log.zip</fileNamePattern>
            <maxHistory>180</maxHistory>
        </rollingPolicy>
        <encoder charset="UTF-8">
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} %-5level [%thread] %logger{36} [%file : %line] %msg%n</pattern>
        </encoder>
    </appender>

    <logger name="org.apache.kafka" level="ERROR"/>
    <logger name="com.proaim" level="DEBUG"/>
    <logger name="org.mybatis" level="DEBUG"/>
    <logger name="org.springframework" level="INFO"/>
    <logger name="org.apache.http" level="INFO"/>
    <logger name="org.apache.coyote" level="INFO"/>
    <logger name="org.apache.catalina" level="INFO"/>
    <logger name="org.apache.tomcat" level="INFO"/>
    <logger name="com.netflix" level="ERROR"/>

    <root level="${log.level}">
        <appender-ref ref="STDOUT"/>
        <appender-ref ref="FILE"/>
    </root>

</configuration>
```

如果使用logback.xml作为日志配置文件，还要使用profile功能，会有以下错误

 `no applicable action for [springProfile]`

## 5、切换日志框架

可以按照slf4j的日志适配图，进行相关的切换；

slf4j+log4j的方式；

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
  <exclusions>
    <exclusion>
      <artifactId>logback-classic</artifactId>
      <groupId>ch.qos.logback</groupId>
    </exclusion>
    <exclusion>
      <artifactId>log4j-over-slf4j</artifactId>
      <groupId>org.slf4j</groupId>
    </exclusion>
  </exclusions>
</dependency>

<dependency>
  <groupId>org.slf4j</groupId>
  <artifactId>slf4j-log4j12</artifactId>
</dependency>

```



#### 切换为log4j2

```xml
	   <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <exclusions>
                <exclusion>
                    <artifactId>spring-boot-starter-logging</artifactId>
                    <groupId>org.springframework.boot</groupId>
                </exclusion>
            </exclusions>
        </dependency>

		<dependency>
  			<groupId>org.springframework.boot</groupId>
  			<artifactId>spring-boot-starter-log4j2</artifactId>
		</dependency>
```

##### log4j2.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--日志级别以及优先级排序: OFF > FATAL > ERROR > WARN > INFO > DEBUG > TRACE > ALL -->
<!-- status log4j2内部日志级别 -->
<Configuration status="WARN">
    <appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <!-- 设置日志输出的格式 -->
            <PatternLayout pattern="[%d{HH:mm:ss:SSS}] [%p] - %l - %m%n"/>
        </Console>
        <RollingFile name="RollingFileInfo" fileName="D:/info.log"
                     filePattern="D:/$${date:yyyy-MM}/info-%d{yyyy-MM-dd}-%i.log">
            <Filters>
                <ThresholdFilter level="INFO"/>
            </Filters>
            <PatternLayout pattern="[%d{HH:mm:ss:SSS}] [%p] - %l - %m%n"/>
            <Policies>
                <TimeBasedTriggeringPolicy/>
                <SizeBasedTriggeringPolicy size="100 MB"/>
            </Policies>
        </RollingFile>
    </appenders>

    <loggers>
        <root level="info">
            <appender-ref ref="Console"/>
            <appender-ref ref="RollingFileInfo"/>
        </root>
    </loggers>

</Configuration>
```

