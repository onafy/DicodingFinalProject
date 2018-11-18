package onafy.kade_finalproject.Features.Search

import onafy.kade_finalproject.ModelDataClass.Event


interface SearchMatchView {
    fun showLoading()
    fun hideLoading()
    fun showSearchMatch(data: List<Event> = emptyList())
}