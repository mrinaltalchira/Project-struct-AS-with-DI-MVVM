package app.Project_setup.data.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import app.Project_setup.data.local.entities.ProfileMedia
import app.Project_setup.data.local.entities.ProfilePostAndMedia
import app.Project_setup.data.local.entities.ProfilePostData

@Dao
interface ProfilePostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(profileData: ProfilePostData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDataMedia(profileMedia : List<ProfileMedia>)

    @Query("SELECT * FROM userPost")
    fun getAllData(): LiveData<List<ProfilePostData>>

    @Query("DELETE FROM userPost")
    fun clearData()

    @Query("SELECT * FROM userPost")
    fun getPostPagingSource() : PagingSource<Int, ProfilePostData>

    @Query("SELECT * FROM userPostMedia")
    fun getAllMedia() : List<ProfileMedia>

    @Transaction
    @Query("SELECT * FROM userPost WHERE postId=:postId")
    fun getDataProfileAndMedia(postId : String) : List<ProfilePostAndMedia>


//    @Query("SELECT * FROM userPost WHERE user_id=:userId and post_type=:type ORDER BY timestamp DESC")
//    fun getUserPostPagingSource(userId : String,type : String) : PagingSource<Int, ProfilePostData>
}