package onafy.kade_finalproject.Features.Match

import com.google.gson.Gson
import onafy.kade_finalproject.Api.TheSportDBApi
import onafy.kade_finalproject.ModelDataClass.EventResponse
import onafy.kade_finalproject.Util.CoroutineContextProvider
import onafy.kade_finalproject.Api.ApiRepository
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(private val view: MainView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson,
                    private val context: CoroutineContextProvider = CoroutineContextProvider()) {


    fun getEventList(matchId: Int?, matchType: String?) {
        view.showLoading()
        doAsync {
            if(matchType != "Favorites")
            {
                var data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getEvents(matchId, matchType)),
                        EventResponse::class.java)
                uiThread {
                    view.hideLoading()
                    view.showEventList(data.events)
                }

            }
            else{
                uiThread {
                    view.hideLoading()
               }
            }
        }
    }
}