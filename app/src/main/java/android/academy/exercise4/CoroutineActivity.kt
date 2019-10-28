package android.academy.exercise4

import android.academy.exercise4.ApplicationFragments.CounterFragment
import android.academy.exercise4.data.Coroutine_Fragment_Key
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class CoroutineActivity : AppCompatActivity() {
    private lateinit var fragment: CounterFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)
        val savedFragment = getCounterFragment(savedInstanceState)
        if (savedFragment != null) {
            fragment = savedFragment
            return
        }
        fragment = CounterFragment()
        supportFragmentManager
            .beginTransaction()
            .add(
                R.id.coroutine_counter_fragment,
                fragment
            )
            .commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        supportFragmentManager.putFragment(outState, Coroutine_Fragment_Key, fragment)
    }

    private fun getCounterFragment(savedInstanceState: Bundle?): CounterFragment? {
        if (savedInstanceState == null)
            return null

        return supportFragmentManager.getFragment(
            savedInstanceState,
            Coroutine_Fragment_Key
        ) as? CounterFragment
    }
}
