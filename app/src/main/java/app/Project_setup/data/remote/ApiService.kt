package app.Project_setup.data.remote

import app.Project_setup.data.remote.beans.RefreshTokenResponse
import app.Project_setup.data.remote.data.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

 /*   @POST(ApiConstants.socialAuth)
    suspend fun socialAuth(@Body hashMap: HashMap<String, Any>): Response<BaseResponseGeneric<SocialAuth>>*/


 /*   @Multipart
    @POST(ApiConstants.editProfile)
    suspend fun updateProfile(
        @Part uName: MultipartBody.Part,
        @Part uUserName: MultipartBody.Part,
        @Part partImage: MultipartBody.Part,
        @Part uBio: MultipartBody.Part
    ): Response<BaseResponseGeneric<Any>>*/


    @GET("user/refresh-token")
    suspend fun refreshToken(): Response<RefreshTokenResponse>



}