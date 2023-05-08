package app.Project_setup.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class EntityUsers(
    @PrimaryKey
    var id: Int,

    @ColumnInfo(name="name")
    var name: String? = "",

    @ColumnInfo(name="username")
    var username: String? = "",

    @ColumnInfo(name="email")
    var email: String? = "",

    @ColumnInfo(name="bio")
    var bio: String? = "",

    @ColumnInfo(name="profile")
    var profile: String? = "",

    @ColumnInfo(name="followers")
    var followers: Int = 0,

    @ColumnInfo(name="following")
    var following: Int = 0,

    @ColumnInfo(name="postCount")
    var post_count: Int = 0
)
