package com.barmagah.tms_demo.system.data

data class CurrentUser(
    val email: String,
    val pass: String,
    val token: String,
    val company_id: Int,
    var isLoggedIn: Boolean = false
)