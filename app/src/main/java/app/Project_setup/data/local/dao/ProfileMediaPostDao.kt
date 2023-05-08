package app.Project_setup.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.Project_setup.data.local.entities.ProfileMedia


@Dao
interface ProfileMediaPostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(profileMedia: ProfileMedia)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllData(profileMedia : List<ProfileMedia>)

    @Query("SELECT * FROM userPostMedia")
    fun getAllMedia(): LiveData<List<ProfileMedia>>

    @Query("DELETE FROM userpostmedia")
    fun clearMedia()

}