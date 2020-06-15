package cn.myst.web.jaeger.common.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * 优雅的使用BeanUtils对List集合的操作工具类
 * 参考文章：https://cloud.tencent.com/developer/article/1579645
 *
 * @author ziming.xing
 * Create Time：2020/6/15
 */
public class BeansUtils extends BeanUtils {
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
        return copyListProperties(sources, target, null);
    }

    /**
     * @author ziming.xing
     * 使用场景：Entity、Bo、Vo层数据的复制，因为BeanUtils.copyProperties只能给目标对象的属性赋值，却不能在List集合下循环赋值，因此添加该方法
     * 如：List<AdminEntity> 赋值到 List<AdminVo> ，List<AdminVo>中的 AdminVo 属性都会被赋予到值
     * S: 数据源类 ，T: 目标类::new(eg: AdminVo::new)
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target, BeansUtilsCallBack<S, T> callBack) {
        List<T> list = new ArrayList<>(sources.size());
        for (S source : sources) {
            T t = target.get();
            copyProperties(source, t);
            list.add(t);
            if (callBack != null) {
                // 回调
                callBack.callBack(source, t);
            }
        }
        return list;
    }

    /**
     * 使用java8的lambda表达式注解：BeansUtilsCallBack接口
     *
     * @author ziming.xing
     * Create Time：2020/6/15
     */
    @FunctionalInterface
    public static interface BeansUtilsCallBack<S, T> {
        void callBack(S t, T s);
    }

}
