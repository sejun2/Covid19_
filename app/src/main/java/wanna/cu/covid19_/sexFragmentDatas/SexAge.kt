package wanna.cu.covid19_.sexFragmentDatas

import android.icu.text.CaseMap
import com.google.gson.annotations.SerializedName

data class SexAgeTop (
    @SerializedName("response")
    var sexResponse : SexAgeResponse
)
data class SexAgeResponse(
    @SerializedName("body")
    var sexBody:SexAgeBody
    )
data class SexAgeBody(
    @SerializedName("items")
    var sexItems : SexAgeItems
)
data class SexAgeItems(
    @SerializedName("item")
    var item : List<SexAge>
)
data class SexAge(
    @SerializedName("confCase")
    val confCase : String,
    @SerializedName("confCaseRate")
    val confCaseRate :String,
    @SerializedName("createDt")
    val createDt:String,
    @SerializedName("criticalRate")
    val criticlaRate:String,
    @SerializedName("death")
    val death : Int,
    @SerializedName("deathRate")
    val deathRate:String,
    @SerializedName("gubun")
    val gubun :String,
    @SerializedName("updateDt")
    val updateDt:String,
    @SerializedName("seq")
    val seq : Int
)
