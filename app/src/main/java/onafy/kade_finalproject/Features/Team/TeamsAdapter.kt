package onafy.kade_finalproject.Features.Team

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import onafy.kade_finalproject.ModelDataClass.Team
import onafy.kade_finalproject.R
import onafy.kade_finalproject.R.id.team_badge
import onafy.kade_finalproject.R.id.team_name
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class TeamsAdapter(private val teams: List<Team>,  private val listener: (Team) -> Unit): RecyclerView.Adapter<TeamViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }


    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(teams[position], listener)
    }

  }

    class TeamViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val teamBadge: ImageView = view.find(team_badge)
        private val teamName: TextView = view.find(team_name)
        fun bindItem(teams: Team, listener: (Team) -> Unit) {
            Picasso.get().load(teams.teamBadge).into(teamBadge)
            teamName.text = teams.teamName
            itemView.onClick { listener(teams)}
        }

    }

// ================================== UI =======================================================
    class TeamUI: AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui){
                linearLayout{
                    lparams(width= matchParent, height= wrapContent)
                    padding=dip(16)
                    orientation= LinearLayout.HORIZONTAL

                    imageView{
                        id= R.id.team_badge
                    }.lparams{
                        height=dip(50)
                        width=dip(50)
                    }

                    textView{
                        id= R.id.team_name
                        textSize= 16f
                    }.lparams{
                        margin= dip(15)
                    }
                }
            }
        }

    }
 // ==============================================================================================

