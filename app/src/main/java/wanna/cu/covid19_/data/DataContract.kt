package wanna.cu.covid19_.data

import android.content.Context
import android.widget.ArrayAdapter

interface DataContract{

    interface Presenter{
        fun getSidosFromSidoModel()
        fun setSidosToSidoFragment(): List<Sido>
    }
    interface FragmentView{
        fun setSidos(tmp : List<Sido>)
        fun initSpinner(tmp : List<Sido>)
    }
}