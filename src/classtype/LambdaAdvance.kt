package classtype

import model.Person
import sum

/**
 * Kotlin 函数都是头等的，这意味着它们可以存储在变量与数据结构中、
 * 作为参数传递给其他高阶函数以及从其他高阶函数返回。
 * 可以像操作任何其他非函数值一样操作函数。
 */

/**
 * 高阶函数
 */
//Collection<T>.fold是扩展函数，首先参数 combine 具有函数类型 (R, T) -> R，所以fold接受一个函数作为参数
//这个函数接受类型为R和T，并且返回一个R类型的值，在for循环内部调用这个函数，然后返回赋值
//泛型类型定义与java一样，类型参数声明在函数名前
fun <T, R> Collection<T>.fold(
        initial: R,
        combine:(acc: R, nextElement: T) -> R
): R {
    var accumulator: R = initial
    for (element: T in this) {
        accumulator = combine(accumulator, element)
    }
    return accumulator
}

fun main(args: Array<String>) {
    val items = listOf(1, 2, 3, 4, 5)
    // Lambdas 表达式是花括号括起来的代码块。
    items.fold(0,{
        // 如果一个 lambda 表达式有参数，前面是参数（名字随便），后跟“->”
        acc: Int, nextElement: Int ->
        print("acc = $acc, nextElement = $nextElement, ")
        val result = acc + nextElement
        println("result = $result")
        // lambda 表达式中的最后一个表达式是返回值：
        result
    })
    val items2 = listOf("1", "2", "3", "4", "5")
    // lambda 表达式的参数类型是可选的，如果能够推断出来的话：
    val joinedToString = items.fold("Elements:",
            { acc, i -> acc + " " + i })
    // 函数引用也可以用于高阶函数调用：
    val product = items.fold(1, Int::times)// 1*1 -> 1*2 ->  2*3 ->  6*4 ->  24*5   所以Int::times相当于接受两个int参数，把他们相乘返回(times就是相乘的意思)
    println("joinedToString = $joinedToString")//Elements: 1 2 3 4 5
    println("product = $product")//120

    /**
     * 函数类型
     * 所有函数类型都有一个圆括号括起来的参数类型列表以及一个返回类型
     * (A, B) -> C 表示接受类型分别为 A 与 B 两个参数并返回一个 C 类型值的函数类型。
     */
    fun testLambda1(someThing:(a:Int,b:Int)->Int):Unit{
        //do nothing
        println("testLambda1---${someThing(1,2)}")
    }
    testLambda1({ a,b -> a+b })
    //参数类型列表可以为空，如 () -> A 但是Unit作为返回类型不可以省略
    fun testLambda2(someThing:()->Int):Unit{
        //do nothing
        println("testLambda2---${someThing()}")
    }
    testLambda2({
        ->1
    })
    //额外的接收者类型:类型 A.(B) -> C 表示可以在 A 的接收者对象上以一个 B 类型参数来调用并返回一个 C 类型值的函数
    fun testLambda3(sum : Int.(other: Int) -> Int):Unit{
        //do nothing
        println("testLambda3---${2.sum(3)}")
    }
    testLambda3({
        x -> this+x//x是括号的数，this是输入的2也就是指的目标对象
    })

    //挂起函数:suspend () -> Unit 或者 suspend A.(B) -> C
    //类型别名: typealias ClickHandler = (Button, Int) -> Unit

    //函数类型实例化
    class IntTransformer: (Int) -> Int {
        override operator fun invoke(x: Int): Int = TODO()
    }
    val intFunction: (Int) -> Int = IntTransformer()
    val a = {
        i: Int -> i + 1
    } // 推断出的类型是 (Int) -> Int
    a.invoke(3)//invoke方法的存在是因为lambda是个对象，让对象像函数一样调用，这个结果应该是4
    //带与不带接受者的函数类型非字面值可以互换，接受者可以代替第一个参数（ (A, B) -> C 类型的值可以传给或赋值给期待 A.(B) -> C ）
    //再解释：意思就是下面的String.(Int) -> String 完全等于  （String，Int） -> String 传入一个string一个int返回一个{}返回值是string
    val repeat: String.(Int) -> String = {
        times ->
        this.repeat(times)//这里this可以省略，为了看值(这里的repeat是调用的string本身的重复api)
    }//看下面的s3
    var s:String = repeat("1",1)//"1"
    var s2:String = repeat.invoke("1",2)//"11"
    var s3:String = "1".repeat(3)//类扩展  "111"
    val twoParameters: (String, Int) -> String = repeat // OK
    fun runTransformation(f: (String, Int) -> String): String {
        return f("hello", 3)
    }
    val result = runTransformation(repeat) // OK
    println("result = $result")//hello重复三次

    /**
     * lambda语法
     */
    //表达式总是括在花括号中,完整语法形式的参数声明放在花括号内,并有可选的类型标注,函数体跟在一个 -> 符号之后
    //下面三行等价
    val sum = { x: Int, y: Int -> x + y }
    val sum2: (Int, Int) -> Int = { x, y -> x + y }
    val sumx: Int.(Int) -> Int = { x -> x + this }
    //下面两行等价
    sumx(1,2)
    1.sumx(2)
    //在 Kotlin 中有一个约定：如果函数的最后一个参数接受函数，
    // 那么作为相应参数传入的 lambda 表达式可以放在圆括号之外(比如下面这样，1后面可以加个,然后把括号放最后，也可以直接括号{}在外)
    val product1 = items.fold(1) { acc, e -> acc * e }
    //如果表达式是唯一的参数，那么圆括号可以省略
    run { println("...") }

    //如果 lambda 表达式的参数未使用，那么可以用下划线取代其名称：
//    map.forEach { _, value -> println("$value!") }

    //Lambda表达式或者匿名函数可以访问其闭包,可以访问外部作用域声明的变量

    //一个带有接收者的函数字面值及其类型的示例
    val sum3: Int.(Int) -> Int = { other -> plus(other) }
    //匿名函数语法允许你直接指定函数字面值的接收者类型
    val sum4 = fun Int.(other: Int): Int = this + other

    //lambda调用Html
    html ("https://www.baidu.com"){       // 带接收者的 lambda 由此开始
        println("$this--go")
        body()   // 调用该接收者对象的一个方法
    }
}

class HTML (url:String){
    val urlAddress:String = url
    fun body() {
        println("body")
    }
}

/**
 * 这个函数接受一个名为 init 的参数，该参数本身就是一个函数。 该函数的类型是 HTML.() -> Unit,由html才能调用的没有参数函数，
 * 它是一个 带接收者的函数类型 。 这意味着我们需要向函数传递一个 HTML 类型的实例（ 接收者 ），
 * 并且我们可以在函数内部调用该实例的成员。 该接收者可以通过 this 关键字访问
 */
fun html(url:String , init: HTML.() -> Unit): HTML {
    val html = HTML(url)  // 创建接收者对象
    html.init()           // 将该接收者对象传给该 lambda，这一步就是调用括号里的方法
    return html
}

