package wanna.cu.covid19_.mapFragmentDatas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import net.daum.mf.map.api.CalloutBalloonAdapter
import net.daum.mf.map.api.MapPOIItem
import wanna.cu.covid19_.R

class CustomCalloutBalloonAdapter(val context: Context?) : CalloutBalloonAdapter {
    lateinit var mCalloutBalloon : View

    init {
        mCalloutBalloon = LayoutInflater.from(context).inflate(R.layout.balloon, null)
    }

    override fun getPressedCalloutBalloon(p0: MapPOIItem?): View {
        return mCalloutBalloon
    }

    override fun getCalloutBalloon(p0: MapPOIItem?): View {
        mCalloutBalloon.findViewById<TextView>(R.id.city_textView).setText(p0?.itemName.toString())

        return mCalloutBalloon
    }
}