package classtype

/**
 * 在 Kotlin 中函数可以在文件顶层声明，这意味着你不需要像一些语言如 Java、C# 或 Scala 那样创建一个类来保存一个函数
 * Kotlin 中函数也可以声明在局部作用域、作为成员函数以及扩展函数
 * 局部函数：一个函数内部有另一个函数，这个局部函数可以访问外部函数的局部变量
 * 成员函数：类或者对象内部定义的函数，用类的实例加上.表示
 *
 */
fun main(args: Array<String>) {

    //需要两个int类型参数，以及一个返回为Unit并且构造没有参数的方法
    foo(1,2) { println("hello") }
    foo(1) { println("hello") } // 使用默认值 baz = 1
    foo { println("hello") }    // 使用两个默认值 bar = 0 与 baz = 1

    //参数有默认值得情况下，省略表示
    var manyParameter = ManyParameter(str = "ok",wordSeparator = 'z')
    //允许调用 f(1, y = 2) 但不允许 f(x = 1, 2)
    var manyParameter1 = ManyParameter( "ok",false)
//    var manyParameter2 = ManyParameter( str = "ok",false)//这一行是报错的

    //可变数量的参数
    val list = asList(1, 2, 3)
    val a = arrayOf(1, 2, 3)
       //伸展操作符，把已有的Array<T>交给vararg当中
    val list2 = asList(-1, 0, *a, 4)


}

//需要两个int类型参数，以及一个返回为Unit并且构造没有参数的方法
fun foo(bar: Int = 0, baz: Int = 1, qux: () -> Unit) { /* …… */ }

//一函数名称来赋值java并不可以， Java 字节码并不总是保留函数参数的名称
class ManyParameter(str: String,
                   normalizeCase: Boolean = true,
                   upperCaseFirstLetter: Boolean = true,
                   divideByCamelHumps: Boolean = false,
                   wordSeparator: Char = ' '){

}

//在函数内部，类型 T 的 vararg 参数的可见方式是作为 T 数组  就是说ts = Array<out T>
//vararg只有一个参数可以标记为它，一般是最后一个相当于java的 T... values
fun <T> asList(vararg ts: T): List<T> {
    val result = ArrayList<T>()
    for (t in ts) // ts is an Array,遍历ts的每个item t
        result.add(t)
    return result
}

//泛型函数
fun <T> singletonList(item: T): List<T> {
    var list: List<T> = listOf<T>(item)
    return list
}