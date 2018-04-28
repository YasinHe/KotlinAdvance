//kotlin多重构造器
class Person(var name: String) {
    init {
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