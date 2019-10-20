package android.academy.exercise4

import android.academy.exercise4.ApplicationFragments.CounterFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CoroutineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.coroutine_counter_fragment,
                CounterFragment()
            )
            .commit()
    }
}
