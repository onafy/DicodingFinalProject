package onafy.kade_finalproject.Features.Team

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.*
import com.google.gson.Gson
import onafy.kade_finalproject.Api.ApiRepository
import onafy.kade_finalproject.Features.TeamDetail.TeamDetailActivity
import onafy.kade_finalproject.ModelDataClass.Team
import onafy.kade_finalproject.R
import onafy.kade_finalproject.R.array.league
import onafy.kade_finalproject.R.color.colorAccent
import onafy.kade_finalproject.R.id.searchBar
import onafy.kade_finalproject.R.menu.search_menu
import onafy.kade_finalproject.Util.gone
import onafy.kade_finalproject.Util.invisible
import onafy.kade_finalproject.Util.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class TeamsFragment : Fragment(), AnkoComponent<Context>, TeamsView {

    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: TeamsPresenter
    private lateinit var adapter: TeamsAdapter
    lateinit var Spinner: Spinner
    lateinit var listEvent: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var leagueName: String

    //============================== MAIN ==============================================================================
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        setup()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }
    //===================================================================================================================



    //================================== FUNCTION ======================================================================
    fun setup ()
    {
        val spinnerItems = resources.getStringArray(league)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        Spinner.adapter = spinnerAdapter

        adapter = TeamsAdapter(teams) {
            ctx.startActivity<TeamDetailActivity>("id" to "${it.teamId}", "teamDescription" to "${it.teamDescription}",
                    "teamBadge" to "${it.teamBadge}")
        }
        listEvent.adapter = adapter
        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamsPresenter(this, request, gson)
        Spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = Spinner.selectedItem.toString()
                presenter.getTeamList(leagueName)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        swipeRefresh.onRefresh {
            leagueName = Spinner.selectedItem.toString()
            presenter.getTeamList(leagueName)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(search_menu, menu)
        val searchBar = menu?.findItem(searchBar)?.actionView as SearchView?
        val search = menu?.findItem(R.id.searchBar)

        search?.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                leagueName = Spinner.selectedItem.toString()
                presenter.getTeamList(leagueName)
                return true
            }
        })
        searchBar?.queryHint = "Search"
        searchBar?.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Spinner.gone()
                presenter.searchTeam(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty() && isVisible) {
                    Spinner.visible()
                    leagueName = Spinner.selectedItem.toString()
                    presenter.getTeamList(leagueName)
                }
                return false
            }
        })
    }


    override fun onPrepareOptionsMenu(menu: Menu?) {
        super.onPrepareOptionsMenu(menu)
        val search = menu?.findItem(searchBar)
        search?.collapseActionView()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            searchBar -> {
                teams.clear()
                adapter.notifyDataSetChanged()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: List<Team>) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showSearchTeam(data: List<Team>) {
        val filteredData = data.filter { it.teamType == "Soccer" }
        teams.clear()
        teams.addAll(filteredData)
        adapter.notifyDataSetChanged()
    }

    //============================================================================================================



    // ============================== UI ===============================================================================
    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            Spinner = spinner()
            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                relativeLayout{
                    lparams (width = matchParent, height = wrapContent)

                    listEvent = recyclerView {
                        lparams (width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                    }.lparams{
                        centerHorizontally()
                    }
                }
            }
        }
    }

    // ===============================================================================================================

}
