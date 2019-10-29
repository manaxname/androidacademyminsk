package android.academy.exercise4.Tasks

import android.academy.exercise4.data.TaskModel

interface TaskExecuter {
    val model: TaskModel
    fun create()
    fun start(): Boolean
    fun cancel()
    fun stop()
}