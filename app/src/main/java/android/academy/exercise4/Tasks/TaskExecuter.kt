package android.academy.exercise4.Tasks

interface TaskExecuter {
    fun create()
    fun start(): Boolean
    fun cancel(cancelContext: Boolean)
}