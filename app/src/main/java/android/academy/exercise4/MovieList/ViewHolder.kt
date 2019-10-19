package android.academy.exercise4.MovieList

import android.academy.exercise4.R
import android.academy.exercise4.data.Movie
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val poster: ImageView = itemView.findViewById(R.id.poster)
    private val title: TextView = itemView.findViewById(R.id.title)
    private val description: TextView = itemView.findViewById(R.id.description)

    fun bind(movie: Movie, clickListener: OnItemClickListener?) {
        poster.setImageResource(movie.pic_normal)
        title.text = movie.name
        description.text = movie.description

        itemView.setOnClickListener {
            clickListener?.onItemClicked(movie)
        }
    }
}