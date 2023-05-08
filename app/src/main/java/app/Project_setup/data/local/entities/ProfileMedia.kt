package app.Project_setup.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "userPostMedia")
data class ProfileMedia(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id") var id: Int = 0,
    @SerializedName("media") var media: String = "",
    @SerializedName("type") var type: String = "",
    @SerializedName("postId") var postId: String = ""
) {
    constructor() : this(0, "", "")
}