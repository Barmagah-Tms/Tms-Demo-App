package com.barmagah.tms_demo.system.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.barmagah.tms_demo.system.provider.UserPreferenceProvider
import com.barmagah.tms_demo.system.repository.SystemRepository

class LoginViewModelFactory(
    private val systemRepository: SystemRepository,
    private val userPreferenceProvider: UserPreferenceProvider

) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(systemRepository, userPreferenceProvider) as T
    }
}