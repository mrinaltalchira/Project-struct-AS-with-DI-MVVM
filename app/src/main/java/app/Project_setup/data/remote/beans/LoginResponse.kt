package app.Project_setup.data.remote.beans

data class LoginResponse(val data: LoginData) : BaseResponse()

data class LoginData(
    val userDetails: User,
    val token: String,
    val refreshToken: String,
)