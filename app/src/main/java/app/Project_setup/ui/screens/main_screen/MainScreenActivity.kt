package app.Project_setup.ui.screens.main_screen

import android.os.Bundle
import app.Project_setup.core.BaseActivity
import app.Project_setup.databinding.ActivityMainScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreenActivity : BaseActivity<VM_MainScreen,ActivityMainScreenBinding>() {
    override fun getViewBinding()= ActivityMainScreenBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

}