package com.barmagah.tms_demo.home.ui

import androidx.lifecycle.ViewModel
import com.barmagah.tms_demo.home.repository.CompanyRepository
import com.barmagah.tms_demo.system.internal.lazyDeferred

class CompanyViewModel(
    private val companyRepository: CompanyRepository
) : ViewModel() {

    val deferredData by lazyDeferred {
        companyRepository.getListUser(1)
    }
}