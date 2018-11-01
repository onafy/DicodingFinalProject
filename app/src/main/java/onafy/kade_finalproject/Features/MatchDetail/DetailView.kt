package onafy.kade_finalproject.Features.MatchDetail
import onafy.kade_finalproject.ModelDataClass.Event
import onafy.kade_finalproject.ModelDataClass.Team


interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun showHomeImage(data: List<Team>)
    fun showAwayImage(data: List<Team>)
    fun showDetail(data: List<Event>)
}