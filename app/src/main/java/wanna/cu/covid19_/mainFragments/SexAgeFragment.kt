package wanna.cu.covid19_.mainFragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.fragment_sexage.*
import kotlinx.android.synthetic.main.fragment_sido.*
import wanna.cu.covid19_.R
import wanna.cu.covid19_.sexFragmentDatas.SexAge
import wanna.cu.covid19_.sexFragmentDatas.SexAgeContract
import wanna.cu.covid19_.sexFragmentDatas.SexAgePresenter
import wanna.cu.covid19_.sidoFragmentDatas.Sido


class SexAgeFragment private constructor() : Fragment(), SexAgeContract.SexFragmentView {
    var sexAges = ArrayList<SexAge>()
    var gubun = ArrayList<String>()
    val sexAgePresenter: SexAgePresenter = SexAgePresenter.newInstance(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    fun clearLists(){
        sexAges = ArrayList<SexAge>()
        gubun = ArrayList<String>()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sexage, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clearLists()
        sexAgePresenter.setSexAgesToSexAgeFragment()
    }

    companion object {
        private var instance: SexAgeFragment? = null
        const val TAG = "SexAgeFragment 디버깅"
        @JvmStatic
        fun newInstance() =
            instance ?: synchronized(this) {
                instance
                    ?: SexAgeFragment()
                        .also { instance = it }
            }
    }

    fun getSpecificSexAge(name: String): SexAge? {
        var tmp: SexAge? = null
        sexAges.forEach {
            if (it.gubun.equals(name)) {
                tmp = it
            }
        }
        return tmp
    }

    fun setCnts() {
        val tmp = spinner_sexAgeFragment.selectedItem.toString()
        val x = getSpecificSexAge(tmp)
        if (x != null) {
            confCaseRate_textView_sexAgeFragment.text = x.confCaseRate+"%"
            confCase_textView_sexAgeFragment.text = x.confCase+"명"
            deathCnt_textView_sexAgeFragment.text = "${x.death}명"
            deathRate_textView_sexAgeFragment.text = x.deathRate+"%"
        }
    }

    /////스피너설정////////
    override fun setSexAges(tmp: List<SexAge>) {
        Log.d(TAG, "setSexAges() : ${tmp.toString()}")
        sexAges = tmp as ArrayList<SexAge>
        initSpinner(sexAges)
    }

    fun getSexAges(tmp: List<SexAge>) {
        Log.d(TAG, "getGubun() : ${tmp.toString()}")
        tmp.forEach({
            gubun.add(it.gubun)
        })
    }

    override fun initSpinner(tmp: List<SexAge>) {
        Log.d(SidoFragment.TAG, "initSpinner() : ${tmp.toString()}")
        getSexAges(tmp)
        spinner_sexAgeFragment.adapter = context?.let {
            ArrayAdapter<String>(
                it,
                android.R.layout.simple_spinner_dropdown_item,
                gubun
            )
        }
        spinner_sexAgeFragment.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                //Do Nothing
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                setCnts()
            }

        }

    }
}