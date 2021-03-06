# [【Springboot】实例讲解Springboot整合OpenTracing分布式链路追踪系统（Jaeger和Zipkin）](https://www.cnblogs.com/larrydpk/p/12638344.html)

# 1 分布式追踪系统

随着大量公司把单体应用重构为微服务，对于运维人员的责任就更加重大了。架构更复杂、应用更多，要从中快速诊断出问题、找到性能瓶颈，并不是一件容易的事。因此，也随着诞生了一系列面向`DevOps`的诊断与分析系统，主要是以下三个系统：

- 集中式日志系统（Logging）
- 集中式度量系统（Metrics）
- 分布式追踪系统（Tracing）

三者相互交织重叠如下：

![loggin_metrics_tracing](images/946674-20200405181335625-467469877.jpg)

技术栈上的成熟框架有，

Logging：Log4j、ELK等，

Metrics：Prometheus、InfluxDB、Grafana等

Tracing：Jaeger和Zipkin等。

分布式追踪系统在Google发表一篇文章[Dapper, a Large-Scale Distributed Systems Tracing Infrastructure](https://research.google.com/pubs/pub36356.html?spm=a2c4e.10696291.0.0.335419a4mTcu58)后快速发展。Tracing系统一般核心步骤有三个：代码埋点、数据存储、查询展示。

历史洪流滚滚向前，大浪淘沙，现在比较流行的有`Jaeger`和`Zipkin`。

# 2 OpenTracing

由于`Tracing`的技术发展迅速，为了解决兼容性问题，有了[OpenTracing](https://opentracing.io/)规范。它是一个轻量级的标准化层，连接应用、类库和追踪系统。

![interface](images/946674-20200405181336774-1678233158.jpg)

OpenTracing的优势：

（1）OpenTracing已经进入`CNCF`（云原生计算基金会，口号是坚持和整合开源技术来编排容器作为微服务架构的一部分），正在为全球的分布式追踪，提供统一的概念和数据标准。

（2）OpenTracing通过提供平台无关、厂商无关的`API`，使得开发人员能够方便添加和更换追踪系统的实现。

## 2.1 相关概念

`Trace`：贯穿一个分布式系统的事务追踪描述，其实就是由许多个`Span`组成的有向无环图。

`Span`：被命名的与记录时间的调用操作，如一个Http GET请求；`Span`有嵌套关系，如果一个请求会调用其它服务，就会生成子`Span`。

`Tag`：一组由键值对构成的标签集合，键值类型必须为字符串。它可以带上许多有用信息，如请求方法、请求URL、返回状态码等。

`Log`：一组`Span`的日志集合。

## 2.2 OpenTracing的实现

`Jaeger`是`Uber`推出的一款开源分布式追踪系统，兼容`OpenTracing API`。架构如下：

![Jaeger Architecture](images/946674-20200405181340611-1315311683.jpg)

`Zipkin`是由`Twitter`推出的开源的分布式追踪系统，架构图如下：

![Zipkin architecture](images/946674-20200405181347816-1718860451.jpg)

# 3 实战整合

本文以Springboot为Web项目，分别整合`Jaeger`和`Zipkin`。

## 3.1 Springboot项目准备

项目中的`Controller`，提供了两个Endpoint，`tracing`和`open`；在访问`open`时，代码会调用`tracing`。

```java
@RestController
public class TracingController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.port}")
    private int port;

    @RequestMapping("/tracing")
    public String tracing() throws InterruptedException {
        Thread.sleep(100);
        return "tracing";
    }

    @RequestMapping("/open")
    public String open() throws InterruptedException {
        ResponseEntity<String> response = 
          restTemplate.getForEntity("http://localhost:" + port + "/tracing", 
                                    String.class);
        Thread.sleep(200);
        return "open " + response.getBody();
    }
}
```

为了能够方便看出调用时长信息，特别在代码中增加了延时`Thread.sleep()`。

配置Web应用的端口和服务名字：

```properties
server.port=80
spring.application.name=opentracing-demo
```

## 3.2 整合Jaeger

### 3.2.1 Springboot整合

引用依赖：

```xml
<!-- 分布式链路追踪-Jaeger -->
<dependency>
    <groupId>io.opentracing.contrib</groupId>
    <artifactId>opentracing-spring-jaeger-web-starter</artifactId>
    <version>3.1.2</version>
</dependency>

<!-- 自定义Jaeger Span -->
<dependency>
    <groupId>io.jaegertracing</groupId>
    <artifactId>jaeger-core</artifactId>
    <version>1.2.0</version>
</dependency>
```

配置连接属性：

```properties
opentracing.jaeger.enabled=true
opentracing.jaeger.udp-sender.host=localhost
opentracing.jaeger.udp-sender.port=6831
```

### 3.2.2 Docker运行Jaeger

为了方便，使用`docker`来运行`Jaeger`：

```bash
# 拉取jaeger镜像
docker pull jaegertracing/all-in-one:1.18
# 运行jaeger实例，
docker run -d --name jaeger \
  -e COLLECTOR_ZIPKIN_HTTP_PORT=9411 \
  -p 5775:5775/udp \
  -p 6831:6831/udp \
  -p 6832:6832/udp \
  -p 5778:5778 \
  -p 16686:16686 \
  -p 14268:14268 \
  -p 14250:14250 \
  -p 9411:9411 \
  jaegertracing/all-in-one:1.18
```

以上端口的信息如下：

![Jaeger Ports](images/946674-20200405181352266-1171028939.jpg)

### 3.2.3 运行与访问UI

启动Web应用和Jaeger后，访问服务：

http://localhost/open

http://localhost/tracing

访问Jaeger UI界面http://localhost:16686/，设置好查询条件后点击`Find Traces`，就能查看到Trace信息了，具体如下：

![Jaeger UI](images/946674-20200405181352768-871323766.jpg)

选择一条Trace点进去后，可以看到详细信息，非常有利于我们分析，具体如下：

![Jaeger UI Details](images/946674-20200405181354809-606417130.jpg)

## 3.3 Zipkin

### 3.3.1 Springboot整合

引用相关依赖：

```xml
<!-- https://mvnrepository.com/artifact/io.opentracing.contrib/opentracing-spring-zipkin-starter -->
<dependency>
    <groupId>io.opentracing.contrib</groupId>
    <artifactId>opentracing-spring-zipkin-starter</artifactId>
    <version>0.2.0</version>
</dependency>
```

配置相关连接信息：

```properties
opentracing.zipkin.enabled=true
opentracing.zipkin.http-sender.base-url=http://localhost:9412/
```

### 3.3.2 Docker运行Zipkin

通过Docker运行Zipkin实例：

```bash
# 拉取Zipkin镜像
docker pull openzipkin/zipkin:2.21
# 运行Zipkin实例
docker run -d -p 9412:9411 openzipkin/zipkin:2.21
```

因为本机的端口9411已经被Jaeger的docker实例占用，所以改为9412。

### 3.3.3 运行与访问UI

启动Web应用和Zipkin后，访问服务：

http://localhost/open

http://localhost/tracing

访问Zipkin UI界面http://localhost:9412/zipkin/，设置好查询条件后点击查询，就能查看到Trace信息了，具体如下：

![Zipkin UI](images/946674-20200405181355645-1948587045.jpg)

选择一条Trace点击进去，同样可以看到许多详细信息，这里不在展示。

# 4 总结

本文通过代码案例详细讲解了Springboot整合OpenTracing的两个实现（`Jaeger`和`Zipkin`），demo的代码可关注公众号后台回复”**OpenTracing**“获取。

参考链接：

OpenTracing概念：https://opentracing.io/docs/overview/tags-logs-baggage/

Jaeger架构图：https://www.jaegertracing.io/docs/1.18/architecture/

Zipkip架构图：https://zipkin.io/pages/architecture.html

Jaeger Docker信息：https://www.jaegertracing.io/docs/1.18/getting-started/

Zipkin Docker信息：https://hub.docker.com/r/openzipkin/zipkin

Jaeger Spring整合项目：https://github.com/opentracing-contrib/java-spring-jaeger

Zipkin Spring整合项目：https://github.com/opentracing-contrib/java-spring-zipkin