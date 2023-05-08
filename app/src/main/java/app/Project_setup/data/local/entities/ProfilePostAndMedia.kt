package app.Project_setup.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class ProfilePostAndMedia(

    @Embedded val profilePostData: ProfilePostData,

    @Relation(
        parentColumn = "postId",
        entityColumn = "postId"
    )
    val profileMedia: ProfileMedia
)