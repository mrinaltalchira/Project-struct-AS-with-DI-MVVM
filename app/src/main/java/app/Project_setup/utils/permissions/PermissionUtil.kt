package app.Project_setup.utils.permissions

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

object PermissionUtil {

    private fun useRuntimePermissions(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
    }

    public fun hasPermission(activity: AppCompatActivity, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED
    }

    private fun hasPermission(fragment: Fragment, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(fragment.requireContext(), permission) == PackageManager.PERMISSION_GRANTED
    }

    fun requestPermission(activity: AppCompatActivity, permissions: Array<String>, requestCode: Int) {
        if (useRuntimePermissions()) {

            activity.requestPermissions(permissions, requestCode)
        }
    }

    fun shouldAskForPermission(activity: AppCompatActivity, permission: String): Boolean {
        return useRuntimePermissions() && !hasPermission(activity, permission) && !hasAlreadyAskedPermission(activity, permission)
    }
    fun shouldAskForPermission(fragment: Fragment, permission: String): Boolean {
        return useRuntimePermissions() && !hasPermission(fragment, permission) && !hasAlreadyAskedPermission(fragment, permission)
    }

    fun shouldAskForPermission(activity: AppCompatActivity, permissions: Array<String>): Boolean {
        for (permission in permissions) {
            if (useRuntimePermissions() && !hasPermission(activity, permission) && !hasAlreadyAskedPermission(activity, permission)) {
                return true
            }
        }
        return false
    }

    fun shouldAskForPermission(fragment: Fragment, permissions: Array<String>): Boolean {
        for (permission in permissions) {
            if (useRuntimePermissions() && !hasPermission(fragment, permission) && !hasAlreadyAskedPermission(fragment, permission)) {
                return true
            }
        }
        return false
    }

    private fun hasAlreadyAskedPermission(activity: AppCompatActivity, permission: String): Boolean {
        return false
    }

    private fun hasAlreadyAskedPermission(fragment: Fragment, permission: String): Boolean {
        return false
    }
}