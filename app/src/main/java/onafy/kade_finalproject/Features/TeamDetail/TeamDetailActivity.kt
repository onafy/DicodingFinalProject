package onafy.kade_finalproject.Features.TeamDetail

import android.database.sqlite.SQLiteConstraintException
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import android.widget.Toast.LENGTH_LONG
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import onafy.kade_finalproject.Api.ApiRepository
import onafy.kade_finalproject.DB.FavoriteTeam
import onafy.kade_finalproject.DB.database
import onafy.kade_finalproject.DetailTeam.TeamDetailPresenter
import onafy.kade_finalproject.DetailTeam.TeamDetailView
import onafy.kade_finalproject.ModelDataClass.Team
import onafy.kade_finalproject.R
import onafy.kade_finalproject.Util.DetailTeamPagerAdapter
import onafy.kade_finalproject.Util.invisible
import onafy.kade_finalproject.Util.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.support.v4.viewPager

class TeamDetailActivity : AppCompatActivity() , TeamDetailView{
    private lateinit var progressBar: ProgressBar
    private lateinit var appBar: AppBarLayout
    private lateinit var tab: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var container: RelativeLayout
    private lateinit var teamBadge: ImageView
    private lateinit var teamName: TextView
    private lateinit var teamFormedYear: TextView
    private lateinit var teamStadium: TextView

    private lateinit var presenter: TeamDetailPresenter
    private lateinit var teams: Team
    private lateinit var id: String
    private lateinit var teamDesc: String

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    companion object {
        val idContainer = 1
        val idAppBar = 2
        val idTab = 3
        val idViewPager = 4
        val idTeamBadge = 5
        val idTeamName = 6
        val idTeamFormedYear = 7
        val idTeamStadium = 8
        val idProgressBar = 9

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ================================== UI ======================================================
        linearLayout{
            lparams(width= matchParent, height= wrapContent)
            orientation = LinearLayout.VERTICAL
            backgroundColor = Color.WHITE

                relativeLayout {
                    id = idContainer
                    lparams(width = matchParent, height = wrapContent)
                    backgroundColor = Color.WHITE

                    appBarLayout {
                        id = idAppBar
                        teamBadge = imageView(){
                            verticalPadding = dip(8)
                        }.lparams(height=dip(100)){
                            gravity = Gravity.CENTER
                        }
                        teamName = textView{
                            this.gravity = Gravity.CENTER
                            bottomPadding = dip(5)
                            textSize = 22f
                            typeface = Typeface.DEFAULT_BOLD
                            textColor = Color.WHITE
                        }.lparams{
                            gravity = Gravity.CENTER
                        }

                        teamFormedYear = textView{
                            typeface = Typeface.DEFAULT_BOLD
                            this.gravity = Gravity.CENTER
                            textColor = Color.WHITE
                        }

                        teamStadium = textView{
                            typeface = Typeface.DEFAULT_BOLD
                            this.gravity = Gravity.CENTER
                            textColor = Color.WHITE
                        }

                        tabLayout {
                            id = idTab
                            lparams(matchParent, wrapContent) {
                                tabMode = TabLayout.MODE_FIXED
                                tabGravity = TabLayout.GRAVITY_FILL
                              // tabTextColors = ContextCompat.getColorStateList(context, R.drawable.tab_item_color_state)
                            }
                        }
                    }.lparams(width = matchParent, height = wrapContent) {
                        alignParentTop()
                    }

                    viewPager {
                        id = idViewPager
                        leftPadding = dip(10)
                        rightPadding = dip(10)

                    }.lparams(width = matchParent, height = matchParent) {
                        below(idAppBar)
                    }

                    progressBar = progressBar {
                        id = idProgressBar
                    }.lparams(){
                        centerInParent()
                    }
                }

        }

        // ========================================================================================

        supportActionBar?.title = null
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        container = find(TeamDetailActivity.idContainer)
        progressBar = find(TeamDetailActivity.idProgressBar)
        viewPager = find(TeamDetailActivity.idViewPager)
        tab = find(TeamDetailActivity.idTab)


        val intent = intent
        id = intent.getStringExtra("id")
        teamDesc = intent.getStringExtra("teamDescription")
        Log.d("teamdesc", teamDesc)

        val teamAdapter = DetailTeamPagerAdapter(supportFragmentManager, id, teamDesc)
      // loadDescFragment(teamDesc)

        viewPager.adapter = teamAdapter
        viewPager.currentItem = 0
        tab.setupWithViewPager(viewPager)

        favoriteState()
        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamDetailPresenter(this, request, gson)
        presenter.getTeamDetail(id)

    }


    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamDetail(data: List<Team>) {
        //display data on UI
        teams = Team(data[0].teamId,
                data[0].teamName,
                data[0].teamBadge)
        Picasso.get().load(data[0].teamBadge).into(teamBadge)
        teamName.text = data[0].teamName
      //  teamDescription.text = data[0].teamDescription
        teamFormedYear.text = data[0].teamFormedYear
        teamStadium.text = data[0].teamStadium
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (::teams.isInitialized) {
                    if (isFavorite) removeFromFavorite() else addToFavorite()
                    isFavorite = !isFavorite
                    setFavorite()
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun addToFavorite(){
        try{
            database.use{
                insert(FavoriteTeam.TABLE_FAVORITE,
                        FavoriteTeam.TEAM_ID to teams.teamId,
                        FavoriteTeam.TEAM_NAME to teams.teamName,
                        FavoriteTeam.TEAM_BADGE to teams.teamBadge)
            }
            Toast.makeText(this, "Added to favorite", LENGTH_LONG).show()
        } catch (e: SQLiteConstraintException){
            Toast.makeText(this, e.localizedMessage, LENGTH_LONG).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(FavoriteTeam.TABLE_FAVORITE, "(TEAM_ID = {id})",
                        "id" to id)
            }
            Toast.makeText(this, "Removed to favorite", LENGTH_LONG).show()
        } catch (e: SQLiteConstraintException){
            Toast.makeText(this, e.localizedMessage, LENGTH_LONG).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    private fun favoriteState(){
        try{
            database.use {
                val result = select(FavoriteTeam.TABLE_FAVORITE)
                        .whereArgs("(TEAM_ID = {id})",
                                "id" to id)
                val favorite = result.parseList(classParser<FavoriteTeam>())
                if (!favorite.isEmpty()) isFavorite = true
            }
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(this, e.localizedMessage, LENGTH_LONG).show()
        }
    }
}
