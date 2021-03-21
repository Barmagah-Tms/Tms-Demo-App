package com.barmagah.tms_demo.system.data.login

import androidx.room.Entity
import androidx.room.PrimaryKey


const val CURRENT_USER_ID = 0

@Entity(tableName = "current_user_table")
data class LoginResponseEntry(
    val companyId: Int,
    val expiration: String,
    val fullName: String,
    val roleId: Int,
    val roleName: Int,
    val token: String,
    val userEmail: String,
    val userId: Int
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = CURRENT_USER_ID
}