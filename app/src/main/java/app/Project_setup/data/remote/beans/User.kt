package app.Project_setup.data.remote.beans

import com.google.gson.annotations.SerializedName

data class User(
    var name:String = "",
    @SerializedName("username")
    var userName:String = "",
    @SerializedName("phone_no")
    var phoneNumber:String = "",
    @SerializedName("country_code")
    var countryCode:Int = 1,
    var password:String = "",
    @SerializedName("device_type")
    var deviceType:Int = 1,
    @SerializedName("device_token")
    var deviceToken:String = "1", //TODO valid device token
    var email:String = "",
){
    class Data

    fun toEmailLoginHashMap() = hashMapOf<String,Any>("name" to name,"username" to userName,"password" to password,"device_type" to deviceType , "device_token" to deviceToken , "password" to password , "email" to email)
    fun toPhoneLoginHashMap() = hashMapOf<String,Any>("name" to name,"username" to userName,"phone_no" to phoneNumber.toLong(),"country_code" to countryCode,"password" to password,"device_type" to deviceType , "device_token" to deviceToken , "password" to password)
}