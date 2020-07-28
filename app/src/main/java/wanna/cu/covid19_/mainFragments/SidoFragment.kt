package wanna.cu.covid19_.mainFragments

import android.graphics.Color
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
import cl.jesualex.stooltip.Position
import cl.jesualex.stooltip.Tooltip
import kotlinx.android.synthetic.main.fragment_sido.*
import org.angmarch.views.NiceSpinner
import org.angmarch.views.OnSpinnerItemSelectedListener
import wanna.cu.covid19_.R
import wanna.cu.covid19_.sidoFragmentDatas.DataContract
import wanna.cu.covid19_.sidoFragmentDatas.DataPresenter
import wanna.cu.covid19_.sidoFragmentDatas.Sido


class SidoFragment private constructor() : Fragment(), DataContract.FragmentView {

    val dataPresenter by lazy {
        DataPresenter.newInstance(this)
    }
    var sidos = ArrayList<Sido>()
    var cities = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    fun clearLists(){
        sidos = ArrayList<Sido>()
        cities = ArrayList<String>()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sido, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clearLists()
        dataPresenter.getSidosFromSidoModel()
        Tooltip.on(niceSpinner_sidoFragment)
            .text(R.string.tooltip_spinner)
            .color(resources.getColor(android.R.color.black))
            .border(Color.BLACK, 2f)
            .clickToHide(true)
            .corner(5)
            .position(Position.BOTTOM)
            .show(3000)

}

    companion object {
        const val TAG = "SidoFragment 디버깅"
        private var instance: SidoFragment? = null

        @JvmStatic
        fun newInstance() =
            instance ?: synchronized(this) {
                instance ?: SidoFragment().also { instance = it }
            }
    }

    fun setCnts() {
        val tmp = niceSpinner_sidoFragment.selectedItem.toString()
        val x = getSpecificSido(tmp)
        if (x != null) {
            defCnt_textView_sidoFragment.text = x.defCnt.toString()
            deathCnt_textView_sidoFragment.text = x.deathCnt.toString()
            isollingClearCnt_textView_sidoFragment.text = x.isolClearCnt.toString()
            isollingCnt_textView_sidoFragment.text = x.isolIngCnt.toString()
        }
    }

    fun getSpecificSido(name: String): Sido? {
        var tmp: Sido? = null
        sidos.forEach {
            if (it.gubun.equals(name)) {
                tmp = it
            }
        }
        return tmp
    }

    /////스피너설정////////
    override fun setSidos(tmp: List<Sido>) {
        Log.d(TAG, "setSidos() : ${tmp.toString()}")
        sidos = tmp as ArrayList<Sido>
        initSpinner(sidos)
    }

    fun getCities(tmp: List<Sido>) {
        Log.d(TAG, "getCities() : ${tmp.toString()}")
        tmp.forEach({
            cities.add(it.gubun)
        })
    }
    override fun initSpinner(tmp: List<Sido>){
        Log.d(TAG, "initNiceSpinner() : ${tmp.toString()}")
        getCities(tmp)
        niceSpinner_sidoFragment.attachDataSource(cities)

        niceSpinner_sidoFragment.setOnSpinnerItemSelectedListener(object : OnSpinnerItemSelectedListener{
            override fun onItemSelected(
                parent: NiceSpinner?,
                view: View?,
                position: Int,
                id: Long
            ) {
                setCnts()
            }

        })


    }
   /* override fun initSpinner(tmp: List<Sido>) {
        Log.d(TAG, "initSpinner() : ${tmp.toString()}")
        getCities(tmp)
        spinner_sidoFragment.adapter = context?.let {
            ArrayAdapter<String>(
                it,
                android.R.layout.simple_spinner_dropdown_item,
                cities
            )
        }
        spinner_sidoFragment.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                setCnts()
            }

        }

    }
    fun setCnts() {
        val tmp = spinner 아이템 세팅
        val x = getSpecificSido(tmp)
        if (x != null) {
            defCnt_textView_sidoFragment.text = x.defCnt.toString()
            deathCnt_textView_sidoFragment.text = x.deathCnt.toString()
            isollingClearCnt_textView_sidoFragment.text = x.isolClearCnt.toString()
            isollingCnt_textView_sidoFragment.text = x.isolIngCnt.toString()
        }
    }
    */
///////////////////////////////////////
}
