package app.Project_setup.data.local.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "userPost")
data class ProfilePostData(

    @SerializedName("userId") var userId: Int = 0,
    @SerializedName("commentCount") var commentCount: Int = 0,
    @SerializedName("created_at") var createdAt: Long = 0,
    @SerializedName("description") var description: String = "",
    @SerializedName("hide_like_views_count") var hideLikeViewsCount: Boolean = false,
    @SerializedName("is_following") var isFollowing: Boolean = false,
    @SerializedName("is_like") var isLike: Boolean = false,
    @SerializedName("likeCount") var likeCount: Int = 0,
    @SerializedName("location_address") var locationAddress: String = "",
    @Ignore @SerializedName("media") var media: List<ProfileMedia>? = null,
    @PrimaryKey(autoGenerate = false) @SerializedName("postId") var postId: Int = 0,
    @SerializedName("post_type") var postType: String = "",
    @SerializedName("profile") var profile: String = "",
    @Ignore @SerializedName("tagpeople") var tagpeople: List<ProfileTagPeople>? = null,
    @SerializedName("username") var username: String = ""
) {
    constructor() : this(
        0, 0, 0, "", false, false, false, 0, "",
        null, 0, "", "", null, ""
    )
}