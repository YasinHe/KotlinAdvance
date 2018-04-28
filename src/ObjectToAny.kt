import model.DiscoverModel
import sun.rmi.runtime.Log
import java.util.concurrent.locks.Lock

//函数式闭包(方法的返回值可以是一个方法，并且规定该方法的返回值)
fun funcation():()->String{
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
//继承
open class Father{
    var charactor:String = "好人"
    fun action(){
    }
    open fun action2(){
    }
}
class Son:Father(){
    init {
        //初始化只能放在init关键字做初始化，主构造不能包含任何代码
        charactor = "坏人"
    }
    override fun action2(){
    }
}
//抽象
abstract class HuMan(name:String){
    var name = name;
    abstract fun eat()//抽象方法不能有实现
}
//接口
interface IMan {
    fun keke()
}
class Man(name:String):HuMan(name),IMan{
    override fun keke() {
//        TODO("not implemented")
    }
    override fun eat() {
//        TODO("not implemented")
    }
}
//代理，b指挥a做接口需要做的事情(这里的by是创建一个新的Man对象，除非单例)
class OtherMan(name:String):IMan by Man(name){
    override fun keke() {
        //我其实是OtherMan
    }
}
//单例(直接创建在jvm内存之中，并且唯一)
object ThirdMan:IMan{
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

//dsl 领域特定语言(声明式编程)
infix fun List<HuMan>.fount(name:String){
    filter {
        it.name == name
    }.forEach (::println)
    //  ::就是lambda的闭包，同理Math::max等效于(a, b)->Math.max(a, b),s::isEmpty等效于()->s.isEmpty()
    //在java中使用一般是类名加方法名（称为方法引用）  Person::getName == person -> person.getName();
    // () -> new HashMap<>();  ==  HashMap::new
}

//protected：只用于类和接口内 internal如下
internal fun 同一模块访问修饰符(){

}

fun main(args: Array<String>) {
    var rect01 = Rect(20,20)
    println("高度:${rect01.height}")
    var son = Son()
    println("儿子：${son.charactor}")
    var man1 = Man("Ace")
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

    //重写set，get
//    private var strings:String?=null
//        private get() = ""
//        private set

    //lateinit延迟加载，不能初始化的
    lateinit var string:String
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

fun <T> otherCheck(body: ()-> T){
    //测试一次
}