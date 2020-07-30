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
    @SerializedName("confCase")
    val confCase: String,
    @SerializedName("confCaseRate")
    val confCaseRate: String,
    @SerializedName("createDt")
    val createDt: String,
    @SerializedName("criticalRate")
    val criticlaRate: String,
    @SerializedName("death")
    val death: Int,
    @SerializedName("deathRate")
    val deathRate: String,
    @SerializedName("gubun")
    val gubun: String,
    @SerializedName("updateDt")
    val updateDt: String,
    @SerializedName("seq")
    val seq: Int,
    var longitude:Double? = null,
    var latitude:Double? = null
)
