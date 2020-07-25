package wanna.cu.covid19_.sidoFragmentDatas

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