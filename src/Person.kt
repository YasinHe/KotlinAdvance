//kotlin多重构造器(次构造函数)
data class Person(var name: String) {
    var age:Int = 0

    init {
        //他其实是主构造函数的一部分，会在构造之初执行
        name = "hello"+ name
        println(name)
    }
    constructor(age: Int) : this("js") {
        println(name + " " + age)
    }
    constructor(sex : Byte) :this(20){
        println(name +" "+ sex)
    }
}