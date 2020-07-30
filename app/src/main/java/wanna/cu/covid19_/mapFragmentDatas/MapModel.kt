package wanna.cu.covid19_.mapFragmentDatas

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import wanna.cu.covid19_.sexFragmentDatas.SexAge
import wanna.cu.covid19_.sexFragmentDatas.SexAgeModel
import wanna.cu.covid19_.sexFragmentDatas.SexAgeRetrofitService
import wanna.cu.covid19_.sexFragmentDatas.SexAgeTop
import wanna.cu.covid19_.sidoFragmentDatas.DataPresenter
import java.lang.StringBuilder
import java.net.URLDecoder
import java.time.LocalDate

class MapModel private constructor(val onMapDataFetchedListener : OnMapDataFetchedListener){
    var mapDatas = ArrayList<MapData>()

    companion object {
        const val TAG = "SexAgeModel 디버깅"
        private var instance: MapModel? = null

        @JvmStatic
        fun newInstance(onMapDataFetchedListener : OnMapDataFetchedListener) =
            instance ?: synchronized(this) {
                instance ?: MapModel(onMapDataFetchedListener).also {
                    instance = it
                }
            }

    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun doMapDataRetrofit(){
        val privateKey = URLDecoder.decode(
            "EucTlJ77vKNrNliw9L6V8tLn8gQuFNogCmvh%2FlUQ7c5Rnstk0AL%2BTFMIrGFYEwbIMJE9Nw6%2BRU09ydd40wfKeg%3D%3D",
            "utf-8"
        )

        val mapDataRetrofit = Retrofit.Builder()
            .baseUrl("http://openapi.data.go.kr/openapi/service/rest/Covid19/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = mapDataRetrofit.create(MapDataRetrofitService::class.java)

        val call = service.getSido(
            serviceKey = privateKey,
            type = "json",
            pageNo = 1,
            numOfRows = 10,
            startCreateDt = getToday(),
            endCreateDt = getToday()
        )
        call.enqueue(object : Callback<MapDataTop> {
            override fun onFailure(call: Call<MapDataTop>, t: Throwable) {
                Log.d(TAG, "onFailure called")
                onMapDataFetchedListener.onFailed()
            }

            override fun onResponse(
                call: Call<MapDataTop>,
                response: Response<MapDataTop>
            ) {
                mapDatas = response.body()?.mapDataResponse?.mapDataBody?.mapDataItems?.item as ArrayList<MapData>
                Log.d(TAG, "onResponse called, mapDatas = ${mapDatas}")
                onMapDataFetchedListener.onFetched()
            }

        })
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun getToday(): String {
        var now = LocalDate.now()
        val tmp = now.toString().split('-')

        val sb = StringBuilder()
        tmp.forEach() {
            sb.append(it)
        }

        Log.d(DataPresenter.TAG, sb.toString())
        return sb.toString()
    }
    interface OnMapDataFetchedListener{
        fun onFetched()

        fun onFailed()
    }
}