package app.Project_setup.core

import android.content.Context
import android.content.SharedPreferences
import app.Project_setup.utils.PrefConstants
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FCMService : FirebaseMessagingService() {

    private lateinit var sharedPreferences: SharedPreferences


    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        sharedPreferences = getSharedPreferences("be-you", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(PrefConstants.fcmToken,token).apply()
    }
}