package com.barmagah.tms_demo.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barmagah.tms_demo.databinding.ItemAddMenuBinding
import com.barmagah.tms_demo.home.data.Menu

class MenuItemAdapter(
    var menuItem: ArrayList<Menu>,
    private val listener: (Menu) -> Unit,
) : RecyclerView.Adapter<MenuItemAdapter.MenuViewHolder>() {

    private var inflater: LayoutInflater? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        if (inflater == null)
            inflater = LayoutInflater.from(parent.context)
        val dataBinding: ItemAddMenuBinding = ItemAddMenuBinding.inflate(inflater!!, parent, false)
        return MenuViewHolder(dataBinding)
    }

    override fun getItemCount(): Int {
        return menuItem.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val item = menuItem[position]
        holder.itemMenuBinding.menuItem = item
        holder.itemView.setOnClickListener { listener(item) }

    }

    private fun getCurrentItemAt(position: Int): Menu {
        return menuItem.get(position)
    }

    inner class MenuViewHolder(var itemMenuBinding: ItemAddMenuBinding) : RecyclerView.ViewHolder(
        itemMenuBinding.root
    )
}


