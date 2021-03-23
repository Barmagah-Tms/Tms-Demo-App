package com.barmagah.tms_demo.home.data.user.list_user

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable


@SuppressLint("ParcelCreator")
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
) : Parcelable {
    // Secondary Constructor
    constructor(companyID: Int) : this(
        companyID, "", "", 0, "", true, true, "", 0, "", true, "", "", 0, ""
    )

    // 3 Constructor
    constructor(ID: Int, UserName: String) : this(
        0, "", "", ID, "", true, true, "", 0, "", true, "", "", 0, ""
    )

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("Not yet implemented")
    }
}