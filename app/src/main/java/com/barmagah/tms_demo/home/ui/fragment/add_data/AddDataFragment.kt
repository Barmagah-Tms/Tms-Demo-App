package com.barmagah.tms_demo.home.ui.fragment.add_data

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.barmagah.tms_demo.R
import com.barmagah.tms_demo.databinding.FragmentAddDataBinding
import com.barmagah.tms_demo.home.adapter.MenuItemAdapter
import com.barmagah.tms_demo.home.data.Menu
import java.util.*

class AddDataFragment : Fragment() {

    private lateinit var mBinding: FragmentAddDataBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_data, container, false)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setMenuRecyclerView()
    }

    private fun setMenuRecyclerView() {
        mBinding.recyclerViewMenu.setHasFixedSize(true)
        mBinding.recyclerViewMenu.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        val sGridLayoutManager = StaggeredGridLayoutManager(
            2, StaggeredGridLayoutManager.VERTICAL
        )
        mBinding.recyclerViewMenu.layoutManager = sGridLayoutManager
        loadMenu()

    }

    private fun getMenuList(): ArrayList<Menu> {
        val menuArrayList: ArrayList<Menu> = ArrayList<Menu>()
        menuArrayList.add(Menu(1, R.drawable.ic_add_project, "Add Project"))
        menuArrayList.add(Menu(2, R.drawable.ic_add_task, "Add Task"))
        return menuArrayList
    }

    private fun loadMenu() {
        mBinding.recyclerViewMenu.adapter = MenuItemAdapter(getMenuList()) { item ->
            onMenuItemClick(item.id)
        }
    }

    private fun onMenuItemClick(id: Int) {
        when (id) {
            1 ->
                Toast.makeText(context, "1", Toast.LENGTH_SHORT).show()
            2 ->
                Toast.makeText(context, "2", Toast.LENGTH_SHORT).show()
        }
    }

}