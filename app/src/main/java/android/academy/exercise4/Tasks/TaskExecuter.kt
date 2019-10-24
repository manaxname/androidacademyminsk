package android.academy.exercise4.Tasks

interface TaskExecuter {
    var currentValue: Int
    val isInProcess: Boolean
    fun create()
    fun start(): Boolean
    fun cancel(cancelContext: Boolean)
}