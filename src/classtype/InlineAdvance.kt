package classtype

import java.util.concurrent.locks.Lock

/**
 * 为什么使用内联？
 * 使用高阶函数都会带来性能损失，因为每个函数都是对象，并且会捕获一个闭包
 * 在函数体内访问变量，所以开销了内存和时间
 * 而内联的lambda可以消除他们的影响
 */
fun main(args: Array<String>) {
    async{
        //doSth,这里挂起一个协程，一直到计算完成返回结果
    }
}

/**
 * 挂起函数
 * 挂起函数和普通函数一样获得参数和返回值，他们用来处理异步任务
 * 而不同于阻塞，他没有多少代价
 * （他完全通过编译技术来实现，不需要VM或者OS端支持，挂起代码会转换为状态机）
 * 状态机：状态机由状态寄存器和组合逻辑电路构成,能够根据控制信号按照预先设定的状态进行状态转移
 */
suspend fun doSomething(){

}
//比如下面这个就是kotlin异步任务
fun <T> async(block: suspend () -> T){

}