package onafy.kade_finalproject.Util

import android.view.View

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}


fun parseEventName(eventName: String?) : Int {
    return when (eventName) {
        "English Premier League" -> 4328
        "German Bundesliga" -> 4331
        "Italian Serie A" -> 4332
        "Spanish La Liga" -> 4335
        else -> 4328
    }
}