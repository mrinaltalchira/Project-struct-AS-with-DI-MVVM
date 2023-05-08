package app.Project_setup.ui.screens.auth.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import app.Project_setup.core.BaseFragment
import app.Project_setup.databinding.FragmentWelcomeBinding
import app.Project_setup.ui.screens.auth.VMAuth
import app.Project_setup.utils.extensions.transparentStatusBar


class Welcome : BaseFragment<VMAuth, FragmentWelcomeBinding>() {

    lateinit var navController: NavController

    override fun getViewBinding() = FragmentWelcomeBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            //controller = this@Welcome
        }


    }

    override fun onResume() {
        super.onResume()
        requireActivity().transparentStatusBar(false)
    }


}