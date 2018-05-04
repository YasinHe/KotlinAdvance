package classtype

fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // 'this' corresponds to the list
    this[index1] = this[index2]
    this[index2] = tmp
}
/**
 * 实现意义：在java中需要写各种units，但是都难看，我们想要他是静态的，直接调用，如果再加上类型条件就更完美了，kotlin扩展就做到了
 *  能够扩展一个类的新功能而无需继承该类或使用像装饰者这样的任何类型的设计模式。 这通过叫做 扩展 的特殊声明完成
    MutableList：表示函数的接收者，也就是函数扩展的对象
    . ：扩展函数修饰符
    classtype.swap：扩展函数的名称
    (...)：扩展函数的参数，可以为null
 */
fun main(args: Array<String>) {
    val l = mutableListOf(1, 2, 3, 9, 60, 54, 8, 21)
    l.forEachIndexed { index, value ->
        //run方法的意思是：调用run函数块。返回值为函数块最后一行，或者指定return表达式
        run {
            l.forEachIndexed { index, value ->
                run {
                    if (((index + 1) < l.size) && (l[index + 1] >= l[index])) {
                        // println(""+l[index+1]+">"+""+l[index])
                        l.swap(index, index + 1)
                    }
                }
            }
        }
    }
    println("--------------------")
    for (t in l.indices) {
        println(l[t])
    }
    println("--------------------")

    //成员扩展---------------------------------------------------------------------------------------------------
    class D {
        fun bar() {  }
    }

    class C {
        fun baz() { }

        fun D.foo() {
            bar()   // 调用 D.bar
            baz()   // 调用 C.baz
        }

        fun D.foo2() {
            toString()         // 调用 D.toString()
            this@C.toString()  // 调用 C.toString()
        }

        fun caller(d: D) {
            d.foo()   // 调用扩展函数
        }
    }
}