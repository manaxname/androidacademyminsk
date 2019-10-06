package android.academy.exercise4

import android.R.id
import android.content.ActivityNotFoundException
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
        val intent = intent
        val extras = intent.extras
        val title: TextView = findViewById(R.id.details_title)
        title.text = extras?.getString("title")
        val description: TextView = findViewById(R.id.details_description)
        description.text = extras?.getString("description")
        val published: TextView = findViewById(R.id.publishedOn)
        published.text = extras?.getString("published")
        val poster: ImageView = findViewById(R.id.details_poster)
        val image: Int? = extras?.getInt("poster")
        if (image != null)
            poster.setImageResource(image)
        val url : String? = extras?.getString("url")
        if (url != null) {
            poster.setOnClickListener {
                val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(webIntent)
            }
        }
    }
}
