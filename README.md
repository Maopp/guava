# Guava

## Guava 是什么?
> Guava是一种基于开源的Java库，其中包含谷歌正在由他们很多项目使用的很多核心库。这个库是为了方便编码，并减少编码错误。这
个库提供用于集合，缓存，支持原语，并发性，常见注解，字符串处理，I/O和验证的实用方法。

## Optional类
> Optional 用于包含非空对象的不可变对象。Optional对象，用于不存在值时表示 null 。这个类有各种实用的方法，以方便代码来处
理为可用或者不可用，而不是检查 null 值。
Guava 用 Optional表示可能为 null 的 T 类型引用。一个 Optional 实例可能包含非 null 的引用（我们称之为引用存在），也可能什
么也不包括（称之为引用缺失）。它从不说包含的是 null 值，而是用存在或缺失来表示。但 Optional 从不会包含 null 值引用。

- **使用 Optional 的意义在哪儿？**
> 使用 Optional 除了赋予 null 语义，增加了可读性，最大的优点在于它是一种傻瓜式的防护。Optional 迫使你积极思考引用缺失的
情况，因为你必须显式地从 Optional 获取引用。直接使用 null 很容易让人忘掉某些情形，尽管 FindBugs 可以帮助查找 null 相关的
问题，但是我们还是认为它并不能准确地定位问题根源。
如同输入参数，方法的返回值也可能是 null。和其他人一样，你绝对很可能会忘记别人写的方法 method(a,b)会返回一个 null，就好像
当你实现 method(a,b)时，也很可能忘记输入参数 a 可以为 null。将方法的返回类型指定为 Optional，也可以迫使调用者思考返回的
引用缺失的情形。
- **其他处理 null 的便利方法**
> 当你需要用一个默认值来替换可能的 null，请使用 Objects.firstNonNull(T, T) 方法。如果两个值都是 null，该方法会抛出 
NullPointerException。Optional 也是一个比较好的替代方案，例如：Optional.of(first).or(second).
还有其它一些方法专门处理 null 或空字符串：emptyToNull(String)，nullToEmpty(String)，isNullOrEmpty(String)。

## Preconditions 类
> 让方法调用的前置条件判断更简单。

- **索引值：**
> checkElementIndex(int index, int size)
索引值常用来查找列表、字符串或数组中的元素，如 List.get(int), String.charAt(int)
- **范围值：**
> checkPositionIndexes(int start, int end, int size)、checkPositionIndex(int index, int size)
位置值和位置范围常用来截取列表、字符串或数组，如 List.subList(int，int), String.substring(int)

## Objects 类
> Objects类提供适用于所有对象，如equals, hashCode等辅助函数
equal 和 hashCode 方法使用 Objects
其他方法使用 MoreObjects

## Ordering 类
> 排序器[Ordering]是 Guava 流畅风格比较器[Comparator]的实现，它可以用来为构建复杂的比较器，以完成集合排序的功能。
从实现上说，Ordering 实例就是一个特殊的 Comparator 实例。Ordering 把很多基于 Comparator 的静态方法（如 Collections.max）
包装为自己的实例方法（非静态方法），并且提供了链式调用方法，来定制和增强现有的比较器。

## Throwables 类
> 简化异常和错误的传播与检查
有时候，你会想把捕获到的异常再次抛出。这种情况通常发生在 Error 或 RuntimeException 被捕获的时候，你没想捕获它们，但是声
明捕获 Throwable 和 Exception 的时候，也包括了了 Error 或 RuntimeException。Guava 提供了若干方法，来判断异常类型并且重新
传播异常。

```
    try {
        someMethodThatCouldThrowAnything();
    } catch (IKnowWhatToDoWithThisException e) {
        handle(e);
    } catch (Throwable t) {
        Throwables.propagateIfInstanceOf(t, IOException.class);
        Throwables.propagateIfInstanceOf(t, SQLException.class);
        throw Throwables.propagate(t);
    }
```
这些方法都会自己决定是否要抛出异常，但也能直接抛出方法返回的结果——例如，throw Throwables.propagate(t);—— 这样可以向
编译器声明这里一定会抛出异常。

## 集合

### 1、不可变集合
- 为什么要使用不可变集合？
    - 当对象被不可信的库调用时，不可变形式是安全的；
    - 不可变对象被多个线程调用时，不存在竞态条件问题；
    - 不可变集合不需要考虑变化，因此可以节省时间和空间。所有不可变的集合都比它们的可变形式有更好的内存利用率（分析和测试细节）；
    - 不可变对象因为固定不变，可以作为常量来安全使用。
> 创建对象的不可变拷贝是一项很好的防御性编程技巧。Guava 为所有 JDK 标准集合类型和 Guava 新集合类型都提供了简单易用的不可变版本。

- JDK 提供的 Collections.unmodifiableXXX 方法与 Guava 集合方法相比：
    - 笨重而且累赘：不能舒适地用在所有想做防御性拷贝的场景；
    - 不安全：要保证没人通过原集合的引用进行修改，返回的集合才是事实上不可变的；
    - 低效：包装过的集合仍然保有可变集合的开销，比如并发修改的检查、散列表的额外空间，等等。
> 如果你没有修改某个集合的需求，或者希望某个集合保持不变时，把它防御性地拷贝到不可变集合是个很好的实践。

*重要提示：所有 Guava 不可变集合的实现都不接受 null 值。我们对 Google 内部的代码库做过详细研究，发现只有 5%的情况需要在
集合中允许 null 元素，剩下的 95%场景都是遇到 null 值就快速失败。如果你需要在不可变集合中使用 null，请使用 JDK 中的 
Collections.unmodifiableXXX 方法。*

- 怎么使用不可变集合?
    - copyOf 方法，如 ImmutableSet.copyOf(set);
    - of 方法，如 ImmutableSet.of(“a”, “b”, “c”)或 ImmutableMap.of(“a”, 1, “b”, 2);
    - Builder 工具，如
        ```
        public static final ImmutableSet<Color> GOOGLE_COLORS =
            ImmutableSet.<Color>builder()
                .addAll(WEBSAFE_COLORS)
                .add(new Color(0, 191, 255))
                .build();
        ```
        
- 作为一种探索，ImmutableXXX.copyOf(ImmutableCollection)会试图对如下情况避免线性时间拷贝：
    - 在常量时间内使用底层数据结构是可能的——例如，ImmutableSet.copyOf(ImmutableList)就不能在常量时间内完成。
    - 不会造成内存泄露——例如，你有个很大的不可变集合 ImmutableList hugeList， ImmutableList.copyOf(hugeList.subList(0, 10))
    就会显式地拷贝，以免不必要地持有 hugeList 的引用。
    - 不改变语义——所以 ImmutableSet.copyOf(myImmutableSortedSet)会显式地拷贝，因为和基于比较器的 ImmutableSortedSet 
    相比，ImmutableSet对hashCode()和 equals 有不同语义。
> 在可能的情况下避免线性拷贝，可以最大限度地减少防御性编程风格所带来的性能开销。

- asList视图：
> 所有不可变集合都有一个 asList()方法提供 ImmutableList 视图，来帮助你用列表形式方便地读取集合元素。例如，你可以使用 
sortedSet.asList().get(k)从 ImmutableSortedSet 中读取第 k 个最小元素。
asList()返回的 ImmutableList 通常是——并不总是——开销稳定的视图实现，而不是简单地把元素拷贝进 List。也就是说，asList 
返回的列表视图通常比一般的列表平均性能更好，比如，在底层集合支持的情况下，它总是使用高效的 contains 方法。

可变集合接口	        属于JDK还是Guava	不可变版本
Collection	            JDK	                ImmutableCollection
List	                JDK	                ImmutableList
Set	                    JDK	                ImmutableSet
SortedSet/NavigableSet	JDK	                ImmutableSortedSet
Map	                    JDK	                ImmutableMap
SortedMap	            JDK	                ImmutableSortedMap
Multiset	            Guava	            ImmutableMultiset
SortedMultiset	        Guava	            ImmutableSortedMultiset
Multimap	            Guava	            ImmutableMultimap
ListMultimap	        Guava	            ImmutableListMultimap
SetMultimap	            Guava	            ImmutableSetMultimap
BiMap	                Guava	            ImmutableBiMap
ClassToInstanceMap	    Guava	            ImmutableClassToInstanceMap
Table	                Guava	            ImmutableTable

### 2、新集合类型4
> Guava 引入了很多 JDK 没有的、但我们发现明显有用的新集合类型。这些新类型是为了和 JDK 集合框架共存，而没有往 JDK 集合抽
象中硬塞其他概念。作为一般规则，Guava 集合非常精准地遵循了 JDK 接口契约。

- Multiset
    **Multiset 元素的顺序是无关紧要的：Multiset {a, a, b}和{a, b, a}是相等的”。**
    - 可以用两种方式看待 Multiset：
        - 没有元素顺序限制的 ArrayList
        - Map<E, Integer>，键为元素，值为计数
    - 当把 Multiset 看成普通的 Collection 时，它表现得就像无序的 ArrayList：
        - add(E)添加单个给定元素
        - iterator()返回一个迭代器，包含 Multiset 的所有元素（包括重复的元素）
        - size()返回所有元素的总个数（包括重复的元素）
    - 当把 Multiset 看作 Map<E, Integer>时，它也提供了符合性能期望的查询操作：
        - count(Object)返回给定元素的计数。HashMultiset.count 的复杂度为 O(1)，TreeMultiset.count 的复杂度为 O(log n)。
        - entrySet()返回 Set<Multiset.Entry>，和 Map 的 entrySet 类似。
        - elementSet()返回所有不重复元素的 Set，和 Map 的 keySet()类似。
        - 所有 Multiset 实现的内存消耗随着不重复元素的个数线性增长。
    - Multiset 不是 Map，它们之间的显著区别：
        - Multiset 中的元素计数只能是正数。任何元素的计数都不能为负，也不能是 0。elementSet()和 entrySet()视图中也不会
        有这样的元素。
        - multiset.size()返回集合的大小，等同于所有元素计数的总和。对于不重复元素的个数，应使用 elementSet().size()方法。
        （因此，add(E)把 multiset.size()增加 1）
        - multiset.iterator()会迭代重复元素，因此迭代长度等于 multiset.size()。
        - Multiset 支持直接增加、减少或设置元素的计数。setCount(elem, 0)等同于移除所有 elem。
        - 对 multiset 中没有的元素，multiset.count(elem)始终返回 0。
    - Multiset 的各种实现
        Map	                对应的Multiset	        是否支持null元素
        HashMap	            HashMultiset	        是
        TreeMap	            TreeMultiset	        是（如果 comparator 支持的话）
        LinkedHashMap	    LinkedHashMultiset	    是
        ConcurrentHashMap	ConcurrentHashMultiset	否
        ImmutableMap	    ImmutableMultiset	    否
- SortedMultiset
> SortedMultiset 是 Multiset 接口的变种，它支持高效地获取指定范围的子集。比方说，你可以用 
latencies.subMultiset(0,BoundType.CLOSED, 100, BoundType.OPEN).size()来统计你的站点中延迟在 100 毫秒以内的访问，然后
把这个值和 latencies.size()相比，以获取这个延迟水平在总体访问中的比例。
```
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
```

- Multimap
*每个有经验的 Java 程序员都在某处实现过 Map<K, List>或 Map<K, Set>，并且要忍受这个结构的笨拙。例如，Map<K, Set>通常用来
表示非标定有向图。Guava 的 Multimap 可以很容易地把一个键映射到多个值。换句话说，Multimap 是把键映射到任意多个值的一般方式。*
    - 可以用两种方式思考 Multimap 的概念：”键-单个值映射”的集合：
        - a -> 1 a -> 2 a ->4 b -> 3 c -> 5
    - 或者”键-值集合映射”的映射：
        - a -> [1, 2, 4] b -> 3 c -> 5
    - 一般来说，Multimap 接口应该用第一种方式看待，但 asMap()视图返回 Map<K, Collection>，让你可以按另一种方式看待 Multimap。
    重要的是，不会有任何键映射到空集合：一个键要么至少到一个值，要么根本就不在 Multimap 中。
    - 很少会直接使用 Multimap 接口，更多时候你会用 ListMultimap 或 SetMultimap 接口，它们分别把键映射到 List 或 Set。
    
    - 修改 Multimap
        *Multimap.get(key)以集合形式返回键所对应的值视图，即使没有任何对应的值，也会返回空集合。ListMultimap.get(key)
        返回 List，SetMultimap.get(key)返回 Set。*
        **对值视图集合进行的修改最终都会反映到底层的 Multimap。**
    - Multimap 不是 Map，它们之间的显著区别包括：
        - Multimap.get(key)总是返回非 null、但是可能空的集合。这并不意味着 Multimap 为相应的键花费内存创建了集合，而只
        是提供一个集合视图方便你为键增加映射值
        - 如果你更喜欢像 Map 那样，为 Multimap 中没有的键返回 null，请使用 asMap()视图获取一个 Map<K, Collection>。
        （或者用静态方法 Multimaps.asMap()为 ListMultimap 返回一个 Map<K, List>。对于 SetMultimap 和 SortedSetMultimap，
        也有类似的静态方法存在）
        - 当且仅当有值映射到键时，Multimap.containsKey(key)才会返回 true。尤其需要注意的是，如果键 k 之前映射过一个或多
        个值，但它们都被移除后，Multimap.containsKey(key)会返回 false。
        - Multimap.entries()返回 Multimap 中所有”键-单个值映射”——包括重复键。如果你想要得到所有”键-值集合映射”，
        请使用 asMap().entrySet()。
        - Multimap.size()返回所有”键-单个值映射”的个数，而非不同键的个数。要得到不同键的个数，请改用 Multimap.keySet().size()。
        
- Table
    - 集种实现方式：
        - HashBasedTable：本质上用 HashMap<R, HashMap<C, V>>实现；
        - TreeBasedTable：本质上用 TreeMap<R, TreeMap<C,V>>实现；
        - ImmutableTable：本质上用 ImmutableMap<R, ImmutableMap<C, V>>实现；注：ImmutableTable 对稀疏或密集的数据集都有优化。
        - ArrayTable：要求在构造时就指定行和列的大小，本质上由一个二维数组实现，以提升访问速度和密集 Table 的内存利用率。
        
- ClassToInstanceMap
*ClassToInstanceMap 是一种特殊的 Map：它的键是类型，而值是符合键所指类型的对象。
 为了扩展 Map 接口，ClassToInstanceMap 额外声明了两个方法：T getInstance(Class) 和 T putInstance(Class, T)，从而避免强制
 类型转换，同时保证了类型安全。*
    - ClassToInstanceMap 有唯一的泛型参数，通常称为 B，代表 Map 支持的所有类型的上界。例如：
    ```$xslt
        ClassToInstanceMap<Number> numberDefaults=MutableClassToInstanceMap.create();
        numberDefaults.putInstance(Integer.class, Integer.valueOf(0));

    ```
    - 实现
        - MutableClassToInstanceMap 和 ImmutableClassToInstanceMap。
        
- RangeSet
*RangeSet描述了一组不相连的、非空的区间。当把一个区间添加到可变的RangeSet时，所有相连的区间会被合并，空区间会被忽略。*
    - RangeSet 的视图
        - complement()：返回 RangeSet 的补集视图。complement 也是 RangeSet 类型,包含了不相连的、非空的区间。
        - subRangeSet(Range)：返回 RangeSet 与给定 Range 的交集视图。这扩展了传统排序集合中的 headSet、subSet 和 tailSet 操作。
        - asRanges()：用 Set<Range>表现 RangeSet，这样可以遍历其中的 Range。
        - **这个方法好像是没有啦。。。** asSet(DiscreteDomain)（仅 ImmutableRangeSet 支持）：用 ImmutableSortedSet表现 
        RangeSet，以区间中所有元素的形式而不是区间本身的形式查看。（这个操作不支持 DiscreteDomain 和 RangeSet 都没有上边
        界，或都没有下边界的情况）
    - RangeSet 的查询方法
        - contains(C)：RangeSet 最基本的操作，判断 RangeSet 中是否有任何区间包含给定元素。
        - rangeContaining(C)：返回包含给定元素的区间；若没有这样的区间，则返回 null。
        - encloses(Range)：简单明了，判断 RangeSet 中是否有任何区间包括给定区间。
        - span()：返回包括 RangeSet 中所有区间的最小区间。
        
- RangeMap
*RangeMap 描述了”不相交的、非空的区间”到特定值的映射。和 RangeSet 不同，RangeMap 不会合并相邻的映射，即便相邻的区间映
射到相同的值。*
    - RangeMap 的视图
        - asMapOfRanges()：用 Map<Range, V>表现 RangeMap。这可以用来遍历 RangeMap。
        - subRangeMap(Range)：用 RangeMap 类型返回 RangeMap 与给定 Range 的交集视图。这扩展了传统的 headMap、subMap 和
        tailMap 操作。

### 3、集合工具类
集合接口	属于JDK还是Guava	对应的Guava工具类
Collection	JDK	                Collections2：不要和 java.util.Collections 混淆
List	    JDK	                Lists
Set	        JDK	                Sets
SortedSet	JDK	                Sets
Map	        JDK	                Maps
SortedMap	JDK	                Maps
Queue	    JDK	                Queues
Multiset	Guava	            Multisets
Multimap	Guava	            Multimaps
BiMap	    Guava	            Maps
Table	    Guava	            Tables

### 4、集合扩展工具类
- Forwarding装饰器
> 针对所有类型的集合接口，Guava 都提供了 Forwarding 抽象类以简化装饰者模式的使用。
Forwarding 抽象类定义了一个抽象方法：delegate()，你可以覆盖这个方法来返回被装饰对象。所有其他方法都会直接委托给 delegate()。
例如说：ForwardingList.get(int)实际上执行了 delegate().get(int)。
通过创建 ForwardingXXX 的子类并实现 delegate()方法，可以选择性地覆盖子类的方法来增加装饰功能，而不需要自己委托每个方法，
因为所有方法都默认委托给 delegate()返回的对象，你可以只覆盖需要装饰的方法。
此外，很多集合方法都对应一个”标准方法[standardxxx]”实现，可以用来恢复被装饰对象的默认行为，以提供相同的优点。比如在
扩展 AbstractList 或 JDK 中的其他骨架类时，可以使用类似 standardAddAll 这样的方法。