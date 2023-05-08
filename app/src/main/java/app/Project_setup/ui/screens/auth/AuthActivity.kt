package app.Project_setup.ui.screens.auth

import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import app.Project_setup.R
import app.Project_setup.core.BaseActivity
import app.Project_setup.databinding.ActivityAuthBinding
import app.Project_setup.utils.extensions.transparentStatusBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity<VMAuth, ActivityAuthBinding>() {

    lateinit var navController: NavController

    override fun onRendered(viewModel: VMAuth, binding: ActivityAuthBinding) {
        transparentStatusBar(false)
        navController = supportFragmentManager.findFragmentById(R.id.nav_host)!!.findNavController()
        navController.graph = navController.navInflater.inflate(R.navigation.auth_nav_graph)


      /*  navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.welcome || destination.id == R.id.createAccount || destination.id == R.id.login) {
                resetModel()
            }
        }*/
    }

    private fun resetModel() {
        viewModel.isAuthTypeEmail.value = true
        viewModel.email.value = ""
        viewModel.phone.value = ""
        viewModel.name.value = ""
        viewModel.username.value = ""
        viewModel.password.value = ""
        viewModel.confirmPassword.value = ""
        viewModel.otp.value = ""
        viewModel.canResendCode = true
        viewModel.resendCounter.value = "Resend Code"
        viewModel.isPasswordVisible.value = false
        viewModel.isConfirmPasswordVisible.value = false
        viewModel.isValidUserName.value = false
    }

    override fun getViewBinding() = ActivityAuthBinding.inflate(layoutInflater)

}