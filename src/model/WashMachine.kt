package model

//constructor 是构造器，也可以省略
class WashMachine constructor (var moudle:String,var name:String){
    var moudleValue:String =moudle
    var nameValue:String =name
    fun selectMode(model:Int){
        when(model){
            0-> println("0000")
            1-> println("1111")
            2,3-> println("23333")
            else-> print("other")
        }
    }

    private fun setMotoSpeed(speed:Int){

    }

    operator fun component1(): Any {
        return moudleValue
    }

    operator fun component2(): Any {
        return nameValue
    }
}