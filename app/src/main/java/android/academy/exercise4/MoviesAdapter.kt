package android.academy.exercise4

import android.academy.exercise4.data.Movie
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface OnItemClickListener{
    fun onItemClicked(movie: Movie)
}

class MoviesAdapter(var movies: List<Movie>, val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.activity_movie,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position], itemClickListener)
    }

}