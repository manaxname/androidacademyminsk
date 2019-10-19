package android.academy.exercise4.ApplicationFragments

import android.academy.exercise4.R
import android.academy.exercise4.data.Movie
import android.academy.exercise4.data.Movie_Current_Controller_Key
import android.academy.exercise4.data.Movie_List_To_Controller_Key
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
            Movies: ArrayList<Movie>,
            currentMovie: Movie
        ): MoviePagerController {
            val args = Bundle()
            args.putParcelableArrayList(Movie_List_To_Controller_Key, Movies)
            args.putParcelable(Movie_Current_Controller_Key, currentMovie)
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
        val movieList: java.util.ArrayList<Movie> =
            arguments?.getParcelableArrayList<Movie>(Movie_List_To_Controller_Key)
                ?: return view
        movies = movieList
        val currentMovie: Movie =
            arguments?.getParcelable(Movie_Current_Controller_Key) ?: movies[0]
        mPager = view.findViewById(R.id.movie_pager_container)
        val pagerAdapter = MoviePagerAdapter(childFragmentManager, movies)
        mPager.adapter = pagerAdapter
        mPager.currentItem = movies.indexOf(currentMovie)
        return view
    }

    /*override fun onAttach(context: Context?) {
        super.onAttach(context)
        val activity: AppCompatActivity? = context as? AppCompatActivity
        if (activity != null) {
            fManager = activity.childFragmentManager
        }
    }*/
    /*override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        myContext = activity as FragmentActivity?
    }*/

    private inner class MoviePagerAdapter(fm: FragmentManager, val movies: List<Movie>) :
        FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = movies.size

        override fun getItem(position: Int): Fragment =
            MovieDetailsScreenFragment.newInstance(movies[position])
    }
}