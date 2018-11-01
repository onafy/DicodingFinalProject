package onafy.kade_finalproject.ModelDataClass

import com.google.gson.annotations.SerializedName

class EventResponse(
        @SerializedName("events") var events: List<Event>)