package app.Project_setup.utils.extensions

import android.util.Log
import com.google.gson.Gson

fun Any.logE(tag: String = "", message: String) = Log.e(tag, message)

fun Any.logE(message: String) = Log.e("Be-You", message)

fun Any.logD(tag: String = "", message: String) = Log.d(tag, message)

fun Any.logD(message: String) = Log.d("Be-You", message)

fun Any.logV(tag: String = "", message: String) = Log.v(tag, message)

fun Any.logV(message: String) = Log.v("Be-You", message)

fun Any.logW(tag: String = "", message: String) = Log.w(tag, message)

fun Any.logW(message: String) = Log.w("Be-You", message)

fun Any.logI(tag: String = "", message: String) = Log.i(tag, message)

fun Any.logI(message: String) = Log.i("Be-You", message)

fun Any.emptyString() = ""

fun <T> String.toObject(targetClass: Class<T>): T {
    val gson = Gson()
    return gson.fromJson(this, targetClass)
}
