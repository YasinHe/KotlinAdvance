//对象表达式的使用
//有时候，我们需要创建一个对某个类做了轻微改动的类的对象，而不用为之显式声明新的子类。 Java 用匿名内部类 处理这种情况
//object关键字的三种作用：1.作为对象表达式（比如第21行） 2.作为对象字面量（第38行）  3.作为单例
open class A(x: Int) {
    public open val y: Int = x
    public open var x1:Int = x
}

interface B {
    fun xy()
}

class D: B{
    override fun xy() {

    }
}

//如果超类型有一个构造函数，则必须传递适当的构造函数参数给它。 多个超类型可以由跟在冒号后面的逗号分隔的列表指定(匿名类的对象)
//下面的ab的本质class类型是A  只是通过object多加了一个接口给他实现多一个功能，但在外部还是无法调用xy的
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
    val d:D = D()
    d.xy()
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
 * 作用域关系：外部访问内部
 * 如果匿名对象作为本地/局部类型 或 私有类型,可正常使用!
 * 如果匿名对象作为公有函数返回类型 或 公有属性类型,
 * 那么实际类型是超类(没有超类,就是Any),无法访问匿名对象的成员!
 */
class C {
    // 私有函数，所以其返回类型是匿名对象类型
    private fun foo() = object {
        val x: String = "x"
    }

    // 公有函数，又没有超类型函数，所以其返回类型是 Any
    fun publicFoo() = object {
        val x: String = "x"
    }

    fun bar() {
        val x1 = foo().x        // 没问题: 类型是object{..},可以访问x
        val x2 = publicFoo()
//        val x3 = publicFoo().x  // 错误：未能解析的引用“x” 类型是Any,无法访问x
    }
}

/*
object单例对象,而companion object伴生相当于class的static成员
当关键字object之后指定了一个名称, 那么它就不再是“对象表达式”，而是一个对“对象声明”
 * 此时，此对象不再是表达式，看作类的变种更为合适吧，不能再将其赋值给一个变量。在使用它时，只需要它的名字引用即可
 * 在JVM平台,使用@JvmStatic注解,使伴生对象成员成为真正的静态方法和字段！
 */
object MyInfo: A(1),B {

    override fun xy() {

    }
    override val y: Int
        get() = 14
}
