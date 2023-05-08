package app.Project_setup.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remoteKeys")
data class EntityRemoteKeys (
    @PrimaryKey
    var repoId: Int,
    var nextKey: Int?)
