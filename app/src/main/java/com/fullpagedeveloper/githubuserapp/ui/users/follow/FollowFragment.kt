package com.fullpagedeveloper.githubuserapp.ui.users.follow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fullpagedeveloper.githubuserapp.R
import kotlinx.android.synthetic.main.fragment_detail_users.*
import kotlinx.android.synthetic.main.fragment_follow.*
import kotlinx.android.synthetic.main.fragment_follow.view.*

class FollowFragment : Fragment() {
    private var followViewModel = FollowViewModel()
    private var followAdapter = FollowAdapter()

    companion object {
        private const val USERNAME = "username"
        private const val PAGE = "page"

        fun putUsernameFollow(username: String, position: Int) = FollowFragment().apply {
            arguments = Bundle().apply {
                putString(USERNAME, username)
                putInt(PAGE, position)
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_follow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        followViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[FollowViewModel::class.java]

        showRecyclerView(view)
        loadAndError(view)

        followViewModel._follow.observe(viewLifecycleOwner, {
            followAdapter.setDataFollow(it)
        })

        followViewModel._follow.observe(viewLifecycleOwner, {
            followAdapter.setDataFollow(it)
        })
    }

    private fun showRecyclerView(view: View) {
        recyclerView_Follow.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = followAdapter
            setHasFixedSize(true)
        }
    }

    private fun loadAndError(view: View) {
        followViewModel.error.observe(viewLifecycleOwner, { isError ->
            isError.let {
                view.text_error.visibility = if (it) View.VISIBLE else View.GONE

                if (it) {
                    group_item.visibility = View.GONE
                }

                if (it) {
                    Toast.makeText(activity, getString(R.string.errorCode), Toast.LENGTH_SHORT).show()
                }
            }
        })

        followViewModel.loading.observe(viewLifecycleOwner, { isLoading ->
            isLoading.let {
                view.progress_bar.visibility = if (it) View.VISIBLE else View.GONE

                if (it) {
                    view.text_error.visibility = View.GONE
                } else {
                    view.progress_bar.visibility = View.GONE
                }
            }

        })
    }

    override fun onResume() {
        super.onResume()

        val username: String = arguments?.getString(USERNAME).toString()
        val page = arguments?.getInt(PAGE, 0) as Int

        if (page == 1) {
            followViewModel.getFollowers(username)
        }
        if (page == 2) {
            followViewModel.getFollowing(username)
        }
    }
}