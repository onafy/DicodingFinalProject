package onafy.kade_finalproject.DetailTeam
import onafy.kade_finalproject.ModelDataClass.Team

interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Team>)
}