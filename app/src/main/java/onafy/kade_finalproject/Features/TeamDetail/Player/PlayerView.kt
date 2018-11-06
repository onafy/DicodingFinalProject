package onafy.kade_finalproject.Features.TeamDetail.Player

import onafy.kade_finalproject.ModelDataClass.Player

interface PlayerView {
    fun showLoading()
    fun hideLoading()
    fun showPlayerList(data: List<Player>)
}