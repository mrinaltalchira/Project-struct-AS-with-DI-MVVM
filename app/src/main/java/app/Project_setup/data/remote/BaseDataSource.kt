package app.Project_setup.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import app.Project_setup.data.remote.beans.BaseResponseGeneric
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

@Suppress("IMPLICIT_CAST_TO_ANY")
abstract class BaseDataSource {

    protected suspend fun <T> getResult(tag: String, call: suspend () -> Response<BaseResponseGeneric<T>>): Resource<BaseResponseGeneric<T>> {
        try {
            val response = call()
            when (response.code()) {
                200 -> {
                    return if(response.body()!!.status){
                        Resource.success(tag, response.body()!!)
                    }else{
                        Resource.error(tag, response.body()!!.message)
                    }
                }
                400 -> {
                    return Resource.error(tag, "oops page that you request not found.")
                }
                404 -> {
                    return Resource.error(tag, "oops page that you request is not found.")
                }
                500 -> {
                    return Resource.error(tag, "Server not responding.")
                }
                else -> {
                    response.errorBody()?.let {
                        return Resource.error(tag, response.errorBody().toString())
                    }
                    return Resource.error(tag, "Unknown error.")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return Resource.error(tag, e.message ?: e.toString())
        }
    }

    fun <T> performFlowOperation(tag: String, networkCall: suspend () -> Resource<T>): Flow<Resource<T?>> = flow {
        try {
            emit(Resource.loading(tag, data = null))
            val responseStatus = networkCall.invoke()
            emit(responseStatus)
        } catch (e: Exception) {
            emit(Resource.error<T>(tag, e.message ?: e.toString()))
        }
    }.flowOn(Dispatchers.Main)

    fun <T> performOperation(tag: String, networkCall: suspend () -> Resource<T>): LiveData<Resource<T?>> = liveData(Dispatchers.IO) {
        try {
            emit(Resource.loading(tag, data = null))
            val responseStatus = networkCall.invoke()
            emit(responseStatus)
        } catch (e: Exception) {
            emit(Resource.error<T>(tag, e.message ?: e.toString()))
        }
    }

    data class Resource<out T>(val status: Status, val data: T?, val message: String?, val tag: String) {
        enum class Status {
            SUCCESS,
            ERROR,
            LOADING
        }

        companion object {
            fun <T> success(tag: String, data: T): Resource<T> {
                return Resource(Status.SUCCESS, data, null, tag)
            }

            fun <T> error(tag: String, message: String?): Resource<T> {
                return Resource(Status.ERROR, null, message, tag)
            }

            fun <T> loading(tag: String, data: T? = null): Resource<T> {
                return Resource(Status.LOADING, data, null, tag)
            }
        }
    }

}

