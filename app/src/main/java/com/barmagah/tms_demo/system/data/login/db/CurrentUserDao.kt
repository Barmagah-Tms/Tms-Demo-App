package com.barmagah.tms_demo.system.data.login.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.barmagah.tms_demo.system.data.login.CURRENT_USER_ID
import com.barmagah.tms_demo.system.data.login.LoginResponseEntry


@Dao
interface CurrentUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertUser(loginResponseEntry: LoginResponseEntry)

    @Query("select * from current_user_table where id = $CURRENT_USER_ID")
    fun getCurrentUser(): LiveData<LoginResponseEntry>

    @Query("DELETE FROM current_user_table WHERE id = $CURRENT_USER_ID")
    fun deleteUser()


}