package app.Project_setup.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "userPostTag")
data class ProfileTagPeople(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("user_id") var userId: Int = 0,
    @SerializedName("name") var name: String = "",
    @SerializedName("profile") var profile: String = "",
    @SerializedName("username") var username: String = ""
) {
    constructor() : this(0, "", "", "")
}