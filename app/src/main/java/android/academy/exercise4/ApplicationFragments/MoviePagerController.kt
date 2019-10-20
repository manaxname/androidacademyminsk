package android.academy.exercise4.ApplicationFragments

import android.academy.exercise4.R
import android.academy.exercise4.data.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager


class MoviePagerController : Fragment() {
    private lateinit var mPager: ViewPager
    private var movies: ArrayList<Movie> = arrayListOf()

    companion object {
        fun createNewPagerController(
            model: MovieModel
        ): MoviePagerController {
            val args = Bundle()
            args.putParcelable(Movie_Model_To_Controller_Key, model)
            val fragment = MoviePagerController()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.movie_pager, container, false)
        val movieModel: MovieModel =
            arguments?.getParcelable(Movie_Model_To_Controller_Key)
                ?: return view
        movies = movieModel.getMovies()
        val currentMovie: Movie = movieModel.getCurrentMovie()
        mPager = view.findViewById(R.id.movie_pager_container)
        val pagerAdapter = MoviePagerAdapter(childFragmentManager, movies)
        mPager.adapter = pagerAdapter
        mPager.currentItem = movies.indexOf(currentMovie)
        return view
    }

    private inner class MoviePagerAdapter(fm: FragmentManager, val movies: List<Movie>) :
        FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = movies.size

        override fun getItem(position: Int): Fragment =
            MovieDetailsScreenFragment.newInstance(movies[position])
    }
}