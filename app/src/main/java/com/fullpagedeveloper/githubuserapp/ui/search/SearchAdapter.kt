package com.fullpagedeveloper.githubuserapp.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fullpagedeveloper.githubuserapp.R
import com.fullpagedeveloper.githubuserapp.data.model.Item
import com.fullpagedeveloper.githubuserapp.extention.getProgresDrawable
import com.fullpagedeveloper.githubuserapp.extention.loadImage
import kotlinx.android.synthetic.main.item_list_users.view.*
import java.util.*
import kotlin.collections.ArrayList


class SearchAdapter(private val doClickListener: (Item) -> Unit) : RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {
    private var itemList = ArrayList<Item>()
    private var itemsFilter: ArrayList<Item> = ArrayList()

    fun addAll(items: ArrayList<Item>) {
        itemList.addAll(items)
        notifyDataSetChanged()
    }

    fun clearAll() {
        itemList.clear()
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindTo(list: Item?) {
            list?.let { it ->
                itemView.item_search_user_image_profile.loadImage(
                    it.avatarUrl,
                    getProgresDrawable(itemView.item_search_user_image_profile.context)
                )
                itemView.item_search_user_title.text = list.login.capitalize(Locale.ROOT)
                itemView.setOnClickListener{
                    doClickListener(list)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_users, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindTo(itemList[position])
    }
}