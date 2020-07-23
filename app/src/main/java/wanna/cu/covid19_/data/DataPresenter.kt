package wanna.cu.covid19_.data

import android.content.Context
import android.os.AsyncTask
import android.os.Build
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import wanna.cu.covid19_.mainFragments.SidoFragment
import java.lang.StringBuilder
import java.net.URLDecoder
import java.net.URLEncoder
import java.time.LocalDate
import javax.xml.bind.JAXBContextFactory



class DataPresenter private constructor(val view: DataContract.FragmentView) : DataContract.Presenter {

    companion object {
        const val TAG = "디버깅"
        private var instance: DataPresenter? = null

        @JvmStatic
        fun newInstance(view: DataContract.FragmentView) =
            instance ?: synchronized(this) {
                instance ?: DataPresenter(view).also { instance = it }
            }
    }

    var cities: ArrayList<Sido> = ArrayList<Sido>()


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
            }

            override fun onResponse(
                call: Call<Top>,
                response: Response<Top>
            ) {
                cities = response.body()?.response?.body?.items?.sido as ArrayList<Sido>
                clarifyCities(cities)
                view.setSidos(cities)
            }

        })
    }

    fun clarifyCities(tmp : ArrayList<Sido>){
        if(!tmp.isEmpty()){
           tmp.removeIf { it.gubun.equals("검역") || it.gubun.equals("합계") }
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun getToday(): String {
        var now = LocalDate.now()
        val tmp = now.toString().split('-')

        val sb = StringBuilder()
        tmp.forEach() {
        sb.append(it)
    }

    Log.d(TAG, sb.toString())
    return sb.toString()
    }





}