package wanna.cu.covid19_.mapFragmentDatas

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import wanna.cu.covid19_.sexFragmentDatas.SexAgeTop
import wanna.cu.covid19_.sidoFragmentDatas.Top

interface MapDataRetrofitService {
    @GET("getCovid19SidoInfStateJson")
    fun getSido(
        @Query("serviceKey") serviceKey: String,
        @Query("_type") type: String,
        @Query("pageNo") pageNo: Int,
        @Query("numOfRows") numOfRows: Int,
        @Query("startCreateDt") startCreateDt: String,
        @Query("endCreateDt") endCreateDt: String
    ): Call<MapDataTop>

}
