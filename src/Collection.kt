/**
 * Kotlin 的 List<out T> 类型是一个提供只读操作如 size、get等的接口。
 * 和 Java 类似，它继承自 Collection<T> 进而继承自 Iterable<T>
 */
fun main(args: Array<String>) {
    //可变list
    val numbers: MutableList<Int> = mutableListOf(1, 2, 3)
    val readOnlyView: List<Int> = numbers//不可变List，但是引用会跟随上面的numbers
    println(numbers)        // 输出 "[1, 2, 3]"
    numbers.add(4)
    println(readOnlyView)   // 输出 "[1, 2, 3, 4]"
//    readOnlyView.clear()    // -> 不能编译
    val strings = hashSetOf("a", "b", "c", "c")
    assert(strings.size == 3)
    //不可变list,返回的实现是 array list
    val items = listOf(1, 2, 3, 4)
    //快照保存
    val snap:Snap = Snap()
    snap.items.add("1")
    snap.items.add("2")
    var save = snap.items2
    snap.items.add("3")
    System.out.print(save)
    //扩展方法
    items.first() == 1
    items.last() == 4
    items.filter { it % 2 == 0 }   // 返回 [2, 4]
    val rwList = mutableListOf(1, 2, 3)
    rwList.requireNoNulls()        // 返回 [1, 2, 3]
    if (rwList.none { it > 6 }) println("No items above 6")  // 输出“No items above 6”
    val item = rwList.firstOrNull()
    //map
    val readWriteMap = hashMapOf("foo" to 1, "bar" to 2)
    println(readWriteMap["foo"])  // 输出“1”
    val snapshot: Map<String, Int> = HashMap(readWriteMap)
}

class Snap{
    //还是上面那个readOnlyView，如果只是想保存一个快照，而不是引用更随
    val items = mutableListOf<String>()
    val items2: List<String> get() = items.toList()//set和get只能在class使用，funcation里面没有
}