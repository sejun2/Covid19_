package wanna.cu.covid19_.sidoFragmentDatas

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import wanna.cu.covid19_.mapFragmentDatas.MapModel
import java.lang.StringBuilder
import java.net.URLDecoder
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class SidoModel private constructor(val onDataFetchedListener: OnDataFetchedListener) {
    var sidos: ArrayList<Sido> = ArrayList<Sido>()
var count = 0
    companion object {
        const val TAG = "SidoMoel 디버깅"
        private var instance: SidoModel? = null

        @JvmStatic
        fun newInstance(onDataFetchedListener: OnDataFetchedListener) =
            instance ?: synchronized(this) {
                instance ?: SidoModel(onDataFetchedListener = onDataFetchedListener).also {
                    instance = it
                }
            }

    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun getTodayMinusOne(): String {
        Log.d(MapModel.TAG, "getTodayMinusOne()")
        var now = LocalDate.now().minusDays(1)

        var resultformat = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"))

        Log.d(MapModel.TAG, resultformat)

        return resultformat
    }
    //도시정보를 받아와서 sidos에 저장
    @RequiresApi(Build.VERSION_CODES.O)
    fun doRetrofit(day : String) {
        val privateKey = URLDecoder.decode(
            "EucTlJ77vKNrNliw9L6V8tLn8gQuFNogCmvh%2FlUQ7c5Rnstk0AL%2BTFMIrGFYEwbIMJE9Nw6%2BRU09ydd40wfKeg%3D%3D",
            "utf-8"
        )

        val retrofit = Retrofit.Builder()
            .baseUrl("http://openapi.data.go.kr/openapi/service/rest/Covid19/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(RetrofitService::class.java)

        val call = service.getSido(
            serviceKey = privateKey,
            type = "json",
            pageNo = 1,
            numOfRows = 10,
            startCreateDt = day,
            endCreateDt = day
        )
        call.enqueue(object : Callback<Top> {
            override fun onFailure(call: Call<Top>, t: Throwable) {
                Log.d(TAG, "onFailure called")
                onDataFetchedListener.onFailed()

                    doRetrofit(getTodayMinusOne())

            }

            override fun onResponse(
                call: Call<Top>,
                response: Response<Top>
            ) {
                Log.d(TAG, "onResponse called, sidos = ${sidos}")
                sidos = response.body()?.response?.body?.items?.sido as ArrayList<Sido>
                onDataFetchedListener.onFetched()
            }

        })
    }



    interface OnDataFetchedListener {
        fun onFetched()

        fun onFailed()
    }
}
