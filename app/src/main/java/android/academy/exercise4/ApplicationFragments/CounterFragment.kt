package android.academy.exercise4.ApplicationFragments


import android.academy.exercise4.R
import android.academy.exercise4.Tasks.CounterCoroutinesTask
import android.academy.exercise4.Tasks.TaskExecuter
import android.academy.exercise4.Tasks.TaskStatus
import android.academy.exercise4.data.TaskModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class CounterFragment : Fragment() {
    private lateinit var taskExecuter: TaskExecuter
    private lateinit var resultWidget: TextView
    private lateinit var taskModel: TaskModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        taskModel = activity!!.run {
            ViewModelProviders.of(this)[TaskModel::class.java]
        }
        taskExecuter = CounterCoroutinesTask(taskModel)
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
        taskModel.currentValue.observe(this, Observer<Int> { item ->
            resultWidget.text = item.toString()
        })
        taskModel.status.observe(this, Observer<TaskStatus> {
            status ->
            when(status){
                TaskStatus.Started -> resultWidget.text = "Start"
                TaskStatus.Stopped -> resultWidget.text = "Canceled"
                TaskStatus.Created -> resultWidget.text = "Ready!"
                TaskStatus.Finished -> resultWidget.text = "Done"
                TaskStatus.Unknown -> resultWidget.text = "Task isn't created"
            }
        })
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        taskExecuter.cancel(true)
    }

    private fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }
}
