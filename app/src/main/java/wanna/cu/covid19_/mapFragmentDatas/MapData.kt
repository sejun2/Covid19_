package wanna.cu.covid19_.mapFragmentDatas

import com.google.gson.annotations.SerializedName

data class MapDataTop(
    @SerializedName("response")
    var mapDataResponse: MapDataResponse
)

data class MapDataResponse(
    @SerializedName("body")
    var mapDataBody: MapDataBody
)

data class MapDataBody(
    @SerializedName("items")
    var mapDataItems: MapDataItems
)

data class MapDataItems(
    @SerializedName("item")
    var item: List<MapData>
)

data class MapData(
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
    val stdDay:String,
    var longitude:Double? = null,
    var latitude:Double? = null
)
