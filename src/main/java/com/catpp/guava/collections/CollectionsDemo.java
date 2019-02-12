package com.catpp.guava.collections;

import com.google.common.collect.*;

import java.util.*;

/**
 * com.catpp.guava.collections
 *
 * @Author cat_pp
 * @Date 2019/2/11
 * @Description
 */
public class CollectionsDemo {

    public static void main(String[] args) {
        // 1、不可变集合
        Set<String> set = new HashSet<>();
        set.add("a");
        set.add("c");
        set.add("b");
        set.add("d");
        set.add("a");
        System.out.println("----------------不可变集合----------------");
        System.out.println(set);

        ImmutableSet<String> immutableSet = ImmutableSet.copyOf(set);
        System.out.println(immutableSet);
        // 不排序
        ImmutableSet<String> immutableSet1 = ImmutableSet.of("a", "d", "c", "b", "c");
        System.out.println(immutableSet1);
        ImmutableSet<Object> build = ImmutableSet.builder().addAll(set).add("e").build();
        System.out.println(build);
        ImmutableSet<Object> build1 = ImmutableSet.builderWithExpectedSize(2).addAll(set).build();
        System.out.println(build1);

        ImmutableSortedSet<String> immutableSortedSet = ImmutableSortedSet.copyOf(set);
        System.out.println(immutableSortedSet);
        ImmutableSortedSet<String> immutableSortedSet1 = ImmutableSortedSet.of("a", "d", "c", "b", "c");
        System.out.println(immutableSortedSet1);


        // 2、新集合类型
        Set<String> set1 = new HashSet<>();
        set1.add("a");
        set1.add("c");
        set1.add("b");
        set1.add("d");
        System.out.println(set + "---" + set1 + "---" + (set == set1) + "---" + set.equals(set1));

        // Multiset
        Multiset multiset = HashMultiset.create();
        multiset.add("a");
        multiset.add("a");
        multiset.add("c");
        multiset.add("d");
        multiset.add("b");
        System.out.println("----------------Multiset----------------");
        System.out.println(multiset);
        System.out.println(multiset.count("a"));
        System.out.println(multiset.size());
        System.out.println(multiset.elementSet().size());
        System.out.println(multiset.entrySet());
        System.out.println(multiset.elementSet());
        System.out.println(multiset.iterator());
        multiset.setCount("a", 3);
        System.out.println(multiset);

        SortedMultiset<Comparable> sortedMultiset = TreeMultiset.create();
        sortedMultiset.add(1);
        sortedMultiset.add(1);
        sortedMultiset.add(2);
        sortedMultiset.add(3);
        sortedMultiset.add(4);
        sortedMultiset.add(5);
        SortedMultiset<Comparable> sortedMultiset1 = sortedMultiset.subMultiset(1, BoundType.CLOSED, 3, BoundType.OPEN);
        System.out.println(sortedMultiset.size());
        System.out.println(sortedMultiset1.size());

        // Multimap
        Multimap<String, Integer> multimap = HashMultimap.create();
//        ListMultimap<Object, Object> listMultimap = ArrayListMultimap.create();
//        SetMultimap<Object, Object> hashMultimap = HashMultimap.create();
        multimap.put("1", 1);
        multimap.put("1", 11);
        multimap.put("1", 111);
        multimap.put("2", 2);
        multimap.put("2", 22);
        multimap.put("2", 222);
        multimap.put(null, null);
        multimap.putAll("3", ImmutableList.of(3, 33, 333));
        System.out.println("----------------Multimap----------------");
        System.out.println(multimap);
        Collection<Map.Entry<String, Integer>> entries = multimap.entries();
        System.out.println(entries);
        Set<Map.Entry<String, Collection<Integer>>> entries1 = multimap.asMap().entrySet();
        System.out.println(entries1);
        Set<String> keySet = multimap.keySet();
        System.out.println(keySet);
        Multiset<String> keys = multimap.keys();
        System.out.println(keys);
        Collection<Integer> values = multimap.values();
        System.out.println(values);
        multimap.replaceValues("1", ImmutableList.of(1));
        System.out.println(multimap);
        Map<String, Collection<Integer>> collectionMap = multimap.asMap();
        System.out.println(collectionMap);
        multimap.clear();
        System.out.println(multimap);

        System.out.println(multimap.get("zhangsan"));
        System.out.println(collectionMap.get("zhangsan"));

        // BiMap
        BiMap<String, Integer> biMap = HashBiMap.create();
        biMap.put("zhangsan", 1);
        biMap.put("lisi", 2);
        biMap.put("wangwu", 3);
        System.out.println("----------------BiMap----------------");
        System.out.println(biMap);
        BiMap<Integer, String> inverse = biMap.inverse();
        System.out.println(inverse);

        // Table
        Table<Integer, Integer, String> hashBasedTable = HashBasedTable.create();
        hashBasedTable.put(1, 1, "11");
        hashBasedTable.put(1, 2, "12");
        hashBasedTable.put(1, 3, "13");
        hashBasedTable.put(2, 1, "21");
        hashBasedTable.put(3, 1, "31");
        System.out.println("----------------Table----------------");
        System.out.println(hashBasedTable);
        System.out.println(hashBasedTable.cellSet());
        System.out.println(hashBasedTable.column(1));
        System.out.println(hashBasedTable.row(1));
        System.out.println(hashBasedTable.get(1, 2));
        System.out.println(hashBasedTable.size());

        ArrayTable<Integer, Integer, String> arrayTable = ArrayTable.create(hashBasedTable);
        System.out.println(arrayTable);
        System.out.println(arrayTable.at(0, 0));
        System.out.println(arrayTable.size());

        // ClassToInstanceMap
        ClassToInstanceMap<Number> classToInstanceMap = MutableClassToInstanceMap.create();
        classToInstanceMap.putInstance(Integer.TYPE, 1);
        classToInstanceMap.putInstance(Integer.TYPE, 2);
        classToInstanceMap.putInstance(Float.TYPE, 1.1f);
        classToInstanceMap.putInstance(Float.TYPE, 1.2f);
        System.out.println("----------------ClassToInstanceMap----------------");
        System.out.println(classToInstanceMap);
        System.out.println(classToInstanceMap.getInstance(Integer.TYPE));
        ClassToInstanceMap<Number> classToInstanceMap1 = ImmutableClassToInstanceMap.copyOf(classToInstanceMap);
        System.out.println(classToInstanceMap1);

        // RangeSet
        RangeSet rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1, 10));
        System.out.println("----------------RangeSet----------------");
        System.out.println(rangeSet);
        rangeSet.add(Range.open(11, 15));
        System.out.println(rangeSet);
        rangeSet.remove(Range.closed(2, 5));
        System.out.println(rangeSet);
        rangeSet.add(Range.range(20, BoundType.CLOSED, 30, BoundType.CLOSED));
        System.out.println(rangeSet);
        RangeSet complement = rangeSet.complement();
        System.out.println(complement);
        RangeSet subRangeSet = rangeSet.subRangeSet(Range.closed(1, 10));
        System.out.println(subRangeSet);
        Set asRanges = rangeSet.asRanges();
        System.out.println(asRanges);
        Iterator iterator = asRanges.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println(rangeSet.contains(5));
        System.out.println(rangeSet.contains(6));
        System.out.println(rangeSet.rangeContaining(5));
        System.out.println(rangeSet.rangeContaining(6));
        System.out.println(rangeSet.encloses(Range.closed(6, 8)));
        System.out.println(rangeSet.span());

        // RangeMap
        RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closed(1, 10), "zhangsan");
        rangeMap.put(Range.open(11, 15), "zhangsan");
        rangeMap.put(Range.closedOpen(15, 20), "lisi");
        System.out.println("----------------RangeMap----------------");
        System.out.println(rangeMap);
        rangeMap.remove(Range.closed(1, 2));
        System.out.println(rangeMap);

        Map<Range<Integer>, String> rangeStringMap = rangeMap.asMapOfRanges();
        Set<Map.Entry<Range<Integer>, String>> entries2 = rangeStringMap.entrySet();
        for (Map.Entry<Range<Integer>, String> entry : rangeStringMap.entrySet()) {
            System.out.println(entry);
        }
        System.out.println(rangeMap.subRangeMap(Range.closed(1, 10)));

    }
}