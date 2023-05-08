package app.Project_setup.data.remote

import android.content.Context
import android.content.SharedPreferences
import app.Project_setup.utils.PrefConstants
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthorizationInterceptor(private val context: Context) : Interceptor {


    lateinit var sharedPreferences: SharedPreferences

    override fun intercept(chain: Interceptor.Chain): Response {
        sharedPreferences = context.getSharedPreferences("be-you", Context.MODE_PRIVATE)
        val newRequest: Request = chain.request().newBuilder()
            .addHeader("authorization", "Bearer ${sharedPreferences.getString(PrefConstants.authToken,"")}")
            .build()
        return chain.proceed(newRequest)
    }
}
