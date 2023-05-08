package app.Project_setup.core

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.Project_setup.R
import app.Project_setup.data.remote.BaseDataSource
import app.Project_setup.utils.extensions.snack
import app.Project_setup.utils.views.loader.Loader
import com.facebook.CallbackManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

import java.lang.reflect.ParameterizedType


abstract class BaseFragment<VM : ViewModel, VB : ViewDataBinding> : Fragment() {

    lateinit var viewModel: VM
    lateinit var binding: VB
    lateinit var loader: Dialog
    val callbackManager: CallbackManager = CallbackManager.Factory.create()
    lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[getViewModelClass()]
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso);
        binding = getViewBinding()
        binding.lifecycleOwner = viewLifecycleOwner
        loader = Loader.getLoader(requireActivity())
        return binding.root
    }

    private fun getViewModelClass(): Class<VM> {
        val type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        return type as Class<VM>
    }

    abstract fun getViewBinding(): VB

    open fun <T> wsWithLoader(dataResource: BaseDataSource.Resource<T>, block: () -> Unit) {
        when (dataResource.status) {
            BaseDataSource.Resource.Status.SUCCESS -> {
                loader.dismiss()
                block.invoke()
            }
            BaseDataSource.Resource.Status.LOADING -> {
                if (!loader.isShowing) {
                    loader.show()
                }
            }
            BaseDataSource.Resource.Status.ERROR -> {
                binding.root.snack(dataResource.message ?: getString(R.string.common_server_error)) {}
                loader.dismiss()
            }
        }
    }

    open fun <T> ws(dataResource: BaseDataSource.Resource<T>, block: () -> Unit) {
        when (dataResource.status) {
            BaseDataSource.Resource.Status.SUCCESS -> {
                block.invoke()
            }
            BaseDataSource.Resource.Status.LOADING -> {

            }
            BaseDataSource.Resource.Status.ERROR -> {
                binding.root.snack(dataResource.message ?: getString(R.string.common_server_error)) {}
            }
        }
    }


}