package app.Project_setup.data.local

import app.Project_setup.data.local.entities.EntityUsers
import javax.inject.Inject

class DBRepository @Inject constructor(private val appDatabase: AppDatabase) {

    suspend fun saveUser(user: EntityUsers): Long {
        return appDatabase.daoUsers().saveUser(user)
    }

    suspend fun updateUser(entityUsers: EntityUsers) {
        appDatabase.daoUsers().update(entityUsers)
    }

    suspend fun clearUser() = appDatabase.daoUsers().clearUser()

}