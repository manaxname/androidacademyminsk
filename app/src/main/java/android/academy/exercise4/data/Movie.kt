package android.academy.exercise4.data

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes

data class Movie(
    val name: String,
    @DrawableRes val pic_normal: Int,
    @DrawableRes val pic_big: Int,
    val description: String,
    val publishedOn: String,
    val url: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        if (parcel == null)
            return
        parcel.writeString(name)
        parcel.writeInt(pic_normal)
        parcel.writeInt(pic_big)
        parcel.writeString(description)
        parcel.writeString(publishedOn)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}
