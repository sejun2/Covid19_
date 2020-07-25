package wanna.cu.covid19_.sexFragmentDatas

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface SexAgeRetrofitService {
    @GET("getCovid19GenAgeCaseInfJson")
    fun getSido(
        @Query("serviceKey") serviceKey: String,
        @Query("_type") type: String,
        @Query("pageNo") pageNo: Int,
        @Query("numOfRows") numOfRows: Int,
        @Query("startCreateDt") startCreateDt: String,
        @Query("endCreateDt") endCreateDt: String
    ): Call<SexAgeTop>

}