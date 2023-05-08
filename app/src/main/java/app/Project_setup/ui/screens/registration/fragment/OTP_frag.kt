package app.Project_setup.ui.screens.registration.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import app.Project_setup.core.BaseFragment
import app.Project_setup.databinding.FragmentOTPFragBinding
import app.Project_setup.ui.screens.main_screen.MainScreenActivity
import app.Project_setup.ui.screens.registration.SignInVM


class OTP_frag : BaseFragment<SignInVM,FragmentOTPFragBinding>() {


    override fun getViewBinding()=FragmentOTPFragBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSubmitCode.setOnClickListener {
            var intent = Intent(requireActivity(),MainScreenActivity::class.java)
            startActivity(intent)
        }
    }

}