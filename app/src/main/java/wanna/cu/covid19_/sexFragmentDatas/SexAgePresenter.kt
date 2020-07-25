package wanna.cu.covid19_.sexFragmentDatas

import android.os.Build
import androidx.annotation.RequiresApi

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
        sexAgeModel.doSexAgeRetrofit()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun setSexAgesToSexAgeFragment(){
        getSexAgesFromSexAgeModel()
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