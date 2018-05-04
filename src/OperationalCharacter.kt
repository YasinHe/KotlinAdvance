import model.Person

fun main(args: Array<String>) {
    //run方法
    /**
     * 立即执行
     */
    val a = run {
        println("run")
        return@run 3
    }
    println(a)
    //调用某对象的run函数，在函数块内可以通过 this 指代该对象。返回值为函数块的最后一行或指定return表达式!!
    val a2 = "string".run {
        println(this)
        3
    }
    println(a2)

    //---------------------------------------------------------------------------------------------------------------------------------------------------

    //apply(申请) 调用某对象的apply函数，在函数块内可以通过 this 指代该对象。返回值为该对象自己,然后可以直接操作这个this的属性 = xxxx
    /**
     * 操作属性，返回自己
     */
    val person = Person("Yasin")
    val a3 = person.apply {
        println(this.name)
        name = "He"
        println(this.name)
    }
    println(a3)

    //---------------------------------------------------------------------------------------------------------------------------------------------------

    //let:调用某对象的let函数，则该对象为函数的参数。在函数块内可以通过 it 指代该对象。返回值为函数块的最后一行或指定return表达式!!
    /**
     * 操作对象，返回一个东西
     */
    val a4 = "string".let {
        println(it)
        3// 这个返回值写什么都行
    }
    println(a4)

    //---------------------------------------------------------------------------------------------------------------------------------------------------

    //also:调用某对象的also函数，则该对象为函数的参数。在函数块内可以通过 it 指代该对象。返回值为该对象自己!!
    /**
     * 操作对象，返回自己
     */
    val a5 = "string".also {
        println(it)
    }
    println(a5)

    //---------------------------------------------------------------------------------------------------------------------------------------------------

    //with:它是将某对象作为函数的参数，在函数块内可以通过 this 指代该对象。返回值为函数块的最后一行或指定return表达式
    /**
     * 输入一个对象，操作对象，返回一个东西
     */
    val a6 = with("string") {
        println(this)
        3
    }
    println(a6)
}