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