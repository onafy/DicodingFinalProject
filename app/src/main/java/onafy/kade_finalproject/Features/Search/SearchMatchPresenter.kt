package onafy.kade_finalproject.Features.Search

import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import onafy.kade_finalproject.Api.ApiRepository
import onafy.kade_finalproject.Api.TheSportDBApi
import onafy.kade_finalproject.ModelDataClass.SearchMatchResponse
import onafy.kade_finalproject.Util.CoroutineContextProvider
import org.jetbrains.anko.coroutines.experimental.bg

class SearchMatchPresenter(private val view: SearchMatchView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun searchMatch(query: String) {
        view.showLoading()
        async(context.main){
            val data = bg{
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getSearchMatch(query)),
                        SearchMatchResponse::class.java
                )
            }
            if (data.await().event.isNotEmpty())
                view.hideLoading()
            view.showSearchMatch(data.await().event)
        }
    }
}