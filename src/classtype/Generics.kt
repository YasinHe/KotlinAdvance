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
    val objects: Source<Any> = strs // 这个没问题，因为 T 是一个 out-参数,如果没有out这里无法赋值
    // ……
}

interface Comparable<in T> {
    //Operator overloading 操作重载符,重载符的意思就是可以用符号对方法直接连接而不需要在括号里调用参数
    operator fun compareTo(other: T): Int
}

fun demo(x: Comparable<Number>) {
    x.compareTo(1.0) // 1.0 拥有类型 Double，它是 Number 的子类型
    // 因此，我们可以将 x 赋给类型为 classtype.Comparable <Double> 的变量
    val y: Comparable<Double> = x // OK！
}

interface ProductionConsumer<T> {
    fun produce(): T
    fun consume(item: T)
}
/**
 * //转换：消费者 in（只写入而不可以被读取（相当于Java中 ? super T））----  消费指定泛型对象(子类泛型对象可以赋值给父类) fun consume(item: T)
 * 生产者 out（只读取，相当于Java中? extends T）----  使用时机是在 泛型作为内部方法的返回(父类泛型对象可以赋值给子类) fun nextT(): T
 * Invariant  兼顾in和out 可以同时存在的时候
 */
