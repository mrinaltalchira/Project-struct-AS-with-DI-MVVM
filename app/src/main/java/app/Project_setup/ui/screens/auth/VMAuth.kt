package app.Project_setup.ui.screens.auth

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.Project_setup.data.local.DBRepository
import app.Project_setup.data.local.entities.EntityUsers
import app.Project_setup.data.remote.ApiHelperImpl
import app.Project_setup.utils.preferences.PrefManager
import com.google.i18n.phonenumbers.PhoneNumberUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class VMAuth @Inject constructor(private val app: Application, private val apiHelper: ApiHelperImpl, private val dbRepository: DBRepository, private val prefManager: PrefManager) : ViewModel() {


    var isAuthTypeEmail = MutableLiveData(true)
    var email = MutableLiveData("")
    var phone = MutableLiveData("")
    var name = MutableLiveData("")
    var username = MutableLiveData("")
    var password = MutableLiveData("")
    var confirmPassword = MutableLiveData("")
    var content = MutableLiveData("")
    var otp = MutableLiveData("")
    var canResendCode = true
    var resendCounter = MutableLiveData("Resend Code")
    var isPasswordVisible = MutableLiveData(false)
    var isConfirmPasswordVisible = MutableLiveData(false)
    var isValidUserName = MutableLiveData(false)


  //  var country = MutableLiveData<Countries>()
//    var countriesList: MutableList<Countries> = arrayListOf()
    private var phoneUtil = PhoneNumberUtil.getInstance()

    init {
       // countriesList = getCountries()
       // country.postValue(getDefaultCountry(countriesList)!!)
    }

    lateinit var timer: CountDownTimer

    fun prefManger() = prefManager

//    private fun getDefaultCountry(countriesList: MutableList<Countries>): Countries? {
//        return countriesList.find { it.isoCode == "US" }
//    }
//
//    private fun getCountries(): MutableList<Countries> {
//        val json = Utils.getJsonDataFromAsset(app, "countries.json")
//        val listPersonType = object : TypeToken<List<Countries>>() {}.type
//        return Gson().fromJson(json, listPersonType)
//    }

/*    fun isValidNumber(): Boolean {
        return try {
            val number = phoneUtil.parse(phone.value!!, country.value!!.isoCode)
            phoneUtil.isValidNumber(number)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }*/

    fun startResendTimer() {
        canResendCode = false
        timer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                resendCounter.value = String.format("Resend Code", "${millisUntilFinished / 1000}")
            }

            override fun onFinish() {
                canResendCode = true
                resendCounter.value = "Resend Code"
            }
        }
        timer.start()
    }

    /*fun getPasswordIcon(visible: Boolean): Int {
        return when (visible) {
           // false -> R.drawable.ic_eye_close
         //   true -> R.drawable.ic_eye_open
        }
    }*/

    fun saveUser(entity: EntityUsers?) {
        viewModelScope.launch(viewModelScope.coroutineContext + Dispatchers.IO) {
            entity?.let {
                dbRepository.saveUser(it)
            }
        }
    }

    fun isEmailValid(): Boolean {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher: Matcher? = email.value?.let { pattern.matcher(it) }
        return matcher?.matches() ?: false
    }

    /*fun setDefaultCountry() {
        country.postValue(getDefaultCountry(countriesList)!!)
    }*/

   // fun socialAuth(params: HashMap<String, Any>) = apiHelper.socialAuth(params)

  //  fun login(params: HashMap<String, Any>) = apiHelper.login(params)

  //  fun sendOTP(params: HashMap<String, Any>) = apiHelper.sendOTP(params)

//    fun verifyOTP(params: HashMap<String, Any>) = apiHelper.verifyOTP(params)


   // fun register(params: HashMap<String, Any>) = apiHelper.register(params)


}

