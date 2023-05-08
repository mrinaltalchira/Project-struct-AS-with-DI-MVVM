package app.Project_setup.utils

object ApiConstants {
    var BASEURL = "http://beyousocialapp.com/api/"
}

object PrefConstants {
    const val user = "user"
    const val authToken = "auth-token"
    const val authRefreshToken = "auth-refresh-token"
    const val fcmToken = "fcm-token"
    const val isAuth = "is-auth"
}

object RemoteConfig {
    const val configProduction = "config_production"
    const val configDevelopment = "config_development"
}

object Env {
    val environment = Environments.DEVELOPMENT
}

object FireStoreCollection{
    const val chats = "chats"
    const val messages = "messages"
    const val users = "users"
}

enum class Environments {
    DEVELOPMENT,
    PRODUCTION
}



