package com.catpp.guava.preconditions;

import com.google.common.base.Preconditions;

/**
 * com.catpp.guava.preconditions
 *
 * @Author cat_pp
 * @Date 2019/1/31
 * @Description 前置条件
 */
public class PreconditionsDemo {

    public static void main(String[] args) {

        Integer a = null, b = 2;

        // 检查条件是否为 true，用来检查传递给方法的参数。IllegalArgumentException
//        Preconditions.checkArgument(a == b, "输出信息：" + (a == b));

        // 检查 value 是否为 null，该方法直接返回 value，因此可以内嵌使用 checkNotNull。NullPointerException
//        Preconditions.checkNotNull(a, "输出信息：" + (a == null));

        // 用来检查对象的某些状态。IllegalStateException
//        Preconditions.checkState(a == b, "输出信息：" + (a == b));

        // 检查 index 作为索引值对某个列表、字符串或数组是否有效。index>=0 && index<size。 IndexOutOfBoundsException
        int[] arr = new int[]{1, 2, 3};
//        Preconditions.checkElementIndex(3, arr.length, "数组下标越界");

        // 检查 index 作为位置值对某个列表、字符串或数组是否有效。index>=0 && index<=size。IndexOutOfBoundsException
//        Preconditions.checkPositionIndex(4, arr.length, "数组元素所在位置越界");

        // 检查[start, end]表示的位置范围对某个列表、字符串或数组是否有效。IndexOutOfBoundsException
//        Preconditions.checkPositionIndexes(-1, 4, arr.length);
    }
}
