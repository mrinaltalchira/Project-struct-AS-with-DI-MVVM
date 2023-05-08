package app.Project_setup.core.workers

import android.content.Context
import android.content.SharedPreferences
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import app.Project_setup.R
import app.Project_setup.utils.PrefConstants
import com.google.firebase.messaging.FirebaseMessaging
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume


@HiltWorker
class FirebaseToken @AssistedInject constructor(@Assisted private val appContext: Context, @Assisted workerParams: WorkerParameters) : CoroutineWorker(appContext, workerParams) {

    lateinit var sharedPreferences: SharedPreferences

    override suspend fun doWork(): Result {
        sharedPreferences = appContext.getSharedPreferences("be-you", Context.MODE_PRIVATE)
        if (runAttemptCount > 3) {
            return Result.failure(workDataOf("message" to appContext.getString(R.string.fcm_connection_message_error)))
        }
        val token = suspendCancellableCoroutine<String> { continuation ->
            FirebaseMessaging.getInstance().token.addOnCompleteListener {
                if (it.isSuccessful) {
                    sharedPreferences.edit().putString(PrefConstants.fcmToken, it.result).apply()
                    if (continuation.isActive) {
                        continuation.resume(it.result)
                    }
                } else {
                    if (continuation.isActive) {
                        sharedPreferences.edit().putString(PrefConstants.fcmToken, it.result).apply()
                        continuation.resume("")
                    }
                }
            }
        }
        if (token.isEmpty()) {
            return Result.retry()
        }
        return Result.success(workDataOf("message" to appContext.getString(R.string.fcm_connection_message_success)))

        /*val body = hashMapOf(
            "device_id" to Settings.Secure.getString(appContext.contentResolver, Settings.Secure.ANDROID_ID),
            "token" to PrefManager.getString(BasicConstants.fcmToken)
        )

        val tokenResponse = apiService.updateFcmTokenByDeviceId(body)
        return when (tokenResponse.data!!.code) {
            "200" -> {
                Result.success(workDataOf(BasicConstants.message to appContext.getString(R.string.fcm_connection_message_success)))
            }
            else -> {
                return Result.retry()
            }
        }*/
    }
}

