package classtype

//密封类
//一个密封类是自身抽象的，它不能直接实例化并可以有抽象（abstract）成员,密封类不允许有非-private 构造函数
sealed class Expr

//非常类似枚举的概念。所不同的是，在枚举中，我们每个类型只有一个对象；而在密封类中，同一个类可以拥有几个对象
data class Const(val number: Double) : Expr()
data class Sum(val e1: Expr, val e2: Expr) : Expr()
object NotANumber : Expr()

//使用密封类的关键好处在于使用 when 表达式 的时候，如果能够验证语句覆盖了所有情况，就不需要为该语句再添加一个 else 子句了
fun eval(expr: Expr): Double = when(expr) {
    is Const -> expr.number
    is Sum -> eval(expr.e1) + eval(expr.e2)//这里等于递归自己实现相加
    NotANumber -> Double.NaN
// 不再需要 `else` 子句，因为我们已经覆盖了所有的情况
}

//实例2--------------------密封类作为声明时，但凡使用该声明的都认为是同一组织的；密封类作为同一类中使用时，when必须写出所有可能--------------------------------
sealed class Operation {
    class Add(val value: Int) : Operation()
    class Substract(val value: Int) : Operation()
    class Multiply(val value: Int) : Operation()
    class Divide(val value: Int) : Operation()
    object Increment : Operation()
    object Decrement : Operation()
 }


fun execute(x: Int, op: Operation) = when (op) {
    is Operation.Add -> x + op.value
    is Operation.Substract -> x - op.value
    is Operation.Multiply -> x * op.value
    is Operation.Divide -> x / op.value
    //如果下面这两个不去实现，when表达式会报错 这里通过加一个else 默认返回解决，实际开发必须要去实现所有情况
//    classtype.Operation.Increment -> x + 1
//    classtype.Operation.Decrement -> x - 1
    else -> ""
}

fun main(args: Array<String>) {
    eval(Sum(Const(5.0),Const(5.0)))
}

