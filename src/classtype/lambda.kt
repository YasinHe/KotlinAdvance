package classtype

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

}

//需要两个int类型参数，以及一个返回为Unit并且构造没有参数的方法
fun foo(bar: Int = 0, baz: Int = 1, qux: () -> Unit) { /* …… */ }

class ManyParameter(str: String,
                   normalizeCase: Boolean = true,
                   upperCaseFirstLetter: Boolean = true,
                   divideByCamelHumps: Boolean = false,
                   wordSeparator: Char = ' '){

}