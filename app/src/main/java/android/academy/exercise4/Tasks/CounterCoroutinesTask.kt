package android.academy.exercise4.Tasks

import android.academy.exercise4.Tasks.TaskStatus.*
import android.academy.exercise4.data.TaskModel
import android.academy.exercise4.data.Task_Init_Value
import kotlinx.coroutines.*

class CounterCoroutinesTask(
    override val model: TaskModel
) : CoroutineScope,
    TaskExecuter {

    private var task: Job? = null

    override fun create() {
        task = launch(context = Dispatchers.Default, start = CoroutineStart.LAZY) {

            val startValue: Int = if (model.currentValue.value == null || model.currentValue.value == 0) Task_Init_Value else model.currentValue.value!!
            for (times in startValue downTo 0) {
                launch(Dispatchers.Main) {
                    model.currentValue.value = times
                }
                delay(1000)
            }
            launch(Dispatchers.Main) {
                model.status.value = Finished
            }
        }
        model.status.value = Created
    }

    override fun start(): Boolean {
        if (task == null)
            return false
        model.status.value = Started
        return task!!.start()
    }

    override fun cancel(cancelContext: Boolean) {
        task?.cancel()
        if (cancelContext)
            coroutineContext.cancel()
        model.status.value = Stopped
    }

    override val coroutineContext = SupervisorJob()
}