package android.academy.exercise4.data

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.ViewModel


class MovieModel() : ViewModel(), Parcelable {
    var currentMovieInxed = 0
    private lateinit var movieList: MutableList<Movie>

    constructor(parcel: Parcel) : this() {
        currentMovieInxed = parcel.readInt()
    }

    fun getMovies(): ArrayList<Movie> {
        return ArrayList(movieList)
    }

    fun getCurrentMovie(): Movie {
        return movieList[currentMovieInxed]
    }

    fun setCurrentMovie(movie: Movie) {
        currentMovieInxed = movieList.indexOf(movie)
    }

    fun getMovieByIndex(position: Int): Movie {
        return movieList[position]
    }

    fun getCount(): Int {
        return movieList.size
    }

    init {
        loadMovie()
        currentMovieInxed = 0
    }

    private fun loadMovie() {
        movieList = DataStorage.getMoviesList().toMutableList()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(currentMovieInxed)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieModel> {
        override fun createFromParcel(parcel: Parcel): MovieModel {
            return MovieModel(parcel)
        }

        override fun newArray(size: Int): Array<MovieModel?> {
            return arrayOfNulls(size)
        }
    }
}