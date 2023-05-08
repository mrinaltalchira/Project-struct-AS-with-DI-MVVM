package app.Project_setup.ui.screens.registration

import app.Project_setup.core.BaseActivity
import app.Project_setup.databinding.ActivitySignInBinding
import app.Project_setup.ui.screens.registration.fragment.SignUpFragment
import app.Project_setup.ui.screens.registration.fragment.SigninFragment
import app.Project_setup.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity : BaseActivity<SignInVM, ActivitySignInBinding>() {
    override fun getViewBinding() = ActivitySignInBinding.inflate(layoutInflater)


    override fun onRendered(viewModel: SignInVM, binding: ActivitySignInBinding) {
        super.onRendered(viewModel, binding)
        if (intent.getStringExtra("intentFrom").toString() == "signin") {
            Utils.loadFragment(SigninFragment(),supportFragmentManager)
        } else {
            Utils.loadFragment(SignUpFragment(),supportFragmentManager)
        }
    }




}