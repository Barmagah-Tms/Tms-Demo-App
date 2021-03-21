package com.barmagah.tms_demo.system.ui.login.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.barmagah.tms_demo.R
import com.barmagah.tms_demo.databinding.FragmentLoginBinding
import com.barmagah.tms_demo.system.ui.LoginActivity
import com.barmagah.tms_demo.system.ui.ScopedFragment
import com.barmagah.tms_demo.system.ui.login.LoginViewModel
import com.barmagah.tms_demo.utils.Constant
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein

class LoginFragment : ScopedFragment(), KodeinAware {
    //Inject
    override val kodein by closestKodein()
    private lateinit var mBinding: FragmentLoginBinding


    //viewModel
    lateinit var viewModel: LoginViewModel
    private val TAG = Constant.TAG_LOGIN_

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return mBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        /*
        * set ViewModel
        * */

        viewModel = (activity as LoginActivity).viewModel
        mBinding.viewModel = viewModel
        mBinding.lifecycleOwner = this

        onBackPressed()
        messageResponse()

        loginResponse()

    }

    private fun loginResponse() = launch {
        val deferredResponse = viewModel.deferredData.await()//sync
        deferredResponse.observe(viewLifecycleOwner, Observer {

            Log.d(TAG, "fragment loginResponse: $it ")
            if (it?.token != null) {
                findNavController().navigate(LoginFragmentDirections.actionToHomeActivity())
                (activity as LoginActivity).finish()
            } else Log.d(TAG, "fragment loginResponse: $it ")
        })
    }

    /*
    *
    * Views
    * */
    private fun messageResponse() {
        viewModel.message.observe(viewLifecycleOwner, Observer { it ->
            if (it == null) return@Observer

            it.getContentIfNotHandled()?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            (activity as LoginActivity).finish()
        }
    }
}