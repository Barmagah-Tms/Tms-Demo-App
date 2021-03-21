package com.barmagah.tms_demo.system.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.barmagah.tms_demo.R
import com.barmagah.tms_demo.system.ui.login.LoginViewModel
import com.barmagah.tms_demo.system.ui.login.LoginViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity() , KodeinAware {
    // Injection
    override val kodein by closestKodein()

    //ViewModel
    private val viewModelFactory: LoginViewModelFactory by instance()
    lateinit var viewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel =
            ViewModelProviders.of(this, viewModelFactory)
                .get(LoginViewModel::class.java)
    }
}