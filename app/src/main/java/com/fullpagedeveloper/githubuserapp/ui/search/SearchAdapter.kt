package com.fullpagedeveloper.githubuserapp.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.fullpagedeveloper.githubuserapp.R
import com.fullpagedeveloper.githubuserapp.data.model.Item
import com.fullpagedeveloper.githubuserapp.data.model.Users
import com.fullpagedeveloper.githubuserapp.extention.getProgresDrawable
import com.fullpagedeveloper.githubuserapp.extention.loadImage
import kotlinx.android.synthetic.main.item_list_users.view.*
import java.util.*
import kotlin.collections.ArrayList


class SearchAdapter(private val doClickListener: (Item) -> Unit) : RecyclerView.Adapter<SearchAdapter.MyViewHolder>(), Filterable {
    private var itemList = ArrayList<Item>()
    private var itemsFilter: ArrayList<Item> = ArrayList()

    fun setDataSearchView(items: ArrayList<Item>) {
        itemList.clear()
        itemList.addAll(items)
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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {

                if (constraint == null || constraint.isEmpty()) {
                    itemList.addAll(itemsFilter)
                } else {
                    val filterPattern = constraint.toString().toLowerCase(Locale.ROOT).trim()
                    for (itemiseUsers in itemsFilter) {
                        if (itemiseUsers.login.toLowerCase(Locale.ROOT).contains(filterPattern)) {
                            itemList.add(itemiseUsers)
                        }
                    }
                }

                val filterResult = FilterResults()
                filterResult.values = itemsFilter
                return filterResult
            }

            override fun publishResults(p0: CharSequence?, filterResults: FilterResults?) {
                itemsFilter.clear()
                itemsFilter.addAll(filterResults?.values as ArrayList<Item>)
                notifyDataSetChanged()
            }

        }
    }
}