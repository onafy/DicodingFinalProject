package onafy.kade_finalproject.Features.TeamDetail.Player

import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import onafy.kade_finalproject.Api.ApiRepository
import onafy.kade_finalproject.Api.TheSportDBApi
import onafy.kade_finalproject.ModelDataClass.PlayerResponse
import onafy.kade_finalproject.Util.CoroutineContextProvider
import org.jetbrains.anko.coroutines.experimental.bg

class PlayerPresenter(private val view: PlayerView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson,
                      private val context: CoroutineContextProvider = CoroutineContextProvider()){


    fun getPlayerList(id: String?) {
        view.showLoading()
        async(context.main) {
            Log.i("masuk presenter", "masuk presenter")
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamPlayers(id)),
                        PlayerResponse::class.java)
            }
            if (data.await().player.isNotEmpty()) {
                Log.i("PlayerPresenter", "Player List Loaded Successfully..")
            }
            view.hideLoading()
            view.showPlayerList(data.await().player)
        }
    }

}