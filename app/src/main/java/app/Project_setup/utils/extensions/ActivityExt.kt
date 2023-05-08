package app.Project_setup.utils.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.provider.Settings
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import java.io.*

fun Activity.showToast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, message, duration).show()

inline fun <reified T : Activity> Activity.openActivity(vararg params: Pair<String, Any>) {
    val intent = Intent(this, T::class.java)
    intent.putExtras(*params)
    this.startActivity(intent)
}

fun Intent.putExtras(vararg params: Pair<String, Any>): Intent {
    if (params.isEmpty()) return this
    params.forEach { (key, value) ->
        when (value) {
            is Int -> putExtra(key, value)
            is Byte -> putExtra(key, value)
            is Char -> putExtra(key, value)
            is Long -> putExtra(key, value)
            is Float -> putExtra(key, value)
            is Short -> putExtra(key, value)
            is Double -> putExtra(key, value)
            is Boolean -> putExtra(key, value)
            is Bundle -> putExtra(key, value)
            is String -> putExtra(key, value)
            is IntArray -> putExtra(key, value)
            is ByteArray -> putExtra(key, value)
            is CharArray -> putExtra(key, value)
            is LongArray -> putExtra(key, value)
            is FloatArray -> putExtra(key, value)
            is Parcelable -> putExtra(key, value)
            is ShortArray -> putExtra(key, value)
            is DoubleArray -> putExtra(key, value)
            is BooleanArray -> putExtra(key, value)
            is CharSequence -> putExtra(key, value)
            is Array<*> -> {
                when {
                    value.isArrayOf<String>() ->
                        putExtra(key, value as Array<String?>)
                    value.isArrayOf<Parcelable>() ->
                        putExtra(key, value as Array<Parcelable?>)
                    value.isArrayOf<CharSequence>() ->
                        putExtra(key, value as Array<CharSequence?>)
                    else -> putExtra(key, value)
                }
            }
            is Serializable -> putExtra(key, value)
        }
    }
    return this
}

fun Activity.setStatusBarColor(color: String, isDark: Boolean) {
    this.window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val window: Window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if (isDark) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        window.statusBarColor = Color.parseColor(color)
    } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {
        val window: Window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = Color.parseColor(color)
    }
}

/*fun MaterialButton.makeEnabled(enable: Boolean = true) {
    if (enable) {
        background = ContextCompat.getDrawable(context, R.drawable.button_gradient)
        isEnabled = true
    } else {
        makeDisabled()
    }
}

fun MaterialButton.makeDisabled() {
    background = ContextCompat.getDrawable(context, R.drawable.disabled_button)
    isEnabled = false
}*/

fun Activity.transparentStatusBar(isDark: Boolean) {
    this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
    this.window.statusBarColor = Color.TRANSPARENT
    if (isDark) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}

private fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
    val win = activity.window
    val winParams = win.attributes
    if (on) {
        winParams.flags = winParams.flags or bits
    } else {
        winParams.flags = winParams.flags and bits.inv()
    }
    win.attributes = winParams
}

fun Activity.openPermissionSetting() {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    val uri: Uri = Uri.fromParts("package", packageName, null)
    intent.data = uri
    startActivity(intent)
}

@Throws(IOException::class)
fun getFile(context: Context, uri: Uri): File? {
    val destinationFilename =
        File(context.filesDir.path + File.separatorChar.toString() + queryName(context, uri))
    try {
        context.contentResolver.openInputStream(uri).use { ins ->
            createFileFromStream(
                ins!!,
                destinationFilename
            )
        }
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
    return destinationFilename
}

private fun queryName(context: Context, uri: Uri): String {
    val returnCursor: Cursor = context.contentResolver.query(uri, null, null, null, null)!!
    val nameIndex: Int = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
    returnCursor.moveToFirst()
    val name: String = returnCursor.getString(nameIndex)
    returnCursor.close()
    return name
}

fun createFileFromStream(ins: InputStream, destination: File?) {
    try {
        FileOutputStream(destination).use { os ->
            val buffer = ByteArray(4096)
            var length: Int
            while (ins.read(buffer).also { length = it } > 0) {
                os.write(buffer, 0, length)
            }
            os.flush()
        }
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}

fun getImageUriFromBitmap(context: Context, bitmap: Bitmap): Uri {
    val bytes = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
    val path = MediaStore.Images.Media.insertImage(context.contentResolver, bitmap, "Title", null)
    return Uri.parse(path.toString())
}

/*
fun mediaCompress(uri: Uri, context: Context): File {
    var byteArray: ByteArray?
    var file: File?
    val time = measureTimeMillis {
        var imageStream: InputStream? = null
        try {
            imageStream = context.contentResolver.openInputStream(uri)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        val bmp = BitmapFactory.decodeStream(imageStream)

        var stream: ByteArrayOutputStream? = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.JPEG, 30, stream)
        byteArray = stream?.toByteArray()
        try {
            stream?.close()
            stream = null
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
     FileOutputStream("filename").use { fileOuputStream ->
        fileOuputStream.write(byteArray)
    }
    return byteArray
}*/
