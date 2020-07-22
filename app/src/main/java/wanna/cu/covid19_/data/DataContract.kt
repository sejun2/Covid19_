package wanna.cu.covid19_.data

import android.content.Context
import android.widget.ArrayAdapter

interface DataContract{

    interface Presenter{

    }
    interface FragmentView{
        fun setSidos(tmp : List<Sido>)
    }
}