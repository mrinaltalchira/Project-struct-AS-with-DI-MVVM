package app.Project_setup.utils

import android.content.Context
import android.os.Build
import android.text.Html
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import app.Project_setup.R
import java.io.IOException


object Utils {
    fun getJsonDataFromAsset(context: Context, fileName: String): String {
        return try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            ""
        }
    }

    fun getHtmlString(input: String): String {
        val description = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(
                input,
                Html.FROM_HTML_MODE_LEGACY
            ).toString()
        } else {
            Html.fromHtml(
                input
            ).toString()
        }
        return description
    }

    fun loadFragment(fragment: Fragment,supportFragmentManager:FragmentManager) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.splash_fram, fragment)
        transaction.disallowAddToBackStack()
        transaction.commit()
    }


}