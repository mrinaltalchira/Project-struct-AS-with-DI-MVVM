package app.Project_setup.ui.screens.registration.fragment

import android.os.Bundle
import android.view.View
import app.Project_setup.core.BaseFragment
import app.Project_setup.databinding.FragmentSignUpBinding
import app.Project_setup.ui.screens.registration.SignInVM
import app.Project_setup.utils.Utils


class SignUpFragment:BaseFragment<SignInVM,FragmentSignUpBinding>() {
    override fun getViewBinding()= FragmentSignUpBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvSignin.setOnClickListener { Utils.loadFragment(SigninFragment(),fragmentManager!!) }
        binding.tvAlreadyHaveAcc.setOnClickListener { binding.tvSignin.performClick() }
        binding.btnSignup.setOnClickListener {
            Utils.loadFragment(OTP_frag(),fragmentManager!!)
        }
    }

}