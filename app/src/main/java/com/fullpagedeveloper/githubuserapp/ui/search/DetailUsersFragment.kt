package com.fullpagedeveloper.githubuserapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fullpagedeveloper.githubuserapp.R
import com.fullpagedeveloper.githubuserapp.data.model.Item
import com.fullpagedeveloper.githubuserapp.extention.getProgresDrawable
import com.fullpagedeveloper.githubuserapp.extention.loadImage
import com.fullpagedeveloper.githubuserapp.ui.users.UsersViewModel
import kotlinx.android.synthetic.main.fragment_detail_users.*
import kotlinx.android.synthetic.main.fragment_detail_users.view.*
import java.util.*


class DetailUsersFragment : Fragment() {

    var usersViewModel = UsersViewModel()

    companion object {
        const val USERNAME = "username"

        fun newInstance(itemList: Item): DetailUsersFragment {
            return DetailUsersFragment().apply {
                arguments = Bundle().apply {
                    putString(USERNAME, itemList.login)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar?.title = getString(R.string.detail_user)

        usersViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[UsersViewModel::class.java]

        val users = arguments?.getString(USERNAME)

        loadAndError()

        dataDetail(users.toString(), view)

    }

    private fun loadAndError() {
        usersViewModel.error.observe(viewLifecycleOwner, { isError ->
            isError.let {
                text_error.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        usersViewModel.loading.observe(viewLifecycleOwner, { isLoading ->
            isLoading.let {
                progress_bar.visibility = if (it) View.VISIBLE else View.GONE

                if (it) {
                    text_error.visibility = View.GONE
                }
            }

        })
    }

    private fun dataDetail(users: String, view: View) {
        usersViewModel.users(users).observe(viewLifecycleOwner, {
            view.iv_Avatar?.loadImage(
                it.avatarUrl,
                getProgresDrawable(view.iv_Avatar.context.applicationContext)
            )
            view.tv_Name.text = it.login.capitalize(Locale.ROOT)
            view.tv_Company.text = it.company
            view.tv_Username.text = it.updatedAt
            view.tv_Location.text = it.location
            view.tv_Repo.text = it.publicRepos.toString()
            view.tv_Follower.text = it.following.toString()
            view.tv_Following.text = it.followers.toString()
        })
    }
}