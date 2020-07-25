package wanna.cu.covid19_.sidoFragmentDatas

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder
import java.net.URLDecoder
import java.time.LocalDate

class SidoModel private constructor(val onDataFetchedListener: OnDataFetchedListener) {
    var sidos: ArrayList<Sido> = ArrayList<Sido>()

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

    //도시정보를 받아와서 sidos에 저장
    @RequiresApi(Build.VERSION_CODES.O)
    fun doRetrofit() {
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
            startCreateDt = getToday(),
            endCreateDt = getToday()
        )
        call.enqueue(object : Callback<Top> {
            override fun onFailure(call: Call<Top>, t: Throwable) {
                Log.d(TAG, "onFailure called")
                onDataFetchedListener.onFailed()
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

    interface OnDataFetchedListener {
        fun onFetched()

        fun onFailed()
    }
}
