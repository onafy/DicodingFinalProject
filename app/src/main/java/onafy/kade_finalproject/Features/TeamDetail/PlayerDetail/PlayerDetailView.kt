package onafy.kade_finalproject.Features.TeamDetail.PlayerDetail

import onafy.kade_finalproject.ModelDataClass.Event
import onafy.kade_finalproject.ModelDataClass.Team


interface PlayerDetailView {
    fun showLoading()
    fun hideLoading()
    fun showHomeImage(data: List<Team>)
    fun showAwayImage(data: List<Team>)
    fun showDetail(data: List<Event>)
}