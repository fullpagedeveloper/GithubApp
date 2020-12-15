package com.fullpagedeveloper.githubuserapp.ui.users.follow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fullpagedeveloper.githubuserapp.R
import com.fullpagedeveloper.githubuserapp.data.model.Follow
import com.fullpagedeveloper.githubuserapp.extention.getProgresDrawable
import com.fullpagedeveloper.githubuserapp.extention.loadImage
import kotlinx.android.synthetic.main.item_list_users.view.*
import java.util.*
import kotlin.collections.ArrayList

class FollowAdapter: RecyclerView.Adapter<FollowAdapter.FollowViewHolder>() {
    private val  followUsers = ArrayList<Follow>()

    fun setDataFollow(follow: ArrayList<Follow>) {
        followUsers.clear()
        followUsers.addAll(follow)
        notifyDataSetChanged()
    }

    class FollowViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindTo(follow: Follow) {
            follow.let {
                itemView.item_search_user_image_profile.loadImage(follow.avatarUrl, getProgresDrawable(itemView.item_search_user_image_profile.context))
                itemView.item_search_user_title.text = follow.login.capitalize(Locale.ROOT)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_users, parent, false)
        return FollowViewHolder(view)
    }

    override fun onBindViewHolder(holder: FollowViewHolder, position: Int) {
        holder.bindTo(followUsers[position])
    }

    override fun getItemCount(): Int = followUsers.size
}