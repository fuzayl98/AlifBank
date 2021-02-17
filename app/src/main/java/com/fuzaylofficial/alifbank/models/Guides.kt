package com.fuzaylofficial.alifbank.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Guides {
    @SerializedName("total")
    @Expose
    var total_count = 0

    @SerializedName("data")
    var guides: List<Guide> = ArrayList()



    @Entity
    data class Guide(val name:String?, val startDate:String?, val endDate:String?, val icon:String?,@PrimaryKey val url:String)
    /*{


        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("startDate")
        @Expose
        var startDate: String? = null

        @SerializedName("endDate")
        @Expose
        var endDate: String? = null

        @SerializedName("icon")
        @Expose
        var iconUrl: String? = null

        @SerializedName("url")
        @Expose
        var url: String? = ""

    }*/
}