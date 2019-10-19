package android.academy.exercise4.ApplicationFragments

import android.academy.exercise4.R
import android.academy.exercise4.data.Movie
import android.academy.exercise4.data.Movie_To_Fragment_Key
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment


class MovieDetailsScreenFragment : Fragment() {

    companion object {
        fun newInstance(movie: Movie): MovieDetailsScreenFragment {
            val fragment = MovieDetailsScreenFragment()
            val args = Bundle()
            args.putParcelable(Movie_To_Fragment_Key, movie)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_movie_details, container, false)
        val movie: Movie = arguments?.getParcelable(Movie_To_Fragment_Key) ?: return view
        val title: TextView = view.findViewById(R.id.details_title)
        title.text = movie.name
        val description: TextView = view.findViewById(R.id.details_description)
        description.text = movie.description
        val published: TextView = view.findViewById(R.id.publishedOn)
        published.text = movie.publishedOn
        val poster: ImageView = view.findViewById(R.id.details_poster)
        val Image: Int = movie.pic_big
        val sposter: ImageView = view.findViewById(R.id.small_poster)
        val sImage: Int = movie.pic_normal
        poster.setImageResource(Image)
        sposter.setImageResource(sImage)
        return view
    }
}