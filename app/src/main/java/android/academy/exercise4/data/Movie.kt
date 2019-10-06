package android.academy.exercise4.data

import androidx.annotation.DrawableRes


data class Movie(
    val name: String,
    @DrawableRes val pic_normal: Int,
    @DrawableRes val pic_big: Int,
    val description: String,
    val publishedOn: String,
    val url: String
)
