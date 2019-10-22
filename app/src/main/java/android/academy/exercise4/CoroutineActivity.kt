package android.academy.exercise4

import android.academy.exercise4.ApplicationFragments.CounterFragment
import android.academy.exercise4.Tasks.CounterCoroutinesTask
import android.academy.exercise4.Tasks.TaskEventsListener
import android.academy.exercise4.Tasks.TaskExecuter
import android.academy.exercise4.Tasks.TaskExecutorFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CoroutineActivity : AppCompatActivity(), TaskExecutorFactory {
    override fun getTaskExecutor(listener: TaskEventsListener): TaskExecuter {
        return CounterCoroutinesTask(listener)
    }

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
