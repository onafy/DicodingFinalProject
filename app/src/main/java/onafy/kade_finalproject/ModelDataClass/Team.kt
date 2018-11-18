package onafy.kade_finalproject.ModelDataClass

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("idTeam")
    var teamId: String = "",

    @SerializedName("strTeam")
    var teamName: String = "",

    @SerializedName("strTeamBadge")
    var teamBadge: String = "",

    @SerializedName("intFormedYear")
    var teamFormedYear: String = "",

    @SerializedName("strStadium")
    var teamStadium: String = "",

    @SerializedName("strDescriptionEN")
    var teamDescription: String = "",

    @SerializedName("strSport")
    var teamType: String = ""


)
