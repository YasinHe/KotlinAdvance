fun main(args: Array<String>) {
    class A { // 隐式标签 @A
        inner class B { // 隐式标签 @B(内部类)
            fun Int.foo() { // 隐式标签 @foo
                val a = this@A // A 的 this
                val b = this@B // B 的 this

                val c = this // foo() 的接收者，一个 Int
                val c1 = this@foo // foo() 的接收者，一个 Int

                val funLit = lambda@ fun String.() {
                    val d = this // funLit 的接收者
                    //-----------------------------------------------------------------------------
                    val lambda = fun (x:Int,some:(s:String)->Int):Int{
                        return some(d)+x
                    }
                    var length = lambda(1) {
                        it.length
                    }

                    val lambda2 = fun (some:String.(s:String)->Int){
                        System.out.println("length2:${this.some(this)}")
                    }
                    lambda2{
                        s:String
                        ->
                        (this+s).length
                    }

                    val lambda3:(String,String)->String = {
                        s: String, s1: String ->
                        s+s1
                    }
                    System.out.println("lambda3:${lambda3(d,d)}")
                    //------------------------------------------------------------------------------
                    System.out.println("length:$length")
                }
                val funLit2 = { s: String ->
                    // foo() 的接收者，因为它包含的 lambda 表达式
                    // 没有任何接收者
                    val d1 = this
                    s.funLit()
                }
                funLit2("a")
            }
            fun general(x:Int){
                x.foo()
            }
        }
    }

    val innerB: A.B = A().B()
    innerB.general(1)
}
