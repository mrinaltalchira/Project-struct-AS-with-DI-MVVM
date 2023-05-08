package app.Project_setup.ui.screens.registration.fragment

import android.os.Bundle
import android.view.View
import app.Project_setup.core.BaseFragment
import app.Project_setup.databinding.FragmentSigninBinding
import app.Project_setup.ui.screens.registration.SignInVM
import app.Project_setup.utils.Utils

class SigninFragment : BaseFragment<SignInVM,FragmentSigninBinding>() {

    override fun getViewBinding()= FragmentSigninBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvSignup.setOnClickListener {  Utils.loadFragment(SignUpFragment(),fragmentManager!!)}
        binding.tvDontHaveAccount.setOnClickListener { binding.tvSignup.performClick()}
        binding.btnSignIn.setOnClickListener {
            Utils.loadFragment(OTP_frag(),fragmentManager!!)
        }
    }

}