package onafy.kade_finalproject.Features.Team

import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import onafy.kade_finalproject.Api.ApiRepository
import onafy.kade_finalproject.Api.TheSportDBApi
import onafy.kade_finalproject.ModelDataClass.TeamResponse
import onafy.kade_finalproject.Util.CoroutineContextProvider
import org.jetbrains.anko.coroutines.experimental.bg

class TeamsPresenter(private val view: TeamsView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()){


    fun getTeamList(league: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeams(league)),
                        TeamResponse::class.java)
            }
            view.hideLoading()
            view.showTeamList(data.await().teams)
        }
    }

}