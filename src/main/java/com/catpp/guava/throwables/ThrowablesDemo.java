package com.catpp.guava.throwables;

import com.google.common.base.Throwables;
import com.sun.org.apache.xerces.internal.dom.AbortException;

import java.util.List;

/**
 * com.catpp.guava.throwables
 *
 * @Author cat_pp
 * @Date 2019/2/2
 * @Description 简化异常和错误的操作
 */
public class ThrowablesDemo {

    public static void main(String[] args) {

        try {
            int a = 1 / 0;
        } catch (Throwable throwable) {
            // 如果 Throwable 是 Error 或 RuntimeException，直接抛出；否则把 Throwable 包装成 RuntimeException 抛出。返
            // 回类型是 RuntimeException，所以你可以像上面说的那样写成throw Throwables.propagate(t)，Java 编译器会意识到
            // 这行代码保证抛出异常。
//            Throwables.propagateIfPossible(throwable);

            // Throwable 类型为 X, Error 或 RuntimeException 才抛出
//            Throwables.propagateIfPossible(throwable, AbortException.class);

            // Throwable 类型为 X 才抛出
//            Throwables.propagateIfInstanceOf(throwable, AbortException.class);

            // Throwable 类型为 Error 或 RuntimeException 才抛出
//            Throwables.propagateIfPossible(throwable);

            // 传播抛出原样如果RuntimeException或Error是一个实例，否则作为最后的报告，把它包装在一个RuntimeException，然后传播。
//            throw Throwables.propagate(throwable);

            // 返回抛出的最里面的原因。
            Throwable rootCause = Throwables.getRootCause(throwable);
            System.out.println(rootCause.toString());

            // 获取一个Throwable的原因链的列表
            List<Throwable> causalChain = Throwables.getCausalChain(throwable);
            System.out.println(causalChain);

            // 返回包含toString()的结果字符串，随后完整抛出，递归的堆栈跟踪。
            String stackTraceAsString = Throwables.getStackTraceAsString(throwable);
            System.out.println(stackTraceAsString);
        }
    }
}
