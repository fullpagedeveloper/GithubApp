package com.fullpagedeveloper.githubuserapp.ui.users

import android.graphics.drawable.TransitionDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.DrawableTransformation
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.fullpagedeveloper.githubuserapp.R
import com.fullpagedeveloper.githubuserapp.data.model.UsersList
import kotlinx.android.synthetic.main.item_list_users.view.*

class UsersListAdapter: RecyclerView.Adapter<UsersListAdapter.UsersViewholder>() {

    var usersList = ArrayList<UsersList>()

    fun setDataUsersList(usersListSet: ArrayList<UsersList>) {
        usersList.clear()
        usersList.addAll(usersListSet)
        notifyDataSetChanged()
    }

    class UsersViewholder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(users: UsersList) {
            Glide.with(itemView)
                .load(users.avatarUrl)
                .placeholder(R.drawable.ic_baseline_search_24)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(itemView.findViewById(R.id.imageView_Avatar))
            itemView.textView_Name.text = users.login
            itemView.textView_userName.text = users.type
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_users, parent,false)
        return UsersViewholder(view)
    }

    override fun onBindViewHolder(holder: UsersViewholder, position: Int) {
        holder.bind(usersList[position])
    }

    override fun getItemCount(): Int = usersList.size
}