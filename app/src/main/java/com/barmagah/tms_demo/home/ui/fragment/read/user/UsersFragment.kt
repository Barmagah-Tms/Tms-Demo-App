package com.barmagah.tms_demo.home.ui.fragment.read.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.barmagah.tms_demo.R
import com.barmagah.tms_demo.databinding.FragmentUsersBinding
import com.barmagah.tms_demo.home.HomeActivity
import com.barmagah.tms_demo.home.adapter.user.ListUserAdapter
import com.barmagah.tms_demo.home.data.user.list_user.CommonUserRecords
import com.barmagah.tms_demo.home.ui.CompanyViewModel
import com.barmagah.tms_demo.system.ui.ScopedFragment
import com.barmagah.tms_demo.utils.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein

class UsersFragment : ScopedFragment(), KodeinAware {

    //Injection
    override val kodein by closestKodein()
    private lateinit var mBinding: FragmentUsersBinding

    //ViewModel
    private lateinit var viewModel: CompanyViewModel
    private val TAG = Constant.TAG_LIST_USERS_


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_users, container, false)
        return mBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = (activity as HomeActivity).viewModel


        setRecyclerView()
        bind()
    }


    private fun bind() = launch(Dispatchers.Main) {
        val entries = viewModel.deferredUserList.await()

        entries.observe(viewLifecycleOwner, Observer { it ->
            if (it == null) return@Observer


            val listUser: List<CommonUserRecords> = it.userResponse.CommonUserRecords
            if (listUser.isNotEmpty()) {
                mBinding.progressCircular.visibility = View.GONE
                mBinding.recyclerView.adapter =
                    ListUserAdapter(listUser) { item ->
                        onMenuItemClick(item)
                    }
            }

        })


    }

    private fun onMenuItemClick(item: CommonUserRecords) {
        val action =
            UsersFragmentDirections.actionToUserProfile(item)
        findNavController().navigate(action)
    }

    private fun setRecyclerView() {
        mBinding.recyclerView.setHasFixedSize(true)
        mBinding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        mBinding.recyclerView.layoutManager = LinearLayoutManager(this@UsersFragment.context)
    }

}
