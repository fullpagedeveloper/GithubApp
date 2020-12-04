package com.fullpagedeveloper.githubuserapp.ui.users

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.fullpagedeveloper.githubuserapp.R
import com.fullpagedeveloper.githubuserapp.data.model.Item
import kotlinx.android.synthetic.main.item_list_users.view.*
import java.util.*
import kotlin.collections.ArrayList


//class SearchAdapter: RecyclerView.Adapter<SearchAdapter.MyViewHolder>(), Filterable{
//
//    private var itemList = ArrayList<Item>()
//    private var itemsFilter: ArrayList<Item> = ArrayList()
//
//    fun setDataSearchView(items: ArrayList<Item>) {
//        itemList.clear()
//        itemList.addAll(items)
//        notifyDataSetChanged()
//    }
//
//    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
//        fun bind(list: Item){
//            Glide.with(itemView)
//                .load(list.avatarUrl)
//                .placeholder(R.drawable.ic_launcher_foreground)
//                .transition(DrawableTransitionOptions.withCrossFade())
//                .apply(RequestOptions.circleCropTransform())
//                .into(itemView.findViewById(R.id.imageView_Avatar))
//            itemView.textView_Name.text = list.login
//            itemView.textView_userName.text = list.type
//            itemView.cv.setOnClickListener {
//
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_users, parent, false)
//        return MyViewHolder(view)
//    }
//
//    override fun getItemCount(): Int = itemList.size
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.bind(itemList[position])
//    }
//
//    override fun getFilter(): Filter {
//        return object : Filter(){
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//
//                if (constraint == null || constraint.isEmpty()) {
//                    Log.d("GOKIL", "gokil 4")
//                    itemList.addAll(itemsFilter)
//                } else {
//                    val filterPattern = constraint.toString().toLowerCase(Locale.ROOT).trim()
//                    Log.d("GOKIL", "gokil 5")
//                    for (itemiseUsers in itemsFilter) {
//                        Log.d("GOKIL", "gokil 6")
//                        if (itemiseUsers.login.toLowerCase(Locale.ROOT).contains(filterPattern)) {
//                            Log.d("GOKIL", "gokil 7")
//                            itemList.add(itemiseUsers)
//                        }
//                    }
//                }
//
//                val filterResult = FilterResults()
//                filterResult.values = itemsFilter
//                Log.d("GOKIL", "gokil 8")
//                return filterResult
//            }
//
//            override fun publishResults(p0: CharSequence?, filterResults: FilterResults?) {
//                itemsFilter.clear()
//                itemsFilter.addAll(filterResults?.values as ArrayList<Item>)
//                Log.d("GOKIL", "gokil 9")
//                notifyDataSetChanged()
//            }
//
//        }
//    }
//}