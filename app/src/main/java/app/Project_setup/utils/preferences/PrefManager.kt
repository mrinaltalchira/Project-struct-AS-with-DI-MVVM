package app.Project_setup.utils.preferences

import android.content.SharedPreferences
import app.Project_setup.utils.extensions.emptyString
import javax.inject.Inject

class PrefManager @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun put(key: String, value: Any) {
        when (value) {
            is Int -> {
                sharedPreferences.edit().putInt(key, value).apply()
            }
            is Float -> {
                sharedPreferences.edit().putFloat(key, value).apply()
            }
            is Boolean -> {
                sharedPreferences.edit().putBoolean(key, value).apply()
            }
            is String -> {
                sharedPreferences.edit().putString(key, value).apply()
            }
        }
    }

    fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }

    fun getFloat(key: String): Float {
        return sharedPreferences.getFloat(key, 0f)
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun getString(key: String): String {
        return sharedPreferences.getString(key, emptyString())!!
    }

    fun clear(){
        sharedPreferences.edit().clear().apply()
    }
}

