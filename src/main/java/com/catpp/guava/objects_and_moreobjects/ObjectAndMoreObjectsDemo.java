package com.catpp.guava.objects_and_moreobjects;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * com.catpp.guava.objects_and_moreobjects
 *
 * @Author cat_pp
 * @Date 2019/2/1
 * @Description 常见Object方法
 */
public class ObjectAndMoreObjectsDemo {

    public static void main(String[] args) {

        String str1 = "hello";
        String str2 = "hello";

        // 确定两个可能是空的对象是否相等
        boolean notNull = Objects.equal(str1, str2);
        boolean isNull = Objects.equal(str1, null);
        System.out.println(notNull);
        System.out.println(isNull);

        // 生成多个值的哈希码。
        int hashCode = Objects.hashCode("zhangsan", "lisi");
        System.out.println(hashCode);

        // 获取不是null的值 NullPointerException
        String firstNonNull1 = MoreObjects.firstNonNull(str1, "world");
        String firstNonNull2 = MoreObjects.firstNonNull(null, "world");
//        String firstNonNull3 = MoreObjects.firstNonNull(null, null);
        System.out.println(firstNonNull1);
        System.out.println(firstNonNull2);

        // 转换字符串
        MoreObjects.ToStringHelper toStringHelper_class = MoreObjects.toStringHelper(Person.class);
        System.out.println(toStringHelper_class);
        MoreObjects.ToStringHelper toStringHelper_object = MoreObjects.toStringHelper(new Person());
        System.out.println(toStringHelper_object);
        MoreObjects.ToStringHelper toStringHelper_string = MoreObjects.toStringHelper("Hello World");
        System.out.println(toStringHelper_string);

        MoreObjects.ToStringHelper zhangsan = toStringHelper_class.add("name", "zhangsan")
                .add("sex", 1).add("desc", null);
        System.out.println(zhangsan);

        // 省略空值
        MoreObjects.ToStringHelper toStringHelper = toStringHelper_class.omitNullValues();
        System.out.println(toStringHelper);
    }
}
