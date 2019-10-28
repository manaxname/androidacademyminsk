package android.academy.exercise4.data

import android.academy.exercise4.Tasks.TaskStatus
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskModel: ViewModel() {
    val currentValue = MutableLiveData<Int>()
    val status = MutableLiveData<TaskStatus>()
}