package android.academy.exercise4

import android.academy.exercise4.ApplicationFragments.MovieListFragment
import android.academy.exercise4.ApplicationFragments.MoviePagerController
import android.academy.exercise4.MovieList.OnItemClickListener
import android.academy.exercise4.data.Movie
import android.academy.exercise4.data.MovieModel
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

class MoviesActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var viewModel: MovieModel

    override fun onItemClicked(movie: Movie) {
        viewModel.setCurrentMovie(movie)
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(
                R.id.movie_list_fragment, MoviePagerController.createNewPagerController(
                    viewModel
                )
            )
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        viewModel = ViewModelProviders.of(this).get(MovieModel::class.java)
        val movieListFragment = MovieListFragment()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.movie_list_fragment, movieListFragment)
            .commit()
    }
}
