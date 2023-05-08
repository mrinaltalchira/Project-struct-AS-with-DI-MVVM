package app.Project_setup.utils.permissions

import android.content.pm.PackageManager
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import java.lang.ref.WeakReference

class PermissionManager {
    private val REQUEST_CODE = 300
    private var permissions: ArrayList<String> = arrayListOf()
    private var callback: PermissionCallback? = null

    companion object {
        private var contextWeakRef: WeakReference<AppCompatActivity>? = null
        private var permissionManagerWeakRef: WeakReference<PermissionManager>? = null
        fun with(context: AppCompatActivity): PermissionManager {
            contextWeakRef = WeakReference(context)
            permissionManagerWeakRef = WeakReference(PermissionManager())
            return getPermissionManager()
        }

        private fun getPermissionManager(): PermissionManager {
            return permissionManagerWeakRef?.get()!!
        }

        private fun getActivity(): AppCompatActivity {
            return contextWeakRef?.get()!!
        }
    }

    fun request(permission: String): PermissionManager {
        permissions.add(permission)
        return getPermissionManager()
    }

    fun request(permissionArr: Array<String>): PermissionManager {
        permissions.addAll(permissionArr)
        return getPermissionManager()
    }

    fun ask() {
        if (!PermissionUtil.shouldAskForPermission(getActivity(), permissions.toTypedArray())) {
            callback?.alreadyHasPermission()
            return
        }
        handlePermission()
    }

    fun setCallbacks(callback: PermissionCallback): PermissionManager {
        this.callback = callback
        return getPermissionManager()
    }

    fun onRequestPermissionResult(requestCode: Int, @NonNull permissions: Array<out String>, @NonNull grantResults: Array<Int>) {
        if (requestCode != REQUEST_CODE) {
            return
        }
        val acceptedPermissions = arrayListOf<String>()
        val rejectedPermissions = arrayListOf<String>()
        val rejectedForeverPermissions = arrayListOf<String>()
        permissions.forEachIndexed { index, perm ->
            if (grantResults[index] == PackageManager.PERMISSION_GRANTED) {
                acceptedPermissions.add(perm)
            } else if (grantResults[index] == PackageManager.PERMISSION_DENIED) {
                val showRationale = shouldShowRequestPermissionRationale(getActivity(), perm)
                if (!showRationale) {
                    rejectedForeverPermissions.add(perm)
                } else {
                    rejectedPermissions.add(perm)
                }
            }
        }
        callback?.permissionResult(acceptedPermissions, rejectedPermissions, rejectedForeverPermissions)
    }

    private fun handlePermission()  {
        PermissionUtil.requestPermission(getActivity(), permissions.toTypedArray(), REQUEST_CODE)
    }

    interface PermissionCallback {
        fun alreadyHasPermission()
        fun permissionResult(acceptedPermissions: ArrayList<String>, rejectedPermissions: ArrayList<String>, rejectedForeverPermissions: ArrayList<String>)
    }
}