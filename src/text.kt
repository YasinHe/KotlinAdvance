
fun main(args: Array<String>) {
    //var 变量,读写  val常量，只读
    var result = sum(1, 2, 3)
    println("heyingxin:" + result)
    //遍历
    lists("1", "2", "3")
    //不可变遍历
    val sumLambda: (Int, Int) -> Int = { x, y -> x + y }
    println(sumLambda(1, 2))  // 输出 3

    //数组1到100
    //  ()表示开区间，就是不包含两端点的  []闭区间表示包含两端的点
    var array = 1..100
    array = 1 until 100// [1,100)
    for (index in array) {
        println("index=$index")
    }

    val a: Int = 1
    val b = 1       // 系统自动推断变量类型为Int
    val c: Int      // 如果不在声明时初始化则必须提供变量类型
    c = 1           // 明确赋值
    var x = 5        // 系统自动推断变量类型为Int
    x += 1           // 变量可修改

    /**  字符串
     *  $ 表示一个变量名或者变量值
    $varName 表示变量值
    ${varName.fun()} 表示变量的方法返回值:
     */
    val s1 = "a的值is$a"
    val s2 = "${s1.replace("is", "was")},原来是$s1"

    //第二个参数的true和false表示的是，是否忽略字符串的大小写
    println(s1.equals(s2, false))
    println(s2)

    //类型后面  加?  表示可为空（不然传参null会报错）
    var age: String? = "23"
    //抛出空指针异常，非空断言运算符（!!）将任何值转换为非空类型，若该值为空则抛出异常
    val ages = age!!.toInt()
    //不做处理返回 null
    val ages1 = age?.toInt()
    //age为空返回-1     如果 ?: 左侧表达式非空，elvis 操作符就返回其左侧表达式，否则返回右侧表达式
    val ages2 = age?.toInt() ?: -1

    /**
    十进制：123
    长整型以大写的 L 结尾：123L
    16 进制以 0x 开头：0x0F
    2 进制以 0b 开头：0b00001011
    注意：8进制不支持
    Doubles 默认写法: 123.5, 123.5e10
    Floats 使用 f 或者 F 后缀：123.5f
     */
    val double = 135.5e10
    val oneMillion = 1_000_000
    val creditCardNumber = 1234_5678_9012_3456L
    val socialSecurityNumber = 999_99_9999L
    val hexBytes = 0xFF_EC_DE_5E
    val bytes = 0b11010010_01101001_10010100_10010010

    // in 就是是不是包含在里面
    fun funcation(): Boolean {
        if (5 in 1..10) {//1..10代表1-10的意思
            return true
        } else {
            return false
        }
    }
    //.. 的 rangeTo 函数辅以 in 和 !in 形成,downTo为反方向 .. ,
    // step就是跳几个执行一次，比如8-1 step3  那就是10 跳3 到5 再到2
    print("循环输出：")
    for (i in 1..4) print(i) // 输出“1234”
    println("\n----------------")
    for (i in 4..1) print(i) // 什么都不输出
    print("设置步长：")
    for (i in 1..4 step 2) print(i) // 输出“13”
    println("\n----------------")
    print("使用 downTo：")
    for (i in 4 downTo 1 step 2) print(i) // 输出“42”
    println("\n----------------")
    print("使用 until：")
    // 使用 until 函数排除结束元素
    for (i in 1 until 4) {   // i in [1, 4) 排除了 4
        print(i)
    }
    println("\n----------------")
    //比较值
    compareValue()
    /** 类型转换
     *  toByte(): Byte
        toShort(): Short
        toInt(): Int
        toLong(): Long
        toFloat(): Float
        toDouble(): Double
        toChar(): Char
     */
    /** 位运算
     *  || – 短路逻辑或
        && – 短路逻辑与
        ! - 逻辑非
     *  shl(bits) – 左移位 (Java’s <<)
        shr(bits) – 右移位 (Java’s >>)
        ushr(bits) – 无符号右移位 (Java’s >>>)
        and(bits) – 与
        or(bits) – 或
        xor(bits) – 异或
        inv() – 反向
     */

    //[1,2,3]
    val ass = arrayOf(1, 2, 3)
    //[0,2,4]
    val bs = Array(3, { i -> (i * 2) })
    //读取数组内容
    println(ass[0])    // 输出结果：1
    println(bs[1])    // 输出结果：2
    val xs: IntArray = intArrayOf(1, 2, 3)
    xs[0] = xs[1] + xs[2]
    ass?.let {
        for (i in it){
            println(ass[i])
        }
    }

    val text = """
    |多行字符串
    |菜鸟教程
    |多行字符串
    |Runoob
    """
    println(text)
    println("前置空格删除了\n"+text.trimMargin())// 前置空格删除了
    val str = "$text.length is ${text.length}" // 求值结果为 "runoob.length is 6"
    println(str)
    val price = """
    ${'$'}9.99
    """
    println(price)  // 求值结果为 $9.99

    //条件判断
    val max = if (a > b) {
        print("Choose a")
        a
    } else {
        print("Choose b")
        b
    }

    //相当于switch- case default
    when (x) {
        0, 1 -> println("x == 0 or x == 1")
        else -> println("otherwise")
    }
    when (x) {
        1 -> println("x == 1")
        2 -> println("x == 2")
        else -> { // 注意这个块
            println("x 不是 1 ，也不是 2")
        }
    }
    when (x) {
        in 0..10 -> println("x 在该区间范围内")
        else -> println("x 不在该区间范围内")
    }

    var b2 = when (x) {
        0 -> 0  //如果a=0，则b=0
        1 -> 1  //如果a=1，则b=1
        else -> 2 //如果a=其他，则b=2
    }

    //is做类型判断，when sitch case，Any 等同于java的Object
    fun hasPrefix(x:Any) = when(x){
        is String ->{}
        is Int ->{}
        else ->{}
    }

    //遍历
    var items = listOf("apple", "banana", "kiwi")
    for (item in items) {
        println(item)
    }
    for (index in items.indices) {
        println("item at $index is ${items[index]}")
    }
    for((i,e) in items.withIndex()){
        println("$i $e")//i是下标  e是指
    }

    /**
     * map
     */
    var map = java.util.TreeMap<String,String>()
    map["1"] = "111"
    map["2"] = "222"
    map["3"] = "333"
    map["4"] = "444"
    println(map["1"])
    //走while循环
    whileFuncation()
    //具名k值，i和j都有默认值，但是不写报错，通过具名可以不写
    sum(k = 3)
}

/**
 * 默认参数和具名参数
 */
//加法  给ijk指定默认值
fun sum(i :Int = 0,j :Int= 0,k :Int= 0) : Int{
    return i+j+k
}

// :Unit 表示无返回值 vararg可变参数(只能一个个传值，不能直接使用外部Array)
fun lists(vararg lists:String) : Unit{
    for(date in lists){
        //类似于instanceof(判断对象类型)
        if(date is String)
            println(date)
    }
}

fun compareValue(){
    // 三个等号 === 表示比较对象地址，两个 == 表示比较两个值大小
    val a: Int = 10000
    println(a === a) // true，值相等，对象地址相等
    //经过了装箱，创建了两个不同的对象
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a
    //虽然经过了装箱，但是值是相等的，都是10000
    println(boxedA === anotherBoxedA) //  false，值相等，对象地址不一样
    println(boxedA == anotherBoxedA) // true，值相等
}

//Kotlin 中的 Char 不能直接和数字操作，Char 必需是单引号''包含起来的
fun decimalDigitValue(c: Char): Int {
    //in可以表示为在什么什么区间之内
    if (c !in '0'..'9')
        throw IllegalArgumentException("Out of range")
    return c.toInt() - '0'.toInt() // 显式转换为数字
}

fun whileFuncation(){
    println("----while 使用-----")
    var x = 5
    while (x > 0) {
        println( x--)
    }
    println("----do...while 使用-----")
    var y = 5
    do {
        println(y--)
    } while(y>0)

    for (i in 1..10) {
        if (i==3) continue  // i 为 3 时跳过当前循环，继续下一次循环
        println(i)
        if (i>5) break   // i 为 6 时 跳出循环
    }

    //遇到标签就结束
    val ints = arrayOf(0,1,2,3)
    ints.forEach lit@ {
        if (it == 0) return@lit
        print(it)
    }
    ints.forEach {
        if (it == 0) return@forEach
        print(it)
    }
    ints.forEach(fun(value: Int) {
        if (value == 0) return
        print(value)
    })

    //集合
    //第一个这个是易变类型集合add remove contains clear,listof或者mapof都是只读的
    var mutableList: MutableList<Int> = mutableListOf()
    var list: List<Int> = listOf<Int>()
    var set: Set<Int> = setOf<Int>()
    var map: Map<String, Int> = mapOf<String, Int>()

    /**
     * 函数式表达式(变量和函数都是一等公民,类似c，c++的函数指针)
     */
    fun add(x:Int,y:Int):Int{
        return x+y
    }
    fun add2(x:Int,y:Int):Int = x+y
    var add3 = {x:Int,y:Int -> x+y}
    add3(3,5)//也是可以的

    /**
     * 字符串数字转换
     */
    fun conversion(){
        var a = "13"
        var b = 13
        a = b.toString()
        b = a.toInt()
        try{

        }catch (e:java.lang.Exception){

        }
    }

    /**
     * lambda
     */
    fun test(num1 : Int, bool : (Int) -> Boolean) : Int{
        return if (bool(num1)){ num1 } else 0
    }
    //it是在当一个高阶函数中Lambda表达式的参数只有一个的时候可以使用it来使用此参数。it可表示为单个参数的隐式名称，是Kotlin语言约定的
    println(test(10,{it > 5}))
    println(test(4,{it > 5}))
    //可以用下划线(_)表示未使用的参数，表示不处理这个参数
    val map2 = mapOf("key1" to "value1","key2" to "value2","key3" to "value3")
    map2.forEach{
        key , value -> println("$key \t $value")
    }
    map2.forEach{
        _ , value -> println("$value")
    }
    //filter
    val arr = arrayOf(1,3,5,7,9)
    // 过滤掉数组中元素小于5的元素，取其第一个打印。这里的it就表示每一个元素。
    println(arr.filter { it < 5 }.component1())
    //匿名函数
    fun(x : Int , y : Int) : Int = x + y

    /**
     * tailrec修饰在fun前，表示尾递归，正常计算机递归会栈溢出
     * kotlin加了这个优化，可以深度递归(编译器发现尾递归，不就不会保存栈帧，而是去覆盖栈帧)
     * 普通递归只计算一个值，一个值赋值或者计算后继续深入递归，该值不断保存栈
     * res（初始化为1）维护递归层次的深度。这就让我们避免了每次还需要将返回值再乘以n
     * Java并不支持尾递归，因为尾递归实际上是依赖编译器的优化，java可以利用lambda的懒加载来实现
     */
    fun facttail( n:Int,  res:Int):Int
    {
        if (n < 0)
            return 0;
        else if(n == 0)
            return 1;
        else if(n == 1)
            return res;
        else
            return facttail(n - 1, n *res);
    }

    //延迟属性，lazy{} 只能用在val类型, lateinit 只能用在var类型
    //lateinit可以在任何位置初始化并且可以初始化多次。而lazy在第一次被调用时就被初始化
    //延迟加载，到第一次调用才初始化，这也是他的意义所在
    fun calculateString(){
        val p: String by lazy {
            // 计算该字符串
            "wodetian"
        }
    }

    //表达式返回,下面的等价
    fun transform(color: String): Int {
        return when (color) {
            "Red" -> 0
            "Green" -> 1
            "Blue" -> 2
            else -> throw IllegalArgumentException("Invalid color param value")
        }
    }
    fun transform2(color: String): Int = when (color) {
        "Red" -> 0
        "Green" -> 1
        "Blue" -> 2
        else -> throw IllegalArgumentException("Invalid color param value")
    }

    // with,对一个对象实例调用多个方法
    val myTurtle = Turtle()
    with(myTurtle) { // 画一个 100 像素的正方形
        penDown()
        for(i in 1..4) {
            forward(100.0)
            turn(90.0)
        }
        penUp()
    }
}

class Turtle {
    fun penDown(){}
    fun penUp(){}
    fun turn(degrees: Double){}
    fun forward(pixels: Double) {}
}

