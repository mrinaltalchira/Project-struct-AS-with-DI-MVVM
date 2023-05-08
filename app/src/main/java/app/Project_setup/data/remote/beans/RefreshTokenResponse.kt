package app.Project_setup.data.remote.beans

data class RefreshTokenResponse(val data:RefreshTokenData):BaseResponse()

data class RefreshTokenData(val token:String)