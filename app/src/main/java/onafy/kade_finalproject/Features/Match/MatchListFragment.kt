package onafy.kade_finalproject.Features.Match


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.gson.Gson
import onafy.kade_finalproject.Api.ApiRepository
import onafy.kade_finalproject.Features.MatchDetail.DetailActivity
import onafy.kade_finalproject.ModelDataClass.Event
import onafy.kade_finalproject.R
import onafy.kade_finalproject.R.array.league
import onafy.kade_finalproject.Util.invisible
import onafy.kade_finalproject.Util.parseEventName
import onafy.kade_finalproject.Util.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MatchListFragment : Fragment(), AnkoComponent<Context>, MainView {
    private var events: MutableList<Event> = mutableListOf()
    private lateinit var listEvent: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var spinner: Spinner
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter
    private lateinit var eventType : String
    private lateinit var eventName : String



    companion object {
        val idListEvent = 100
        val idSwipeRefresh = 101
        val idSpinner = 102
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //================================== recyclerView ========================================
        // this adapter is used for nextEvent and pastEvent
        adapter = MainAdapter(ctx, events) {
            startActivity(intentFor<DetailActivity>(
                    "eventId" to it.eventId,
                    "homeId" to it.homeId,
                    "awayId" to it.awayId))
        }
        eventType = this.arguments?.getString("matchType") ?:""
        Log.d("eventtype", eventType)
        listEvent.adapter = adapter
        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)
        // =====================================================================================


        // ================================= Spinner and Swipe  ===========================================
        val spinnerItems = resources.getStringArray(league)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                events.clear()
                eventName = spinner.selectedItem.toString()
                presenter.getEventList(parseEventName(eventName), eventType)
            }
        }

        swipeRefresh.onRefresh {
            eventName = spinner.selectedItem.toString()
            presenter.getEventList(parseEventName(eventName), eventType)
        }
        //======================================================================================

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    @SuppressLint("ResourceType")
    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width= matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            spinner = spinner{
                id = idSpinner
            }
            swipeRefresh = swipeRefreshLayout{
                id = idSwipeRefresh
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)
                relativeLayout{
                    lparams(width= matchParent, height= wrapContent)

                    listEvent = recyclerView{
                        id = idListEvent
                        lparams(width= matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }
                    progressBar =  progressBar{}
                            .lparams{
                                centerHorizontally()
                            }
                }
            }
        }//========================================================================================
    }




    // ===================================== Function ==============================
    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showEventList(data: List<Event>) {
        swipeRefresh.isRefreshing = false
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }

}
