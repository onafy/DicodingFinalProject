package onafy.kade_finalproject.ModelDataClass

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Player (
        @SerializedName("idPlayer")
        var playerId: String = "",

        @SerializedName("strPlayer")
        var playerName: String = "",

        @SerializedName("strPosition")
        var playerPosition: String = "",

        @SerializedName("strCutout")
        var playerPict: String = "",

        @SerializedName("strFanart1")
        var playerThumb: String = "",

        @SerializedName("strHeight")
        var playerHeight: String = "",

        @SerializedName("strWeight")
        var playerWeight: String = "",

        @SerializedName("strDescriptionEN")
        var playerDesc: String = ""
) : Parcelable






