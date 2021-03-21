package com.barmagah.tms_demo.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.barmagah.tms_demo.home.repository.CompanyRepository

class CompanyViewModelFactory(
    private val companyRepository: CompanyRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CompanyViewModel(companyRepository) as T
    }
}