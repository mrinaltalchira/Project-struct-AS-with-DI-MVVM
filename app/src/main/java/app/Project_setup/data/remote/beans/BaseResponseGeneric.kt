package app.Project_setup.data.remote.beans

open class BaseResponseGeneric<T> {
    var message: String = ""
    var status: Boolean = false
    var data: T? = null
}