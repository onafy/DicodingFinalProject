package onafy.kade_finalproject.Features.Favorites

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import onafy.kade_finalproject.DB.FavoriteMatch
import onafy.kade_finalproject.R
import org.jetbrains.anko.find

class FavoriteMatchAdapter(private val context: Context, private val favoriteMatch: List<FavoriteMatch>, val listener: (FavoriteMatch) -> Unit) : RecyclerView.Adapter<FavoriteMatchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            FavoriteMatchViewHolder(LayoutInflater.from(context).inflate(R.layout.favoritelist_item, parent, false))


    override fun getItemCount(): Int = favoriteMatch.size

    override fun onBindViewHolder(holder: FavoriteMatchViewHolder, position: Int) {
        holder.bindItem(favoriteMatch[position], listener)
    }

}

class FavoriteMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val homeName: TextView = view.find(R.id.homenameTV)
    private val eventDate: TextView = view.find(R.id.dateeventTV)
    private val homeScore: TextView = view.find(R.id.homescoreTV)
    private val awayScore: TextView = view.find(R.id.awayscoreTV)
    private val awayName: TextView = view.find(R.id.awaynameTV)

    fun bindItem(favoriteMatch: FavoriteMatch, listener: (FavoriteMatch) -> Unit) {
        homeName.text = favoriteMatch.homeName
        awayName.text = favoriteMatch.awayName
        Log.d(favoriteMatch.homeName, "HomeName6")
        Log.d(favoriteMatch.awayName, "awayName7")
        eventDate.text = favoriteMatch.eventDate
        homeScore.text = favoriteMatch.homeScore
        awayScore.text = favoriteMatch.awayScore
        itemView.setOnClickListener {
            listener(favoriteMatch)
        }
    }

}



