import classtype.swap
import model.Person
import java.util.concurrent.locks.Lock

//函数式闭包(方法的返回值可以是一个方法，并且规定该方法的返回值)
fun funcation():()->String{//返回一个函数，并且函数的返回值需要是String
    var x = 0
//    ++x
    return fun():String{
        ++x
        println(x)
        return x.toString()
    }
}

//Data Classes(那种什么都不做只持有数据的Bean类)
data class User(val name: String, val age: Int)

//面向对象类
class Rect(var width:Int,var height:Int){
    fun area():Int{
        return width*height
    }
}
//继承(默认来说所有的类都是final的，除非现式的声明)
// 原则： 设计一个基类时，应该避免在构造函数、属性初始化器以及 init 块中使用 open 成员(因为父类先于子类初始化
// 子类重写的open还未初始化，可能在父类使用时会不正确，此时父类用的open是自己的，不是子类的)
open class Father{
    var charactor:String = "好人"
    fun action(){
    }
    open fun action2(){//open表示继承会重写，重写之后的“孙子”类如果不想允许重写，就要在子类重写前面加上final
    }
}
open class Son():Father(){
    init {
        //初始化只能放在init关键字做初始化，主构造不能包含任何代码
        charactor = "坏人"
    }
    constructor(power:String) : this() {
        //第二构造传值，继承的子也要传值(看下面的grandSon)
    }
    final override fun action2(){
    }
}
class grandSon(power:String): Son(power) {

}
//抽象
abstract class HuMan(name:String = "Jack"){
    var name = name;
    abstract fun eat()//抽象方法不能有实现
}
//接口
interface IMan {
    val prop: Int // 抽象的
//    get() = 1   //加上这个就非抽象了,因为接口里的值是不能初始化的，所以子必须实现然后给一个值，或者接口通过get返回值那也行。
    var propertyWithImplementation: String//非抽象的
        get() {
                return "foo"
        }
       //返回了get  所以不是抽象的
        set(value){
            println("currentValue:$value")
        }
    fun keke()
    fun foo() {//有括号内容实现，所以也不是抽象的
        print(prop)
    }
}
class Man(name:String):HuMan(name),IMan{
    override val prop: Int = 29
    override fun keke() {
        propertyWithImplementation
        propertyWithImplementation = "Ace"
    }
    override fun eat() {
        println("i am Man")
    }
}
//代理，b指挥a做接口需要做的事情(这里的by是创建一个新的Man对象，除非单例)
class OtherMan(name:String="Ok",hair:String = "Long"):IMan by Man(name){
    val hair:String = hair
    override fun keke() {
        //我其实是OtherMan
        println("i am OtherMan")
    }
}
//单例(直接创建在jvm内存之中，并且唯一)
object ThirdMan:IMan{
    override val prop: Int = 30
    override fun keke() {
    }
}
class FMan:IMan by ThirdMan{
    override fun keke() {
        ThirdMan.keke()
    }
}
//枚举
enum class Week{
    星期一,星期二,星期三
}
//Sealed class,密封类(子类类型有限的class)
sealed class Gril{
    var sex:String = "女孩"
    fun introduceSelf(){
        println("i am $sex")
    }
    class LittleGirl(name: String):HuMan(name){
        override fun eat() {
        }
    }
    class AdultGril:Gril()
}

//dsl 领域特定语言(声明式编程)又叫中缀表示法(要求：必须是成员、扩展函数，必须只有一个参数，参数不可以接受可变数量参数而且不可以有默认值)
infix fun List<HuMan>.fount(name:String){
    filter {
        it.name == name
    }.forEach (::println)
    //  ::就是lambda的闭包，同理Math::max等效于(a, b)->Math.max(a, b),s::isEmpty等效于()->s.isEmpty()
    //在java中使用一般是类名加方法名（称为方法引用）  model.Person::getName == person -> person.getName();
    // () -> new HashMap<>();  ==  HashMap::new
}

//protected：只用于类和接口内 internal如下
internal fun 同一模块访问修饰符(){

}

//重写set，get;当一个属性需要一个幕后字段时，Kotlin 会自动提供field ，标识符只能用在属性的访问器内。
var stringRepresentation:String? = null // 这里等于null 这个初始器直接为幕后字段赋值 为 null
    get() = stringRepresentation.toString()
    private set(value) {
        if(value==="Ace"){
            field = value
        }
    }
//因为通过默认 getter 和 setter 访问私有属性会被优化，所以不会引入函数调用开销;默认属性
private var _table: Map<String, Int>? = null
public val table: Map<String, Int>
    get() {
        if (_table == null) {
            _table = HashMap() // 类型参数已推断出
        }
        return _table ?: throw AssertionError("Set to null by another thread")
    }
//    set(v){//这里是不行的  val没有set，只有一个get，交给了_table
//
//    }

fun main(args: Array<String>) {
    //这里只输出了一个参数name，显示的表示了name，而hair有默认值，不写就用默认值;当然如果不写默认值那这里就报错了,除非用lambda
    var otherMan = OtherMan("Yasin")
    println("${otherMan.prop}")
    otherMan.keke()

    var rect01 = Rect(20,20)
    println("高度:${rect01.height}")
    var son = Son()
    println("儿子：${son.charactor}")
    var man1 = Man("Ace")
    man1.keke()
    var man2 = Man("BASH")
    var man3 = Man("COLOR")
    var listMan = listOf<HuMan>(man1,man2,man3)
    listMan.forEach {
//        huMan: HuMan -> huMan.eat()
        it.eat()//用匿名的it 可以代替上面的，完成函数式
    }
    listMan.find {
        //查找第一个符合条件的，并返回
        it.name=="Ace"
    }
    listMan.groupBy {
        it.name// 根据限定条件去分组(返回一个map，value包含List)
    }.get("Ace")?.forEach { it.eat() } //这里不加? 号会报错，因为可能分组结果里面没有这个key
    listMan fount "Ace"  //领域特定语言，直接空格，不需要点

    Week.星期二.ordinal//元素位置
//    var gril = Gril()//这个是报错的
    var gril = Gril.LittleGirl("小可爱")
    gril.eat()
    var gril2 = Gril.AdultGril()
    gril2.introduceSelf()
    var grilMap = mutableMapOf<String,Gril>()
    grilMap.put("1",gril2)
//    grilMap.put("2",gril)//塞不进去
    grilMap.forEach { t, u -> u.introduceSelf() }
    grilMap.maxBy { it.value.sex }//最大过滤(集合都可以用) minBy
    grilMap.filter {
        ((it.key=="1") and (it.value.sex==""))
    }//条件过滤，多条件用括号 加 and or xor inv
    grilMap.map {
        it.value.sex  //获取已知集合的所有数据，然后赋给一个新的集合再返回出来（返回一个List）
    }
    grilMap.any {
        it.value.sex=="18"  //判断是否有，返回boolean
    }
    grilMap.count {
        it.value.sex=="18" //年龄十八的有多少人
    }

    //函数式闭包测试(该函数返回是一个函数，本函数只支持一次new，
    // 下面三个执行的都是他的返回函数 执行三次，每次x的值都得以保留
    // java函数里不能声明函数，也不能返回函数，函数也不能作为参数传给另一个函数
    //函数本身没有状态也没有封装能力，但是通过函数闭包，他的内部参数得以保留状态
    //高阶函数：参数或者返回值为函数的函数
    var funcationTest = funcation()
    funcationTest()
    funcationTest()
    funcationTest()
    whileFuncation()//不是现式写出是private都可以直接调用

    //单例
    var singleton = Singleton.getInstance()

    //lateinit延迟加载，不能初始化的
    Lateinit().initializationLogic()

    //if可以作为表达式，直接if xx  则xx  else  xx，不需要三目运算符

    //标签，break，continue;标签限制的 break 跳转到刚好位于该标签指定的循环后面的执行点
    // (如果这里不是break@loop，那么会继续loop，加了则会出去),foo方法里的循环也是一样
    loop@ for (i in 1..100) {
        for (j in 1..100) {
            if (i==3&&j==1) break@loop
        }
    }
    foo()//继续测试标签的使用

    //扩展函数
    val l = mutableListOf(1, 2, 3, 9, 60, 54, 8, 21)
    l.swap(0, 1)

    //data数据的复制
    var person: Person = Person("heyx")
    person.copy("yasin")
    //解析声明
    val name = person.component1()//多参数类型可以  （Param1，param2） = obj
    //在 toString()、 equals()、 hashCode() 以及 copy() 的实现中只会用到 name 属性
    val person1 = Person("John")
    val person2 = Person("John")
    person1.age = 10
    person2.age = 20
    person1===person2
    println("person1 == person2: ${person1 == person2}")
    println("person1 with age ${person1.age}: ${person1}")
    println("person2 with age ${person2.age}: ${person2}")
    //这里打印结果是 true ，认为这两个对象的值是相等的，===是不相等的
}

/**
 * 调用一个方法会经历压栈和出栈的过程，这个过程需要耗费性能
 * inline内联，比如这个check方法很简单，里面方法也可以直接写到需要的方法里面
 * 但是为了封装和结构就是需要拉出来作为方法，那么多次调用的话，可以选择内联
 * 编译器在编译时会优化，实际是增加代码的，但是调用是高效的
 *但是下面使用otherCheck，非内联方法就会报错，因为body已经是内联，不是一个函数对象，不能传递
 * 除非下面那个也改成内联函数
 */
inline fun <T> check(lock: Lock, body: () -> T): T {
    lock.lock()
    try {
//        otherCheck(body)//会报错
        return body()
    } finally {
        lock.unlock()
    }
}

fun foo() {
    listOf(1, 2, 3, 4, 5).forEach lit@{
        if (it == 3) return@lit // 局部返回到该 lambda 表达式的调用者，即 forEach 循环   @forEach代替@lit也可以
        print(it)//这里打印结果应该是： 1245 done with explicit label  如果是直接return ：12
    }
    print(" done with explicit label")

    //调用run函数块。返回值为函数块最后一行，或者指定return表达式
    run loop@{
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@loop // 从传入 run 的 lambda 表达式非局部返回
            print(it)
        }
    }
    print(" done with nested loop")// 最后结果就是  12 done with nested loop

    //调用某对象的also函数，则该对象为函数的参数。在函数块内可以通过 it 指代该对象。返回值为该对象自己。
    val a = "string".also {
        println(it)
    }
    println(a)
}

fun <T> otherCheck(body: ()-> T){
    //测试一次
}

/**
 * 超类调用
 */
open class Foo {
    open fun f() { println("Foo.f()") }
    open val x: Int get() = 1
}
class Bar1 : Foo() {
    override fun f() {
        //使用 super 关键字调用其超类的函数与属性访问器的实现,下面尖括号可以指定父类，比如还有一个父接口也有一个f方法
        super<Foo>.f()
        println("Bar1.f()")
    }

    override val x: Int get() = super.x + 1
}
class Bar2 : Foo() {
    override fun f() { /* …… */ }
    override val x: Int get() = 0

    //内部类中访问外部类的超类，可以通过由外部类名限定的 super 关键字来实现：super@Outer
    inner class Baz {
        fun g() {
            super@Bar2.f() // 调用 Foo 实现的 f()
            println(super@Bar2.x) // 使用 Foo 实现的 x 的 getter
        }
    }
}

//延迟加载
class Lateinit {
    lateinit var lateinitVar: String
    fun initializationLogic() {
        println("isInitialized before assignment: " + this::lateinitVar.isInitialized)
        lateinitVar = "value"
        println("isInitialized after assignment: " + this::lateinitVar.isInitialized)
    }
}

//类型智能转换
fun demo(x: Any) {
    if (x is String) {
        print(x.length) // x 自动转换为字符串
    }
    // `||` 右侧的 x 自动转换为字符串
    if (x !is String || x.length == 0) return

    // `&&` 右侧的 x 自动转换为字符串
    if (x is String && x.length > 0) {
        print(x.length) // x 自动转换为字符串
    }
    when (x) {
        is Int -> print(x + 1)
        is String -> print(x.length + 1)
        is IntArray -> print(x.sum())
    }
}
