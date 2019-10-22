package android.academy.exercise4.Tasks

import kotlinx.coroutines.*

class CounterCoroutinesTask(private val listener: TaskEventsListener) : CoroutineScope,
    TaskExecuter {

    private var task: Job? = null

    override fun create() {
        task = launch(context = Dispatchers.Default, start = CoroutineStart.LAZY) {

            for (time in 10 downTo 1) {
                launch(Dispatchers.Main) {
                    listener.onProgressUpdate(time)
                }
                delay(1000)
            }
            launch(Dispatchers.Main) {
                listener.onPostExecute()
            }
        }
    }

    override fun start(): Boolean {
        if (task == null)
            return false
        listener.onPreExecute()
        return task!!.start()
    }

    override fun cancel(cancelContext: Boolean) {
        task?.cancel()
        if (cancelContext)
            coroutineContext.cancel()
        listener.onPostExecute()
    }

    override val coroutineContext = SupervisorJob()
}