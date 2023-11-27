import dev.icerock.moko.resources.StringResource

expect class StringUtils {
    fun get(id:StringResource,args:List<Any>):String
}