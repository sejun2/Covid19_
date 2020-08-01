package wanna.cu.covid19_.sexFragmentDatas

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import wanna.cu.covid19_.sidoFragmentDatas.DataPresenter
import java.lang.StringBuilder
import java.time.LocalDate

class SexAgePresenter private constructor(val view : SexAgeContract.SexFragmentView) : SexAgeContract.SexPresenter, SexAgeModel.OnSexAgeDataFetchedListener{
    val sexAgeModel = SexAgeModel.newInstance(this)
    var sexAges = ArrayList<SexAge>()
    companion object {
        const val TAG = "SexPresenter 디버깅"
        private var instance: SexAgePresenter? = null

    @JvmStatic
    fun newInstance(view: SexAgeContract.SexFragmentView) =
        instance ?: synchronized(this) {
            instance ?: SexAgePresenter(view).also { instance = it }
        }
}
    @RequiresApi(Build.VERSION_CODES.O)
    override fun getSexAgesFromSexAgeModel() {
        sexAgeModel.doSexAgeRetrofit(getToday())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun setSexAgesToSexAgeFragment(){
        getSexAgesFromSexAgeModel()
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
//OnSexDataFetchedListener Impl
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onFetched() {
        sexAges = sexAgeModel.sexAges
        view.setSexAges(sexAges)
    }

    override fun onFailed() {
        //fail
    }

}