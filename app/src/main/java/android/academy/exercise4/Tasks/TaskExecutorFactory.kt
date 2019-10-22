package android.academy.exercise4.Tasks

interface TaskExecutorFactory {
    fun getTaskExecutor(listener: TaskEventsListener) : TaskExecuter
}