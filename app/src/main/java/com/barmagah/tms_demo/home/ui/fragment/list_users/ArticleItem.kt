package com.barmagah.tms_demo.home.ui.fragment.list_users

import com.barmagah.tms_demo.R
import com.barmagah.tms_demo.home.data.list_user.CommonUserRecords
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_list_user.*


class ArticleItem(
    private val articleEntry: CommonUserRecords
) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            text_name.text = articleEntry.Name
        }
    }

    override fun getLayout() = R.layout.item_list_user


}