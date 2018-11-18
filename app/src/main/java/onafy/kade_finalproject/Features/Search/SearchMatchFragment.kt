package onafy.kade_finalproject.Features.Search

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.google.gson.Gson
import onafy.kade_finalproject.Api.ApiRepository
import onafy.kade_finalproject.Features.Match.MainAdapter
import onafy.kade_finalproject.Features.MatchDetail.DetailActivity
import onafy.kade_finalproject.ModelDataClass.Event
import onafy.kade_finalproject.Util.invisible
import onafy.kade_finalproject.Util.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx


class SearchMatchFragment() : Fragment(), AnkoComponent<Context>, SearchMatchView {

    private var events: MutableList<Event> = mutableListOf()
    private var query: String = ""
    private lateinit var adapterMatch: MainAdapter
    private lateinit var listEvents: RecyclerView
    private lateinit var progressBar: ProgressBar
    lateinit var presenter: SearchMatchPresenter

// -------------------------- Main Fragment ----------------------------------------------------------------------------
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        query = this.arguments?.getString("query") ?: ""
        adapterMatch = MainAdapter(ctx, events) {
            ctx.startActivity<DetailActivity>(
                    "eventId" to it.eventId,
                    "homeId" to it.homeId,
                    "awayId" to it.awayId)
        }
        listEvents.adapter = adapterMatch
        val request = ApiRepository()
        val gson = Gson()
        presenter = SearchMatchPresenter(this, request, gson)
        presenter.searchMatch(query)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

 // -----------------------------------------------------------------------------------------------------------------------



    //------------------------------- UI --------------------------------------------------------------------------------
    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            listEvents = recyclerView {
                lparams (width = matchParent, height = wrapContent)
                layoutManager = LinearLayoutManager(ctx)
            }

            progressBar = progressBar {
            }.lparams{
                // centerHorizontally()
                gravity = Gravity.CENTER
            }
        }
    }
    //----------------------------------------------------------------------------------------------------------------


    //-------------------------------------- FUNCTION -------------------------------------------------------------------
    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showSearchMatch(data: List<Event>) {
            val filteredData = data.filter { it.eventType == "Soccer" }
            events.clear()
            events.addAll(filteredData)
            adapterMatch.notifyDataSetChanged()

    }

    //------------------------------------------------------------------------------------------------------------------

}
