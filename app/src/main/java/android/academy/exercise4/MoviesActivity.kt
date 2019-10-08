package android.academy.exercise4

import android.academy.exercise4.data.DataStorage
import android.academy.exercise4.data.Movie
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

const val MOVIE_POSITION_MESSAGE = "android.academy.movie_position"

class MoviesActivity : AppCompatActivity(), OnItemClickListener {

    override fun onItemClicked(movie: Movie) {
        val detailsActivity = Intent(this, DetailsActivity::class.java)
        detailsActivity.putExtra(MOVIE_POSITION_MESSAGE, movie)
        startActivity(detailsActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        val moviesList: RecyclerView = findViewById(R.id.moviesList)
        val movies: List<Movie> = DataStorage.getMoviesList()
        moviesList.adapter = MoviesAdapter(movies, this)
    }
}
