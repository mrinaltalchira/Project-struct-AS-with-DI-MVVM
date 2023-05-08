package app.Project_setup.utils.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


fun Fragment.showToast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) = activity?.showToast(message, duration)

fun Fragment.isFragmentInBackStack(destinationId: Int) =
    try {
        findNavController().getBackStackEntry(destinationId)
        true
    } catch (e: Exception) {
        false
    }