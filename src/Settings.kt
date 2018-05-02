//使用object关键字替代class关键字就可以声明一个单例对象
object Settings {
    /**
     *
        位于顶层或者是 object 的一个成员
        用 String 或原生类型 值初始化
        没有自定义 getter
        const 修饰符标记为 编译期常量
     */
    const val PATH_USER = "users"
}