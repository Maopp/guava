package com.catpp.guava.optional;

import com.google.common.base.Optional;

import javax.jws.Oneway;
import java.util.Set;

/**
 * com.catpp.guava.optional
 *
 * @Author cat_pp
 * @Date 2019/1/31
 * @Description
 */
public class OptionalDemo {

    public static void main(String[] args) {

        // 创建引用缺失的 Optional 实例
        Optional<Integer> absent = Optional.absent();

        // 创建指定引用的 Optional 实例，若引用为 null 则快速失败
        Optional<Integer> optional1 = Optional.of(5);
        System.out.println(optional1);

        // 返回 Optional 所包含的引用，若引用缺失，则抛出 java.lang.IllegalStateException
        System.out.println(optional1.get());

        // 如果 Optional 包含非 null 的引用（引用存在），返回true
        System.out.println(optional1.isPresent());

        System.out.println();

        // 创建指定引用的 Optional 实例，若引用为 null 则表示缺失
        Optional<Integer> optional2 = Optional.fromNullable(null);
//        System.out.println(optional2.get());

        // 返回 Optional 所包含的引用，若引用缺失，返回指定的值
        System.out.println(optional2.or(1));

        // 返回 Optional 所包含的引用，若引用缺失，返回 null
        System.out.println(optional2.orNull());

        // 返回 Optional 所包含引用的单例不可变集，如果引用存在，返回一个只有单一元素的集合，如果引用缺失，返回一个空集合。
        Set<Integer> set = optional2.asSet();
        System.out.println(set);
//        set.add(2);
    }
}
