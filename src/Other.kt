import model.Person
import model.WashMachine
import kotlin.properties.Delegates

fun main(args: Array<String>) {
    //解构声明
    var wodetian: WashMachine = WashMachine("moudle","洗衣机")
    val (moudleValue, nameValue) = wodetian//需要重写对象目标的component
    val moudleValue2 = wodetian.component1()
    val nameValue2 = wodetian.component2()
    System.out.print("wodetian:$moudleValue$nameValue$moudleValue2$nameValue2")
    var map:MutableMap<String,String> = mutableMapOf()
    map.put("1","a")
    map.put("2","b")
    map.put("3","c")
    for((key,vaule)in map){
        System.out.print("key$key"+"value$vaule")
    }
    //map的各种扩展
    map.mapValues { entry -> "${entry.value}!" }
    map.mapValues { (key, value) -> "$value!" }
    map.mapValues { (_, value: String) -> "$value!" }
}

private var mPhotoUrl: String? = null
fun uploadClicked() {
//    uploadPhoto(mPhotoUrl)
//    uploadPhoto(mPhotoUrl!!)
    //上面第一行是会报错的，因为空，第二行也不好，使用let很容易解决
    mPhotoUrl?.let { uploadPhoto(it) }
}
fun uploadPhoto(s:String){
}
//基本数据类型的委托，避免空
private var mNumber: Int by Delegates.notNull<Int>()

/**
 * 内置函数 requireNotNull 或者 checkNotNull 和一些附带的异常消息易于调
 */
//uploadPhoto(requireNotNull(intent.getStringExtra("PHOTO_URL"), { "Activity parameter 'PHOTO_URL' is missing" }))