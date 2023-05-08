package app.Project_setup.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.Project_setup.data.local.dao.RemoteKeys
import app.Project_setup.data.local.dao.Users
import app.Project_setup.data.local.entities.*

@Database(entities =[EntityUsers::class,EntityRemoteKeys::class,ProfilePostData::class,ProfileMedia::class,ProfileTagPeople::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun daoUsers(): Users

    abstract fun daoRemoteKeys(): RemoteKeys

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase = instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "be-you")
                .fallbackToDestructiveMigration().build()
    }

}

