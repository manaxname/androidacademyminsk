package android.academy.exercise4.Tasks

import android.academy.exercise4.data.Task_Init_Value
import kotlinx.coroutines.*

class CounterCoroutinesTask(
    private val listener: TaskEventsListener,
    override var currentValue: Int = 0,
    override var isInProcess: Boolean = false
) : CoroutineScope,
    TaskExecuter {

    private var task: Job? = null

    override fun create() {
        task = launch(context = Dispatchers.Default, start = CoroutineStart.LAZY) {

            val startValue = if (currentValue == 0) Task_Init_Value else currentValue
            for (times in startValue downTo 0) {
                launch(Dispatchers.Main) {
                    currentValue = times
                    listener.onProgressUpdate(times)
                }
                delay(1000)
            }
            launch(Dispatchers.Main) {
                isInProcess = false
                listener.onPostExecute()
            }
        }
    }

    override fun start(): Boolean {
        if (task == null)
            return false
        listener.onPreExecute()
        isInProcess = true
        return task!!.start()
    }

    override fun cancel(cancelContext: Boolean) {
        task?.cancel()
        if (cancelContext)
            coroutineContext.cancel()
        isInProcess = false
        listener.onPostExecute()
    }

    override val coroutineContext = SupervisorJob()
}