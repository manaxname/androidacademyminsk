package android.academy.exercise4.ApplicationFragments


import android.academy.exercise4.MovieList.MoviesAdapter
import android.academy.exercise4.MovieList.OnItemClickListener
import android.academy.exercise4.R
import android.academy.exercise4.data.DataStorage
import android.academy.exercise4.data.Movie
import android.academy.exercise4.data.MovieModel
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView


class MovieListFragment : Fragment() {

    private var listener: OnItemClickListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnItemClickListener) {
            listener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_movie_list, container, false)
        val moviesList: RecyclerView = view.findViewById(R.id.moviesList)
        val movieModel = activity?.let { ViewModelProviders.of(it).get(MovieModel::class.java) }
        val movies: List<Movie> = movieModel!!.getMovies()
        moviesList.adapter = MoviesAdapter(movies, listener)
        return view
    }


}
