/*
package app.Project_setup.data.remote.dataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import app.Project_setup.data.remote.ApiService
import retrofit2.HttpException
import java.io.IOException

class ProfileTextPaging(private val apiServiceImpl: ApiService,val otherUserId: String) : PagingSource<Int, ProfileText.Usertextdetail>(){

    var startPage = 1

    override fun getRefreshKey(state: PagingState<Int, ProfileText.Usertextdetail>): Int {
        return 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProfileText.Usertextdetail> {
        val page = params.key ?: startPage
        return try {
            val response = apiServiceImpl.getProfileText(
                hashMapOf(
                    "other_user_id" to otherUserId,
                    "page" to page
            ))

            val data = response.body()!!.data!!.usertextdetails
            LoadResult.Page(
                data = data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (data.isEmpty()) null else page + 1)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: Exception) {
            e.printStackTrace()
            return LoadResult.Error(e)
        }
    }

}*/
