package app.Project_setup.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.Project_setup.data.local.entities.ProfileTagPeople

@Dao
interface ProfileTagedUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(profileData: ProfileTagPeople)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllData(profileData : List<ProfileTagPeople>)

    @Query("SELECT * FROM userPostTag")
    fun getAllTagPeople(): LiveData<List<ProfileTagPeople>>

    @Query("DELETE FROM userposttag")
    fun clearTagPeople()
}