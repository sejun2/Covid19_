package wanna.cu.covid19_.sexFragmentDatas

import wanna.cu.covid19_.sidoFragmentDatas.Sido

interface SexAgeContract {
    interface  SexPresenter {
        fun getSexAgesFromSexAgeModel()
        fun setSexAgesToSexAgeFragment()
    }
    interface SexFragmentView{
        fun setSexAges(tmp : List<SexAge>)
        fun initSpinner(tmp : List<SexAge>)
    }
}