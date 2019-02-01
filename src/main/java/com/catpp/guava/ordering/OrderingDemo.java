package com.catpp.guava.ordering;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * com.catpp.guava.ordering
 *
 * @Author cat_pp
 * @Date 2019/2/1
 * @Description 排序
 */
public class OrderingDemo {

    public static void main(String[] args) {

        /*List<Person> list = new ArrayList<>();
        list.add(new Person("zhangsan", 1));
        list.add(new Person("lisi", 2));
        list.add(new Person("wangwu", 3));
        list.add(new Person("maliu", 4));
        list.add(new Person("zhaoqi", 5));
        list.add(new Person("chuba", 6));

        System.out.println(list);

        // 创建排序器

        // 对可排序类型做自然排序，如数字按大小，日期按先后排序
        Ordering ordering = Ordering.natural();
        Collections.sort(list, ordering);
        System.out.println(list);*/

        List<String> list = new ArrayList<>();
        list.add("zhangsan");
        list.add("lisi");
        list.add("wangwu");
        list.add("maliu");
        list.add("zhaoqi");
        list.add("chuba");

        System.out.println(list);

        // 创建排序器

        // 对可排序类型做自然排序，如数字按大小，日期按先后排序
        Ordering ordering = Ordering.natural();
        Collections.sort(list, ordering);
        System.out.println(list);
        // 获取语义相反的排序器
        Collections.sort(list, ordering.reverse());
        System.out.println(list);

//        list.add(null);
        // 使用当前排序器，但额外把 null 值排到最前面。
        Collections.sort(list, ordering.nullsFirst());
        System.out.println(list);
        // 使用当前排序器，但额外把 null 值排到最后面。
        Collections.sort(list, ordering.nullsLast());
        System.out.println(list);

        // 获取可迭代对象中最大的k个元素。
        List list1 = ordering.greatestOf(list, 3);
        System.out.println(list1);
        List list2 = ordering.leastOf(list, 3);
        System.out.println(list2);

        // 判断可迭代对象是否已按排序器排序：允许有排序值相等的元素。
        boolean ordered = ordering.isOrdered(list);
        System.out.println(ordered);
        boolean strictlyOrdered = ordering.isStrictlyOrdered(list);
        System.out.println(strictlyOrdered);

        // 判断可迭代对象是否已严格按排序器排序：不允许排序值相等的元素。
        List list3 = ordering.sortedCopy(list);
        System.out.println(list3);
        ImmutableList immutableList = ordering.immutableSortedCopy(list);
        System.out.println(immutableList);

        System.out.println(ordering.min(list));
        System.out.println(ordering.max(list));


        // 基于处理类型 T 的排序器，返回该类型的可迭代对象 Iterable<T>的排序器。
        Ordering lexicographical = ordering.lexicographical();

        // 对集合中元素调用 Function，再按返回值用当前排序器排序。
        ordering.onResultOf(sub(list));

        // 合成另一个比较器，以处理当前排序器中的相等情况。
        ordering.compound((o1, o2) -> 1);


    }

    private static Function sub(List<String> list) {
        return null;
    }
}
