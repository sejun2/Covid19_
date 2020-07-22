package wanna.cu.covid19_.mainFragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.fragment_sido.*
import wanna.cu.covid19_.R
import wanna.cu.covid19_.data.DataContract
import wanna.cu.covid19_.data.DataPresenter
import wanna.cu.covid19_.data.Sido


class SidoFragment : Fragment(), DataContract.FragmentView {
    val dataPresenter by lazy {
        DataPresenter.newInstance(this)
    }
    var sidos = ArrayList<Sido>()
    var cities = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        dataPresenter.doRetrofit()


    }

    companion object {
        private var instance: SidoFragment? = null

        @JvmStatic
        fun newInstance() =
            instance ?: synchronized(this) {
                instance ?: SidoFragment().also { instance = it }
            }
    }

/////스피너설정////////
    override fun setSidos(tmp : List<Sido>) {
        sidos = tmp as ArrayList<Sido>
        initSpinner(sidos)
    }
    fun getCities(tmp : List<Sido>){
        tmp.forEach({
            cities.add(it.gubun)
        })
    }
        fun initSpinner(tmp : List<Sido>){
            getCities(tmp)
            spinner_sidoFragment.adapter = context?.let { ArrayAdapter<String>(it, android.R.layout.simple_spinner_dropdown_item, cities) }
    }
///////////////////////////////////////
}
