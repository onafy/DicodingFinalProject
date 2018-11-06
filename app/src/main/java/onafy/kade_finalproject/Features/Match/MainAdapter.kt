package onafy.kade_finalproject.Features.Match

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import onafy.kade_finalproject.ModelDataClass.Event
import onafy.kade_finalproject.R
import org.jetbrains.anko.find

class MainAdapter(private val context: Context, private val events: List<Event>,  val listener: (Event) -> Unit): RecyclerView.Adapter<EventViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
            EventViewHolder(LayoutInflater.from(context).inflate(R.layout.eventlist_item, parent, false))


    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bindItem(events[position], listener)
    }

}

class EventViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val homeName: TextView = view.find(R.id.homenameTV)
    private val eventDate: TextView = view.find(R.id.dateeventTV)
    private val homeScore: TextView = view.find(R.id.homescoreTV)
    private val awayScore: TextView = view.find(R.id.awayscoreTV)
    private val awayName: TextView = view.find(R.id.awaynameTV)

    fun bindItem(events: Event, listener: (Event) -> Unit)
    {
        homeName.text = events.homeName
        Log.d(events.homeName, "HomeName")
        eventDate.text = events.eventDate
        homeScore.text = events.homeScore
        awayScore.text = events.awayScore
        awayName.text = events.awayName
        itemView.setOnClickListener {
            listener(events)
        }
    }

}



