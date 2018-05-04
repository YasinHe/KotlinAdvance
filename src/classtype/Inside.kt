package classtype

import java.awt.event.ActionListener

//嵌套类(标记inner的才是内部类)
class Outer {
    private val bar: Int = 1
    class Nested {
        fun foo() = 2
    }
}

//内部类,类可以标记为 inner 以便能够访问外部类的成员。内部类会带有一个对外部类的对象的引用
class Outer2{
    private val bar: Int = 1
    private lateinit var listener:OnClickListener
    inner class Inner {
        fun foo() = bar//没有这个引用  就无法访问bar
        fun setListener(listener1:OnClickListener ){
            listener = listener1
        }
    }
}

fun main(args: Array<String>) {
    val demo = Outer.Nested().foo() // == 2
    val demo2 = Outer2().Inner().foo() // == 1
    //下面的是匿名内部类实现,需要使用object 这个关键字
    Outer2().Inner().setListener(object : OnClickListener {
        override fun click() {
            println("clicked")
        }
    })

    //java内部类可以这样使用，接口类型前缀的lambda表达式创建
    val listener = ActionListener { println("clicked") }
    //kotlin的需要这样
    val listener2 = object : OnClickListener {
        override fun click() {
            println("clicked")
        }
    }
    Outer2().Inner().setListener(listener2)
}

interface OnClickListener{
    fun click()
}
