package classtype

//每个枚举常量都是一个对象。枚举常量用逗号分隔。
enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

//初始化枚举
enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

//枚举常量的匿名实现，枚举常量也可以被定义在他们自己的匿名类里
enum class ProtocolState {
    WAITING {
        //只要有括号  就必须重写这个方法，
        override fun signal(): ProtocolState {
            println("ok,It's singnal")
            return this
        }
//        override fun signal() = WAITING
    },

    TALKING {
        override fun signal() = WAITING
    };
    abstract fun signal(): ProtocolState
}

fun main(args: Array<String>) {
    //访问枚举 enumValues<T>() 和 enumValueOf<T>()   val name: String val ordinal: Int  枚举的属性和位置
    printAllValues<Color>() // 输出 RED, GREEN, BLUE
    ProtocolState.WAITING.signal()
}

//reified必须使用inline内联，reified的意思是使具体化，以reified修饰类型后，我们就能够在函数内部使用相关类型了，Java的泛型T是不行的，这点要注意
//比如 inline fun <reified T : View> Activity.findView(id: Int) = findViewById(id) as T；
// 如果不使用内联和具体化，编译器就不能确定有效类型，从而导致报错:  fun <T : View> Activity.findView(id: Int) = findViewById(id) as T
inline fun <reified T : Enum<T>> printAllValues() {
    print(enumValues<T>().joinToString { it.name })//ordinal下标
}
