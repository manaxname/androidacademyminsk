package android.academy.exercise4.ApplicationFragments


import android.academy.exercise4.R
import android.academy.exercise4.Tasks.TaskEventsListener
import android.academy.exercise4.Tasks.TaskExecuter
import android.academy.exercise4.Tasks.TaskExecutorFactory
import android.academy.exercise4.data.Task_Current_Value
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class CounterFragment : Fragment(), TaskEventsListener {
    private lateinit var taskExecuter: TaskExecuter
    private lateinit var resultWidget: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskExecuter = (activity as? TaskExecutorFactory)?.getTaskExecutor(this)
            ?: throw Exception("Activity should implement TaskExecutorFactory")
    }

    override fun onPreExecute() {
        resultWidget.text = "Started!"
    }

    override fun onPostExecute() {
        resultWidget.text = "Done!"
    }

    override fun onProgressUpdate(progress: Int) {
        resultWidget.text = "$progress"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_counter, container, false)
        val createButton: Button = view.findViewById(R.id.create)
        val startButton: Button = view.findViewById(R.id.start)
        val cancelButton: Button = view.findViewById(R.id.cancel)
        resultWidget = view.findViewById(R.id.threadStatus)
        createButton.setOnClickListener {
            taskExecuter.create()
        }
        startButton.setOnClickListener {
            val isStarted: Boolean? = taskExecuter.start()
            if (isStarted == null) {
                showMessage("TaskExecuter in null")
                return@setOnClickListener
            }
            if (isStarted == false)
                showMessage("Task is not created")
        }
        cancelButton.setOnClickListener {
            taskExecuter.cancel(false)
        }
        resultWidget.text = "Ready!"
        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (taskExecuter.isInProcess)
            outState.putInt(Task_Current_Value, taskExecuter.currentValue)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState != null) {
            taskExecuter.currentValue = savedInstanceState.getInt(Task_Current_Value)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        taskExecuter.cancel(true)
    }

    private fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }
}
