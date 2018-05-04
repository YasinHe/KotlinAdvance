package classtype

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * 委托:另一个对象帮你做事情,比如安卓的adapter回传activity，处理事件，也是一种委托
 */
interface Base {
    fun print()
    val message: String
}

class BaseImpl(val x: Int) : Base {
    override val message = "classtype.BaseImpl: x = $x"
    override fun print() { println(message) }
    fun print1(){
        println("print1")
    }
}

//classtype.Derived 的超类型列表中的 by-子句表示 b 将会在 classtype.Derived 中内部存储并且编译器将生成转发给 b 的所有 classtype.Base 的方法。
class Derived(b: Base) : Base by b{
    override val message = "Message of classtype.Derived"
//    override fun print() { print(message) }//覆盖之后就不会被b的base影响了
    fun print1(){
        println("print2")
    }
}

fun main(args: Array<String>) {
    val b = BaseImpl(10)
    //Derived的所有跟Base相关工作都由b来完成了
    Derived(b).print()
    Derived(b).print1()
    //重写的成员不会在委托对象的成员中调用 ，委托对象的成员只能访问其自身对接口成员实现
    println(Derived(b).message)
    //这里打印结果是10 和 print2 最后message是Message of classtype.Derived

    //属性委托(重铸setter，getter)
    val e = Example()
    println(e.p)
    e.p = "NEW"

    //延迟属性
    //lazy() 是接受一个 lambda 并返回一个 Lazy <T> 实例的函数，返回的实例可以作为实现延迟属性的委托：
    // 第一次调用 get() 会执行已传递给 lazy() 的 lambda 表达式并记录结果， 后续调用 get() 只是返回记录的结果
    //lazy{} 只能用在val类型,比如封装延迟加载给view，返回泛型View，然后findViewById，这样bind的view是延迟到加载时才初始化

    // lazy 属性的求值是同步锁的（synchronized）
    //下面传的这个值得意思是：如果初始化委托的同步锁不是必需的，这样多个线程可以同时执行
    val lazyValue: String by lazy(LazyThreadSafetyMode.PUBLICATION) {
        println("computed!")
        "Hello"
    }
    println(lazyValue)
    println(lazyValue)
    //打印结果 computed!  Hello  Hello（第一次执行lambda，第二次只执行这个lazyValue get（）的返回结果）

    //可观察属性
    val user = UserModel()
    user.name = "first"
    user.name = "second"

    //把属性储存在映射中,可以使用映射实例自身作为委托来实现委托属性
    class User(val map: Map<String, Any?>) {
        val name: String by map
        val age: Int     by map
    }
    val user2 = User(mapOf(
            "name" to "John Doe",
            "age"  to 25
    ))
    println(user2.name) // Prints "John Doe"
    println(user2.age)  // Prints 25
}

//--------------------------------------------------------------------属性委托-----------------------------------------------------------------------------------
class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}

class Example {
    //属性对应的 get()（和 set()）会被委托给它的 getValue() 和 setValue() 方法
    var p: String by Delegate()
}


class UserModel {
    //还可以使用vetoable()
    //它有三个参数：被赋值的属性、旧值和新值
    var name: String by Delegates.observable("<no name>") {
        prop, old, new ->
        println("$old -> $new")
    }
}