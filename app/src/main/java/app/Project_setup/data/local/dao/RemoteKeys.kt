package app.Project_setup.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.Project_setup.data.local.entities.EntityRemoteKeys


@Dao
interface RemoteKeys {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: EntityRemoteKeys)

    @Query("SELECT * FROM remoteKeys WHERE repoId = :id")
    suspend fun remoteKeyByQuery(id: Int): EntityRemoteKeys

    @Query("DELETE FROM remoteKeys WHERE repoId = :id")
    suspend fun deleteByQuery(id: Int)
}