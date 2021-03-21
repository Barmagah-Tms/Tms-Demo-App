package com.barmagah.tms_demo.system.ui.login

import android.util.Patterns
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barmagah.tms_demo.system.internal.lazyDeferred
import com.barmagah.tms_demo.system.provider.UserPreferenceProvider
import com.barmagah.tms_demo.system.repository.SystemRepository
import com.barmagah.tms_demo.utils.Constant.Companion.SH_USER_EMAIL
import com.barmagah.tms_demo.utils.Constant.Companion.SH_USER_PASS
import com.barmagah.tms_demo.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val systemRepository: SystemRepository,
    private val userPreferenceProvider: UserPreferenceProvider
) : ViewModel(), Observable {


    @Bindable
    val inputEmail = MutableLiveData<String>()

    @Bindable
    val inputPass = MutableLiveData<String>()


    private val statusMessage = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>>
        get() = statusMessage

    /*
    * _current
    * */
    private lateinit var _email: String
    private lateinit var _password: String
    val isLoggedIn = userPreferenceProvider.isLoggedIn()


    init {
        if (userPreferenceProvider.isLoggedIn()) {
            inputEmail.value = userPreferenceProvider.getUserStringData(SH_USER_EMAIL)
            inputPass.value = userPreferenceProvider.getUserStringData(SH_USER_PASS)
        } else {
            inputEmail.value = ""
            inputPass.value = ""
        }
    }

    /*
    * Action
    *
    *
    *
    *
    *
    * */

    val deferredData by lazyDeferred {
        systemRepository.getLoginResponse(inputEmail.value!!, inputPass.value!!)
    }


    fun login() {
        _email = inputEmail.value!!
        _password = inputPass.value!!
        if (!Patterns.EMAIL_ADDRESS.matcher(_email).matches()) {
            statusMessage.value = Event("invalid email ..")
        } else if (_password.length < 4) {
            statusMessage.value = Event("invalid password  ..")
        } else {
            viewModelScope.launch(Dispatchers.Main) {
                systemRepository.getLoginResponse(_email, _password)
            }

        }
    }

    /*
    *
    *
    * override*/
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}