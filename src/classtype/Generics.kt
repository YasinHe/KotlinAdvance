package classtype

/**
 * java很多时候必须依赖通配符，去实现泛型的数据集合，比如原则上的列表优先于数组
 * 就是因为列表实际上要求统配类型，而? extends E  也就决定了，往往list的addAll添加类型必须是E的子类型
 * List<String> strs = new ArrayList<String>();
   List<Object> objs = strs;
   objs.add(1); // 这里我们把一个整数放入一个字符串列表
   String s = strs.get(0); // ！！！ ClassCastException：无法将整数转换为字符串
   上面的代码在java里面是完全错误的，第二行无法赋值  第四行无法转换
 */
interface Source<out T> {
    fun nextT(): T
}

fun demo(strs: Source<String>) {
    val objects: Source<Any> = strs // 这个没问题，因为 T 是一个 out-参数
    // ……
}

interface Comparable<in T> {
    //Operator overloading 操作重载符
    operator fun compareTo(other: T): Int
}

fun demo(x: Comparable<Number>) {
    x.compareTo(1.0) // 1.0 拥有类型 Double，它是 Number 的子类型
    // 因此，我们可以将 x 赋给类型为 classtype.Comparable <Double> 的变量
    val y: Comparable<Double> = x // OK！
}
/**
 * //转换：消费者 in, 生产者 out!
 */
