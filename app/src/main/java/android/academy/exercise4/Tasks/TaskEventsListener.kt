package android.academy.exercise4.Tasks

interface TaskEventsListener {
    fun onPreExecute()
    fun onPostExecute()
    fun onProgressUpdate(progress: Int)
}