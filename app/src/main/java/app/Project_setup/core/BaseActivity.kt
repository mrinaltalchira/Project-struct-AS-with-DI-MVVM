package app.Project_setup.core

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.Project_setup.R
import app.Project_setup.data.local.DBRepository
import app.Project_setup.data.remote.BaseDataSource
import app.Project_setup.utils.FireStoreCollection
import app.Project_setup.utils.extensions.snack
import app.Project_setup.utils.views.loader.Loader
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

abstract class BaseActivity<VM : ViewModel, VB : ViewDataBinding> : AppCompatActivity() {

    lateinit var viewModel: VM
    lateinit var binding: VB
    lateinit var loader : Dialog

    @Inject
    lateinit var dbRepository: DBRepository

    var db = Firebase.firestore

    private val ioScope = CoroutineScope(Dispatchers.IO + Job())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[getViewModelClass()]
        binding = getViewBinding()
        binding.lifecycleOwner = this
        loader = Loader.getLoader(this)
        setContentView(binding.root)
        onRendered(viewModel,binding)
    }

    private fun getViewModelClass(): Class<VM> {
        val type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        return type as Class<VM>
    }

    abstract fun getViewBinding(): VB

    open fun onRendered(viewModel: VM, binding: VB) {}

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

    override fun onStart() {
        super.onStart()
        updateStatus(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        updateStatus(false)
    }

    override fun onPause() {
        super.onPause()
        updateStatus(false)
    }

    private fun updateStatus(status: Boolean) {
        val firestoreUser = db.collection(FireStoreCollection.users)
        ioScope.launch {
            dbRepository.getUser().let { user ->
                if(user != null){
                    firestoreUser.document(user.id.toString()).update("isOnline",status)
                }
            }
        }
    }

}