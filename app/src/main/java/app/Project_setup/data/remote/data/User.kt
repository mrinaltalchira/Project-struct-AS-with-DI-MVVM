package app.Project_setup.data.remote.data


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("bio")
    val bio: String,
    @SerializedName("country_code")
    val countryCode: Int,
    @SerializedName("device_token")
    val deviceToken: String,
    @SerializedName("device_type")
    val deviceType: Int,
    @SerializedName("followers")
    val followers: Int,
    @SerializedName("following")
    val following: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone_no")
    val phoneNo: Long,
    @SerializedName("postCount")
    val postCount: Int,
    @SerializedName("profile")
    val profile: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("username")
    val username: String
)