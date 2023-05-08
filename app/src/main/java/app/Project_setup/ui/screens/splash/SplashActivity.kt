package app.Project_setup.ui.screens.splash

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.animation.TranslateAnimation
import app.Project_setup.core.BaseActivity
import app.Project_setup.databinding.ActivitySplashBinding
import app.Project_setup.ui.screens.registration.SignInActivity
import app.Project_setup.utils.preferences.PrefManager
import com.google.android.play.core.appupdate.AppUpdateManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SplashActivity : BaseActivity<VMSplash, ActivitySplashBinding>() {

    @Inject
    lateinit var prefManager: PrefManager

    private lateinit var appUpdateManager: AppUpdateManager
    private val inAppUpdateCode = 123
    override fun getViewBinding()= ActivitySplashBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setTimer()

        binding.apply {
            binding.btnSignin.setOnClickListener {

                var intent = Intent(this@SplashActivity,SignInActivity::class.java)
                intent.putExtra("intentFrom","signin")
                startActivity(intent)


            }
            binding.btnSignup.setOnClickListener {
                var intent = Intent(this@SplashActivity,SignInActivity::class.java)
                intent.putExtra("intentFrom","signup")
                startActivity(intent)

            }
        }



    }
    fun slideUp(view: View) {
        view.visibility = View.VISIBLE
        val animate = TranslateAnimation(
            0f,  // fromXDelta
            0f,  // toXDelta
            view.height.toFloat(),  // fromYDelta
            0f
        ) // toYDelta
        animate.duration = 2000
        animate.fillAfter = true
        view.startAnimation(animate)
    }

fun setTimer(){
    object : CountDownTimer(5000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
             // logic to set the EditText could go here
        }

        override fun onFinish() {

            slideUp(binding.imageView3)
            slideUp(binding.textBox)

        }
    }.start()
}
}