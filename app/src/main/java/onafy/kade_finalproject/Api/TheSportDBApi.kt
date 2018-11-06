package onafy.kade_finalproject.Api

import android.net.Uri
import android.util.Log
import onafy.kade_finalproject.BuildConfig

object TheSportDBApi {

    fun getEvents(matchId: Int?, matchType: String?): String {
        var url = ""

        if(matchType == "Next Event")
        {
            url = "eventsnextleague.php"
        }
        else if(matchType == "Past Event")
        {
            url = "eventspastleague.php"
        }
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/" + url + "?id=" + matchId
    }

    fun getTeamsById(teamId: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupteam.php?id=" + teamId
    }

    fun getEventDetail(eventId: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupevent.php?id=" + eventId
    }

    fun getTeams(league: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("search_all_teams.php")
                .appendQueryParameter("l", league)
                .build()
                .toString()
    }

    fun getTeamPlayer(id: String?): String {
        Log.d("id masuk", id)
        val url = "lookup_all_players.php"
        return BuildConfig.BASE_URL + "/api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/$url?id=" + id
    }

    fun getTeamPlayers(id: String?): String {
        Log.d("id masuk", id)
        val url = "lookup_all_players.php"
        return BuildConfig.BASE_URL + "/api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/$url?id=" + id
        Log.d("return url", BuildConfig.BASE_URL + "/api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/$url?id=" + id)
    }

}