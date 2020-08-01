package wanna.cu.covid19_.mapFragmentDatas

import android.content.Context
import android.location.Geocoder
import android.os.AsyncTask
import android.os.Build
import android.util.Log
import android.util.TimeUtils
import androidx.annotation.RequiresApi
import wanna.cu.covid19_.mainFragments.MapFragment
import wanna.cu.covid19_.sexFragmentDatas.SexAgeContract
import wanna.cu.covid19_.sexFragmentDatas.SexAgePresenter
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.system.measureTimeMillis


class MapPresenter private constructor(val view: MapContract.MapView, val context: Context) : MapContract.MapPresenter,
    MapModel.OnMapDataFetchedListener {
    val mapModel = MapModel.newInstance(this)
    var mapDatas: ArrayList<MapData>? = ArrayList<MapData>()
    val geocoder = Geocoder(context)
    var geoMapDatas = ArrayList<MapData>()
    companion object {
        const val TAG = "MapPresenter 디버깅"
        private var instance: MapPresenter? = null

        @JvmStatic
        fun newInstance(view: MapContract.MapView, context: Context) =
            instance ?: synchronized(this) {
                instance ?: MapPresenter(view, context).also { instance = it }
            }
    }
    fun setLocationToMapData(){
        GeoAsync().execute()
    }
    fun setCircleToView(){
        view.setCircleToMap(geoMapDatas)
        view.setMarkerToMap(geoMapDatas)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun getMapDataFromModel() {
        mapModel.doMapDataRetrofit(getToday())
    }

    override fun onFetched() {
        Log.d(
            TAG, "onFetched"
        )
        mapDatas = mapModel.mapDatas
        setLocationToMapData()
    }

    override fun onFailed() {
        Log.d(TAG, "onFailed")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getToday(): String {
        val now = LocalDate.now()

        val resultformat = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"))

        Log.d("MapModel", resultformat)

        Log.d("MapModel", now.minusDays(1).toString())


        return resultformat
    }
    inner class GeoAsync : AsyncTask<Unit, Unit, Unit>(){
        override fun doInBackground(vararg p0: Unit?) {
            val time = measureTimeMillis {
                for(i in mapDatas!!){
                    var tmp = i
                    Log.d(TAG, tmp.toString())
                    var geo = geocoder.getFromLocationName("${i.gubun}", 1).let {
                        if(!it.isEmpty()){
                            tmp.latitude = it.get(0).latitude
                            tmp.longitude= it.get(0).longitude
                        }
                    }
                    Log.d(TAG, i.toString())
                    geoMapDatas.add(tmp)
                }
            }
            Log.d(TAG, geoMapDatas.toString())
            setCircleToView()

            Log.d(TAG, time.toString())
        }


    }

}