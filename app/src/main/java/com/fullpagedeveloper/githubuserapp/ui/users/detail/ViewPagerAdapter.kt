package com.fullpagedeveloper.githubuserapp.ui.users.detail

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.fullpagedeveloper.githubuserapp.R
import com.fullpagedeveloper.githubuserapp.ui.users.follow.FollowFragment


class ViewPagerAdapter(private val mContext: Context, private val username: String, fragmentManager: FragmentManager): FragmentPagerAdapter(
    fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        return FollowFragment.putUsernameFollow(username, position+1)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when(position) {
            0 ->  mContext.getString(R.string.followers)
            else -> mContext.getString(R.string.following)
        }
    }
}