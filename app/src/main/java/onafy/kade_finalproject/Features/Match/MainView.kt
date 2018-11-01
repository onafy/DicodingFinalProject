package onafy.kade_finalproject.Features.Match

import onafy.kade_finalproject.ModelDataClass.Event

interface MainView {
        fun showLoading()
        fun hideLoading()
        fun showEventList(data: List<Event>)
}