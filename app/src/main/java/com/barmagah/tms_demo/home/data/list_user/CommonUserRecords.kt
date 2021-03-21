package com.barmagah.tms_demo.home.data.list_user

data class CommonUserRecords(
    val CompanyID: Int,
    val CreatedAtStr: String,
    val Email: String,
    val ID: Int,
    val ImageUrl: String,
    val IsActive: Boolean,
    val IsDeleted: Boolean,
    val LastModifiedAtStr: String,
    val LastModifiedBy: Int,
    val Name: String,
    val NotAllowedToDelete: Boolean,
    val Password: String,
    val Phone: String,
    val RoleId: Int,
    val UserName: String
) {

    // Secondary Constructor
    constructor(companyID: Int) : this(
         companyID, "", "", 0, "", true, true, "", 0, "", true, "", "", 0, ""
    )
}