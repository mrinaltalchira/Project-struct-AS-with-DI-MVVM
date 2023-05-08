package app.Project_setup.data.local.dao

import androidx.room.*
import app.Project_setup.data.local.entities.EntityUsers

@Dao
interface Users {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(entityMusic: EntityUsers): Long

    @Query("SELECT * FROM users LIMIT 1")
    fun getUser(): EntityUsers

    @Update
    suspend fun update(obj: EntityUsers)

    @Query("DELETE FROM users")
    suspend fun clearUser()
}