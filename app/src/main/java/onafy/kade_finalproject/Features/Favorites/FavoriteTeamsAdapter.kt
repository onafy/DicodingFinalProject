package onafy.kade_finalproject.Features.Favorites

import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.squareup.picasso.Picasso
import onafy.kade_finalproject.DB.FavoriteTeam
import onafy.kade_finalproject.R
import onafy.kade_finalproject.R.id.team_badge
import onafy.kade_finalproject.R.id.team_name
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class FavoriteTeamsAdapter(private val favTeam: MutableList<FavoriteTeam>, private val listener: (FavoriteTeam) -> Unit)
    : RecyclerView.Adapter<FavoriteTeamsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(favTeam[position], listener)
    }

    override fun getItemCount(): Int = favTeam.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val teamName = view.findViewById<TextView>(TeamUI.team_name)
        private val teamBadge = view.findViewById<ImageView>(TeamUI.team_badge)

        fun bindItem(favTeam: FavoriteTeam, listener: (FavoriteTeam) -> Unit) {
            teamName.text = favTeam.teamName
            Log.d("items", favTeam.toString())
            Picasso.get().load(favTeam.teamBadge).into(teamBadge)
            itemView.setOnClickListener {
                listener(favTeam)
            }
        }
    }
    class TeamUI() : AnkoComponent<ViewGroup> {
        companion object {
            val team_name = 1
            val team_badge = 2
        }

        override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
            return linearLayout{
                lparams(width= matchParent, height= wrapContent)
                padding=dip(16)
                orientation= LinearLayout.HORIZONTAL

                imageView{
                    id= team_badge
                }.lparams{
                    height=dip(50)
                    width=dip(50)
                }

                textView{
                    id= team_name
                    textSize= 16f
                }.lparams{
                    margin= dip(15)
                }
            }
        }
    }
}