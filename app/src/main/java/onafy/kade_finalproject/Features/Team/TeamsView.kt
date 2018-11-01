package onafy.kade_finalproject.Features.Team

import onafy.kade_finalproject.ModelDataClass.Team

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}