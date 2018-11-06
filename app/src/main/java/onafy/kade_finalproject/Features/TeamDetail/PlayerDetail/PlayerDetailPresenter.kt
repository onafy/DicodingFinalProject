package onafy.kade_finalproject.Features.TeamDetail.PlayerDetail


import com.google.gson.Gson
import onafy.kade_finalproject.Api.ApiRepository
import onafy.kade_finalproject.Api.TheSportDBApi
import onafy.kade_finalproject.ModelDataClass.EventResponse
import onafy.kade_finalproject.ModelDataClass.TeamResponse
import onafy.kade_finalproject.Util.CoroutineContextProvider
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PlayerDetailPresenter(private val view: PlayerDetailView,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson,
                            private val context: CoroutineContextProvider = CoroutineContextProvider()) {
    fun getTeamDetail(eventId: String, homeId: String, awayId: String) {
        view.showLoading()
        doAsync {
            //detail
            var detail = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getEventDetail(eventId)),
                    EventResponse::class.java)

            //home
            var data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamsById(homeId)),
                    TeamResponse::class.java)

            //away
            var data2 = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamsById(awayId)),
                    TeamResponse::class.java)


            uiThread {
                view.hideLoading()
                view.showHomeImage(data.teams)
                view.showAwayImage(data2.teams)
                view.showDetail(detail.events)

            }
        }
    }
    
}