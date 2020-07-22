package wanna.cu.covid19_.data

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @GET("getCovid19SidoInfStateJson")
    fun getSido(
            @Query("serviceKey") serviceKey: String,
            @Query("_type") type: String,
            @Query("pageNo") pageNo: Int,
            @Query("numOfRows") numOfRows: Int,
            @Query("startCreateDt") startCreateDt: String,
            @Query("endCreateDt") endCreateDt: String
    ): Call<Top>


}