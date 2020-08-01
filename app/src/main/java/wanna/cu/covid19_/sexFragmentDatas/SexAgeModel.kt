package wanna.cu.covid19_.sexFragmentDatas

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import wanna.cu.covid19_.mapFragmentDatas.MapModel
import wanna.cu.covid19_.sidoFragmentDatas.*
import java.lang.StringBuilder
import java.net.URLDecoder
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class SexAgeModel private constructor(val onSexAgeDataFetchedListener : OnSexAgeDataFetchedListener){
    var sexAges = ArrayList<SexAge>()
    var count = 0
    companion object {
        const val TAG = "SexAgeModel 디버깅"
        private var instance: SexAgeModel? = null

        @JvmStatic
        fun newInstance(onSexDataFetchedListener: OnSexAgeDataFetchedListener) =
            instance ?: synchronized(this) {
                instance ?: SexAgeModel(onSexDataFetchedListener).also {
                    instance = it
                }
            }

    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun doSexAgeRetrofit(day : String){
        val privateKey = URLDecoder.decode(
            "EucTlJ77vKNrNliw9L6V8tLn8gQuFNogCmvh%2FlUQ7c5Rnstk0AL%2BTFMIrGFYEwbIMJE9Nw6%2BRU09ydd40wfKeg%3D%3D",
            "utf-8"
        )

        val sexAgeRetrofit = Retrofit.Builder()
            .baseUrl("http://openapi.data.go.kr/openapi/service/rest/Covid19/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = sexAgeRetrofit.create(SexAgeRetrofitService::class.java)

        val call = service.getSido(
            serviceKey = privateKey,
            type = "json",
            pageNo = 1,
            numOfRows = 10,
            startCreateDt = day,
            endCreateDt = day
        )
        call.enqueue(object : Callback<SexAgeTop> {
            override fun onFailure(call: Call<SexAgeTop>, t: Throwable) {
                Log.d(TAG, "onFailure called")
                onSexAgeDataFetchedListener.onFailed()

                    doSexAgeRetrofit(getTodayMinusOne())

            }

            override fun onResponse(
                call: Call<SexAgeTop>,
                response: Response<SexAgeTop>
            ) {
                Log.d(TAG, "onResponse called, sexAges = ${sexAges}")
                sexAges = response.body()?.sexResponse?.sexBody?.sexItems?.item as ArrayList<SexAge>
                onSexAgeDataFetchedListener.onFetched()
            }

        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getTodayMinusOne(): String {
        Log.d(MapModel.TAG, "getTodayMinusOne()")
        var now = LocalDate.now().minusDays(1)

        var resultformat = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"))

        Log.d(MapModel.TAG, resultformat)

        return resultformat
    }
    interface OnSexAgeDataFetchedListener{
        fun onFetched()

        fun onFailed()
    }
}