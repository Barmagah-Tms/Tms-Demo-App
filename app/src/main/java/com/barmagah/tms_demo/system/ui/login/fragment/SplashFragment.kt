package com.barmagah.tms_demo.system.ui.login.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.barmagah.tms_demo.R
import com.barmagah.tms_demo.databinding.FragmentSplashBinding
import com.barmagah.tms_demo.system.ui.LoginActivity
import com.barmagah.tms_demo.system.ui.ScopedFragment
import com.barmagah.tms_demo.system.ui.login.LoginViewModel
import com.barmagah.tms_demo.utils.Constant
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import java.lang.Exception

class SplashFragment : ScopedFragment(), KodeinAware {
    //Inject
    override val kodein by closestKodein()
    private lateinit var mBinding: FragmentSplashBinding

    //viewModel
    private lateinit var viewModel: LoginViewModel
    private val TAG = Constant.TAG_LOGIN_

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        return mBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        viewModel = (activity as LoginActivity).viewModel




        Handler(Looper.getMainLooper()).postDelayed({
            checkIsUserLoggedIn()
        }, 2000)


    }

    private fun autoLogin() = launch {
        try {

            val deferredData = viewModel.deferredData.await()//sync
            deferredData.observe(viewLifecycleOwner, Observer {

                if (it?.token != null) {
                    moveToFragment(SplashFragmentDirections.actionToHomeActivity())
                    (activity as LoginActivity).finish()
                } else
                    moveToFragment(SplashFragmentDirections.actionToLogin())
            })

        } catch (ex: Exception) {
            Log.d(TAG, "autoLogin: $ex")
        }
    }

    /*
    *
    * action */
    private fun checkIsUserLoggedIn() {
        if (!viewModel.isLoggedIn) {
            moveToFragment(SplashFragmentDirections.actionToLogin())
        } else autoLogin()
    }

    private fun moveToFragment(directions: NavDirections) {
        findNavController().navigate(directions)
    }
}