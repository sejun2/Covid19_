package wanna.cu.covid19_.mapFragmentDatas

interface MapContract {
    interface MapPresenter{
        fun getMapDataFromModel()
    }
    interface MapView{
       fun  setCircleToMap(list : List<MapData>)
}
}