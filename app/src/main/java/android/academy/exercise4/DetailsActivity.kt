package android.academy.exercise4

import android.academy.exercise4.data.Movie
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val movie: Movie = intent.getParcelableExtra(MOVIE_POSITION_MESSAGE) ?: return
        val title: TextView = findViewById(R.id.details_title)
        title.text = movie.name
        val description: TextView = findViewById(R.id.details_description)
        description.text = movie.description
        val published: TextView = findViewById(R.id.publishedOn)
        published.text = movie.publishedOn
        val poster: ImageView = findViewById(R.id.details_poster)
        val image: Int = movie.pic_big
        poster.setImageResource(image)
        poster.setOnClickListener {
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(movie.url))
            startActivity(webIntent)
        }
    }
}
