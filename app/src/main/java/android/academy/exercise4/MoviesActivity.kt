package android.academy.exercise4

import android.academy.exercise4.ApplicationFragments.MovieListFragment
import android.academy.exercise4.ApplicationFragments.MoviePagerController
import android.academy.exercise4.MovieList.OnItemClickListener
import android.academy.exercise4.data.DataStorage
import android.academy.exercise4.data.MOVIE_POSITION_MESSAGE
import android.academy.exercise4.data.Movie
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MoviesActivity : AppCompatActivity(), OnItemClickListener {


    override fun onItemClicked(movie: Movie) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.movie_list_fragment, MoviePagerController.createNewPagerController(
                ArrayList(DataStorage.getMoviesList()), movie))
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        val movieListFragment = MovieListFragment()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.movie_list_fragment, movieListFragment)
            .commit()
    }
}
