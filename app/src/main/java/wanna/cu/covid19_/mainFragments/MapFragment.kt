package wanna.cu.covid19_.mainFragments

import android.graphics.Color
import android.location.Geocoder
import android.os.AsyncTask
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
import net.daum.mf.map.api.*
import org.angmarch.views.NiceSpinner
import org.angmarch.views.OnSpinnerItemSelectedListener
import wanna.cu.covid19_.R
import wanna.cu.covid19_.mapFragmentDatas.MapContract
import wanna.cu.covid19_.mapFragmentDatas.MapData
import wanna.cu.covid19_.mapFragmentDatas.MapPresenter
import wanna.cu.covid19_.sexFragmentDatas.SexAge
import wanna.cu.covid19_.sexFragmentDatas.SexAgeContract
import wanna.cu.covid19_.sexFragmentDatas.SexAgePresenter
import wanna.cu.covid19_.sidoFragmentDatas.Sido
import javax.xml.bind.JAXBElement


class MapFragment private constructor() : Fragment(), MapContract.MapView {
    val mapPresenter by lazy {
        MapPresenter.newInstance(this, context!!)
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapPresenter.getMapDataFromModel()
        mapView.setZoomLevel(9, true);

    }

    override fun onDetach() {
        super.onDetach()
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

    override fun setMarkerToMap(list: List<MapData>) {
        for(i in list){
            if(i.longitude != null) {
                var customMarker = MapPOIItem()
                customMarker.itemName = """
                    ${i.gubun}
                    확진자 : ${i.defCnt}
                    격리중 : ${i.isolIngCnt}
                    사망 : ${i.deathCnt}""".trimIndent()
                customMarker.markerType= MapPOIItem.MarkerType.CustomImage
                customMarker.customImageResourceId = R.drawable.germ
                customMarker.mapPoint = MapPoint.mapPointWithGeoCoord(i.latitude!!, i.longitude!!)
                customMarker.setCustomImageAnchor(0.5f, 1.0f)

                mapView.addPOIItem(customMarker)
            }
        }
    }

    override fun setCircleToMap(list: List<MapData>) {
        for (i in list) {
            if (i.longitude != null) {
                var tmpCircle = MapCircle(
                    MapPoint.mapPointWithGeoCoord(i.latitude!!, i.longitude!!),
                    20000,
                    Color.argb(128, 0, 255, 0),
                    Color.argb(128, 255, 0, 0)
                )
                mapView.addCircle(tmpCircle)
            }


        }
    }

}
