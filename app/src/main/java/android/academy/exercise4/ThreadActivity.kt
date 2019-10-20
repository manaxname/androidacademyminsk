package android.academy.exercise4

import android.academy.exercise4.ApplicationFragments.CounterFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ThreadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.thread_counter_fragment,
                CounterFragment()
            )
            .commit()
    }
}
