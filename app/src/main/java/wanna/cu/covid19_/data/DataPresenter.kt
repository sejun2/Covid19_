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



class DataPresenter private constructor(val view: DataContract.FragmentView) : DataContract.Presenter, SidoModel.OnDataFetchedListener{
    var sidos: ArrayList<Sido> = ArrayList<Sido>()
    var cities = ArrayList<String>()
    val sidoModel = SidoModel.newInstance(this)
    companion object {
        const val TAG = "디버깅"
        private var instance: DataPresenter? = null

        @JvmStatic
        fun newInstance(view: DataContract.FragmentView) =
            instance ?: synchronized(this) {
                instance ?: DataPresenter(view).also { instance = it }
            }
    }





    fun clarifyCities(tmp : ArrayList<Sido>){
        if(!tmp.isEmpty()){
           tmp.removeIf { it.gubun.equals("검역") || it.gubun.equals("합계") }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getSidosFromSidoModel() {
        sidoModel.doRetrofit()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun setSidosToSidoFragment() : List<Sido>{
        view.setSidos(sidos)
        return sidos
    }
    //SidoModel dataFetchListener
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onFetched() {
        sidos = sidoModel.sidos
        Log.d(TAG, "sidos = ${sidos}")
        setSidosToSidoFragment()
}

    override fun onFailed() {
        TODO("Not yet implemented")
    }


}