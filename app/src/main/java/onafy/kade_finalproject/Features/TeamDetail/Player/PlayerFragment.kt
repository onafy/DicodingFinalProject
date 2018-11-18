package onafy.kade_finalproject.Features.TeamDetail.Player

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
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.google.gson.Gson
import onafy.kade_finalproject.Api.ApiRepository
import onafy.kade_finalproject.Features.TeamDetail.PlayerDetail.PlayerDetailActivity
import onafy.kade_finalproject.ModelDataClass.Player
import onafy.kade_finalproject.R.color.colorAccent
import onafy.kade_finalproject.Util.invisible
import onafy.kade_finalproject.Util.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class PlayerFragment : Fragment(), AnkoComponent<Context>, PlayerView {
    //=================================== declaration ===========================================
    private var players: MutableList<Player> = mutableListOf()
    private lateinit var presenter: PlayerPresenter
    private lateinit var adapters: PlayerAdapter
    lateinit var listPlayer: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    //============================================================================================



    //================================ MAIN FRAGMENT =============================================
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setup()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }
    //=============================================================================================





    //================================ FUNCTION ===================================================
    fun setup(){
        val id = this.arguments?.getString("idTeam")
        Log.d("idTeam",id)

        adapters = PlayerAdapter(players) {
            ctx.startActivity<PlayerDetailActivity>(
                    "Pict" to "${it.playerPict}",
                    "Height" to "${it.playerHeight}",
                    "Weight" to "${it.playerWeight}",
                    "Position" to "${it.playerPosition}",
                    "Desc" to "${it.playerDesc}"
            )
        }
        listPlayer.adapter = adapters

        val request = ApiRepository()
        val gson = Gson()
        presenter = PlayerPresenter(this, request, gson)
        presenter.getPlayerList(id)

        swipeRefresh.onRefresh {
            presenter.getPlayerList(id)
        }

    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showPlayerList(data: List<Player>) {
        swipeRefresh.isRefreshing = false
        players.clear()
        players.addAll(data)
        Log.d("Players", players.toString())
        adapters.notifyDataSetChanged()
    }
    //====================================================================================================================

    //============================== UI =========================================================================
    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(10)
            rightPadding = dip(10)


            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                relativeLayout{
                    lparams (width = matchParent, height = wrapContent)

                    listPlayer = recyclerView {
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

    //==================================================================================================================


}
