//kotlin单例
//Singleton.getInstance()
class Singleton private constructor() {
    private object mHolder {
        val INSTANCE = Singleton()
    }
    //companion同行,同伴对象(类似于java的静态成员(实际上是实例成员)，kotlin没有static) 这里的名字Factory可以省略，省略之后默认名为Companion
    //在 JVM 平台，如果使用 @JvmStatic 注解，你可以将伴生对象的成员生成为真正的静态方法和字段
    companion object {
        const val CONSTANT = "constant"//常量，相当于java的static final ，必须在object内
        fun getInstance(): Singleton {
            return mHolder.INSTANCE
        }
    }
}