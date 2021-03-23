package com.barmagah.tms_demo.home.data.user.list_user

data class UserResponse(
    val AddorEditID: Int,
    val CommonUserRecords: List<CommonUserRecords>,
    val Message: String,
    val StatusCode: Int,
    val Success: Boolean,
    val TotalCount: Int
)