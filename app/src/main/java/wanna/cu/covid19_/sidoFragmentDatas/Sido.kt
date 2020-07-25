package wanna.cu.covid19_.sidoFragmentDatas

import com.google.gson.annotations.SerializedName
data class Top(
    @SerializedName("response")
    var response:Response
)
data class Response(
    @SerializedName("body")
    var body:Body
)
data class Body(
    @SerializedName("items")
    var items:Items
)
data class Items(
    @SerializedName("item")
    var sido:List<Sido>
)

data class Sido(
    @SerializedName("createDt")
    val createDt:String,
    @SerializedName("deathCnt")
    val deathCnt:Int,
    @SerializedName("defCnt")
    val defCnt:Int,
    @SerializedName("gubun")
    val gubun:String,
    @SerializedName("incDec")
    val incDec:Int,
    @SerializedName("isolClearCnt")
    val isolClearCnt:Int,
    @SerializedName("isolIngCnt")
    val isolIngCnt:Int,
    @SerializedName("stdDay")
    val stdDay:String
)
