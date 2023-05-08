package app.Project_setup.data.local

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.ByteArrayOutputStream
import java.lang.reflect.Type

class Converters {

    val gson = Gson()

    @TypeConverter
    fun fromBitmap(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }

    @TypeConverter
    fun toBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

    @TypeConverter
    fun from(list: List<String>?): String? {
        val string = if (list == null) null else gson.toJson(list)
        return string
    }

    @TypeConverter
    fun to(jsonData: String?): List<String>? {
        val gson = Gson()
        val listType: Type = object : TypeToken<List<String?>?>() {}.type
        return gson.fromJson(jsonData, listType)
    }

    /*@TypeConverter
    fun mediaToString(list: List<Posts.Media>?): String? {
        val gson = Gson()
        return if (list == null) null else gson.toJson(list)
    }

    @TypeConverter
    fun stringToMedia(jsonData: String?): List<Posts.Media>? {
        val gson = Gson()
        val listType: Type = object : TypeToken<List<Posts.Media?>?>() {}.type
        return gson.fromJson(jsonData, listType)
    }

    @TypeConverter
    fun tagPeopleToString(list: List<Posts.TagPeople>?): String? {
        val gson = Gson()
        return if (list == null) null else gson.toJson(list)
    }

    @TypeConverter
    fun stringToTagPeople(jsonData: String?): List<Posts.TagPeople>? {
        val gson = Gson()
        val listType: Type = object : TypeToken<List<Posts.TagPeople?>?>() {}.type
        return gson.fromJson(jsonData, listType)
    }*/
}