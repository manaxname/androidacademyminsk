package android.academy.exercise4

import android.academy.exercise4.ApplicationFragments.MovieListFragment
import android.academy.exercise4.ApplicationFragments.MoviePagerController
import android.academy.exercise4.MovieList.OnItemClickListener
import android.academy.exercise4.data.Movie
import android.academy.exercise4.data.MovieModel
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import android.view.Menu
import android.widget.Toast
import android.view.MenuItem


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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.global_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.open_coroutines_activity -> startActivity(
                Intent(
                    this,
                    CoroutineActivity::class.java
                )
            )
            R.id.open_thread_handler_activity -> startActivity(
                Intent(
                    this,
                    ThreadActivity::class.java
                )
            )
        }
        return super.onOptionsItemSelected(item)
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
