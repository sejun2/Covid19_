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
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.android.synthetic.main.fragment_sexage.*
import kotlinx.android.synthetic.main.fragment_sido.*
import net.daum.mf.map.api.MapView
import org.angmarch.views.NiceSpinner
import org.angmarch.views.OnSpinnerItemSelectedListener
import wanna.cu.covid19_.R
import wanna.cu.covid19_.sexFragmentDatas.SexAge
import wanna.cu.covid19_.sexFragmentDatas.SexAgeContract
import wanna.cu.covid19_.sexFragmentDatas.SexAgePresenter
import wanna.cu.covid19_.sidoFragmentDatas.Sido


class MapFragment private constructor() : Fragment() {

    val mapView:MapView by lazy{
        MapView(this.context)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView_container.addView(mapView)

    }

    companion object {
        private var instance: MapFragment? = null
        const val TAG = "MapFragment 디버깅"
        @JvmStatic
        fun newInstance() =
            instance ?: synchronized(this) {
                instance
                    ?: MapFragment()
                        .also { instance = it }
            }
    }

}
