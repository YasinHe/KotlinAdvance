//对象表达式的使用
//有时候，我们需要创建一个对某个类做了轻微改动的类的对象，而不用为之显式声明新的子类。 Java 用匿名内部类 处理这种情况
//object关键字的三种作用：1.作为对象表达式（比如第14行） 2.作为对象字面量（第29行）  3.作为单例
open class A(x: Int) {
    public open val y: Int = x
    public open var x1:Int = x
}

interface B {
    fun xy()
}

//如果超类型有一个构造函数，则必须传递适当的构造函数参数给它。 多个超类型可以由跟在冒号后面的逗号分隔的列表指定(匿名类的对象)
val ab: A = object : A(1),B {
    override fun xy() {
        y+x1
    }
    override val y: Int
        get() = 15
    override var x1: Int
        get() = super.x1
        set(value) {}
}

//
fun main(args: Array<String>) {
    //创建一个对象，他并没有什么超类，就是一个简单的对象
    fun foo() {
        val adHoc = object {
            var x: Int = 0
            var y: Int = 0
        }
        print(adHoc.x + adHoc.y)
    }
    //普通对象
    foo()
    //匿名对象测试
    var c:C = C()
    c.bar()
    //对象声明
    MyInfo.y
}

/**
 * 要注意到：匿名对象可以用作只在本地和私有作用域中声明的类型
 * 如果你使用匿名对象作为公有函数的返回类型或者用作公有属性的类型，那么该函数或属性的实际类型会是匿名对象声明的超类型
 * 如果你没有声明任何超类型，就会是 Any
 */
class C {
    // 私有函数，所以其返回类型是匿名对象类型
    private fun foo() = object {
        val x: String = "x"
    }

    // 公有函数，有没有超类型函数，所以其返回类型是 Any
    fun publicFoo() = object {
        val x: String = "x"
    }

    fun bar() {
        val x1 = foo().x        // 没问题
        val x2 = publicFoo()
//        val x3 = publicFoo().x  // 错误：未能解析的引用“x”
    }
}

/*
object相当于 static class
当关键字object之后指定了一个名称, 那么它就不再是“对象表达式”，而是一个对“对象声明”
 * 此时，此对象不再是表达式，看作类的变种更为合适吧，不能再将其赋值给一个变量。在使用它时，只需要它的名字引用即可
 */
object MyInfo: A(1),B {
    override fun xy() {

    }
    override val y: Int
        get() = 14
}
