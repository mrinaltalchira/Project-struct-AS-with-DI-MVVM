package app.Project_setup.utils.views.loader

import android.app.Activity
import android.app.Dialog
import android.view.Window
import android.view.WindowManager
import app.Project_setup.R

class Loader {
    companion object {
        fun getLoader(context: Activity): Dialog {
            val dialog = Dialog(context, R.style.DialogFragmentTheme)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.window!!.setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE)
            dialog.setContentView(R.layout.loader_dialog)
            dialog.setCanceledOnTouchOutside(false)
            dialog.setCancelable(false)
            return dialog
        }
    }
}