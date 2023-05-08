package app.Project_setup.core.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import app.Project_setup.R
import app.Project_setup.utils.ApiConstants
import app.Project_setup.utils.Env
import app.Project_setup.utils.Environments
import app.Project_setup.utils.RemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.suspendCancellableCoroutine
import org.json.JSONObject
import kotlin.coroutines.resume

@HiltWorker
class FirebaseRemoteConfig @AssistedInject constructor(@Assisted private val appContext: Context,@Assisted workerParams: WorkerParameters) : CoroutineWorker(appContext, workerParams) {

    var isTrying = false

    override suspend fun doWork(): Result {
        if (runAttemptCount > 3) {
            return Result.failure(workDataOf("message" to appContext.getString(R.string.fcm_connection_message_error)))
        }
        val remoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 0
        }
        var isConfigFetched = false
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.firebase_api_url)

        isConfigFetched = suspendCancellableCoroutine<Boolean> { continuation ->
            remoteConfig.fetchAndActivate().addOnCompleteListener {
                if (it.isSuccessful) {
                    val configProduction = remoteConfig.getString(RemoteConfig.configProduction)
                    val configDevelopment = remoteConfig.getString(RemoteConfig.configDevelopment)
                    if (Env.environment == Environments.PRODUCTION) {
                        val objProduction = JSONObject(configProduction)
                        ApiConstants.BASEURL = objProduction.getString("base_url")
                    } else {
                        val objDevelopment = JSONObject(configDevelopment)
                        ApiConstants.BASEURL = objDevelopment.getString("base_url")
                    }
                    if(continuation.isActive){
                        continuation.resume(true)
                    }
                } else {
                    if(continuation.isActive){
                        continuation.resume(false)
                    }
                }
            }.addOnFailureListener {
                if(continuation.isActive){
                    continuation.resume(false)
                }
            }
        }

        return if (isConfigFetched) {
            Result.success(workDataOf("message" to appContext.getString(R.string.fcm_connection_message_success)))
        } else {
            Result.retry()
        }

    }
}