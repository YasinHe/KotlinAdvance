//kotlin单例
class Singleton private constructor() {
    public var value: Singleton? = null

    private object mHolder {
        val INSTANCE = Singleton()
    }
    //companion同行,同伴对象(类似于java的静态成员，kotlin没有static)
    companion object Factory {
        fun getInstance(): Singleton {
            return mHolder.INSTANCE
        }
    }
}