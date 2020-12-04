package com.fullpagedeveloper.githubuserapp.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.fullpagedeveloper.githubuserapp.R


class DetailUsersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity?)!!.supportActionBar?.title = "Detail User"

//        val users = arguments?.getParcelable<Users>(USERS_LIST)
//        val avatar = view.resources.getIdentifier(users?.avatar, "drawable", view.context.packageName)
//        activity?.applicationContext?.let {
//            Glide.with(it)
//                .load(avatar)
//                .into(iv_Avatar)
//        }

//        view.tv_Name.text = users?.name
//        view.tv_Username.text = users?.username
//        view.tv_Repo.text = users?.repository.toString()
//        view.tv_Follower.text = users?.follower.toString()
//        view.tv_Following.text = users?.following.toString()
//        view.tv_Location.text = users?.location
//        view.tv_Company.text = users?.company
    }

    companion object {
        const val USERS_LIST = "list_users"

//        fun newInstance(itemList: Users) : DetailUsersFragment {
//            return DetailUsersFragment().apply {
//                arguments = Bundle().apply { putParcelable(USERS_LIST, itemList) }
//            }
//        }
    }
}