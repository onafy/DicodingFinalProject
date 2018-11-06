package onafy.kade_finalproject.Features.TeamDetail.Player

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import onafy.kade_finalproject.ModelDataClass.Player
import onafy.kade_finalproject.R
import onafy.kade_finalproject.R.id.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class PlayerAdapter(private val players: List<Player>, private val listener: (Player) -> Unit): RecyclerView.Adapter<PlayerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }


    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(players[position], listener)
    }

  }

    class PlayerViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val playerPict: ImageView =  view.findViewById(player_pict)
        private val playerName: TextView = view.findViewById(player_name)
        private val playerPosition: TextView = view.findViewById(player_position)
        fun bindItem(players: Player, listener: (Player) -> Unit) {
            Log.i("masuk", "masuk adapter")
            playerName.text = players.playerName
            Log.d("playersName", players.playerName)
            playerPosition.text = players.playerPosition
           // Picasso.get().load(players.playerPict).into(playerPict)
            Glide.with(itemView.context)
                    .load(players.playerPict)
                    .into(playerPict)

            itemView.onClick {
                listener(players)
            }
        }

    }


    class TeamUI: AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui){
                linearLayout{
                    lparams(width= matchParent, height= wrapContent)
                    padding=dip(10)
                    orientation= LinearLayout.HORIZONTAL

                    imageView{
                        id= R.id.player_pict
                    }.lparams{
                        height=dip(50)
                        width=dip(50)
                        rightMargin = dip(10)
                        gravity = Gravity.CENTER
                    }

                    textView{
                        id= R.id.player_name
                        textSize= 16f
                    }.lparams{
                        margin= dip(15)
                        gravity = Gravity.CENTER
                    }

                    textView{
                        id= R.id.player_position
                        textSize= 16f
                    }.lparams{
                        margin= dip(15)
                        gravity = Gravity.CENTER_VERTICAL or Gravity.END
                    }
                }
            }
        }

    }


