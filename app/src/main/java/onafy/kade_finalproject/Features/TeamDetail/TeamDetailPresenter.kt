package onafy.kade_finalproject.DetailTeam

import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import onafy.kade_finalproject.Api.ApiRepository
import onafy.kade_finalproject.Api.TheSportDBApi
import onafy.kade_finalproject.ModelDataClass.TeamResponse
import onafy.kade_finalproject.Util.CoroutineContextProvider
import org.jetbrains.anko.coroutines.experimental.bg

class TeamDetailPresenter(private val view: TeamDetailView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson,
                          private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamDetail(teamId: String) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getTeamsById(teamId)),
                        TeamResponse::class.java)
            }
            view.hideLoading()
            view.showTeamDetail(data.await().teams)
                }
            }
        }
