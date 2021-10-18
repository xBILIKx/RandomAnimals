package com.example.animals.Cats

import com.google.gson.annotations.SerializedName

data class CatResponce(
    /*@SerializedName("id")
    val id: Int,
    @SerializedName("url")
    val imgUrl: String,
    @SerializedName("webpurl")
    val webpurl: String,
    @SerializedName("x")
    val x: Double,
    @SerializedName("y")
    val y: Double*/

    @SerializedName("file")
    val imgUrl: String
)