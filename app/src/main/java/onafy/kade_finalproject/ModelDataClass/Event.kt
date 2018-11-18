package onafy.kade_finalproject.ModelDataClass

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("idEvent")
    var eventId: String? = null,

    @SerializedName("strSport")
    var eventType: String = "",

    @SerializedName("strHomeTeam")
    var homeName: String? = null,

    @SerializedName("strAwayTeam")
    var awayName: String? = null,

    @SerializedName("strDate")
    var eventDate: String? = null,

    @SerializedName("intHomeScore")
    var homeScore: String? = null,

    @SerializedName("intAwayScore")
    var awayScore: String? = null,


    //============================================ Detail Component =================

    @SerializedName("idHomeTeam")
    var homeId: String? = null,

    @SerializedName("idAwayTeam")
    var awayId: String? = null,

    @SerializedName("strHomeGoalDetails")
    var homeGoalDetails: String? = null,

    @SerializedName("strAwayGoalDetails")
    var awayGoalDetails: String? = null,

    @SerializedName("intHomeShots")
    var homeShots: String? = null,

    @SerializedName("intAwayShots")
    var awayShots: String? = null,

    @SerializedName("strHomeLineupGoalkeeper")
    var homeGoalKeeper: String? = null,

    @SerializedName("strAwayLineupGoalkeeper")
    var awayGoalKeeper: String? = null,

    @SerializedName("strHomeLineupDefense")
    var homeDefense: String? = null,

    @SerializedName("strAwayLineupDefense")
    var awayDefense: String? = null,

    @SerializedName("strHomeLineupMidfield")
    var homeMidfield: String? = null,

    @SerializedName("strAwayLineupMidfield")
    var awayMidfield: String? = null,

    @SerializedName("strHomeLineupForward")
    var homeForward: String? = null,

    @SerializedName("strAwayLineupForward")
    var awayForward: String? = null,

    @SerializedName("strHomeLineupSubstitutes")
    var homeSubtitutes: String? = null,

    @SerializedName("strAwayLineupSubstitutes")
    var awaySubtitutes: String? = null


)