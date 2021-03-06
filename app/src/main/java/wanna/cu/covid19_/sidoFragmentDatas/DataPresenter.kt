package wanna.cu.covid19_.sidoFragmentDatas

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.lang.StringBuilder
import java.time.LocalDate


class DataPresenter private constructor(val view: DataContract.FragmentView) : DataContract.Presenter, SidoModel.OnDataFetchedListener{
    var sidos: ArrayList<Sido> = ArrayList<Sido>()

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
        sidoModel.doRetrofit(getToday())
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
    override fun onFailed() {

    }


}