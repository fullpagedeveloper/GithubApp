package com.fullpagedeveloper.githubuserapp.ui.users.detail

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fullpagedeveloper.githubuserapp.R
import com.fullpagedeveloper.githubuserapp.data.model.Item
import com.fullpagedeveloper.githubuserapp.extention.getProgresDrawable
import com.fullpagedeveloper.githubuserapp.extention.loadImage
import kotlinx.android.synthetic.main.fragment_detail_users.*
import kotlinx.android.synthetic.main.fragment_detail_users.view.*
import java.util.*


class DetailUsersFragment : Fragment() {

    private lateinit var usersViewModel: DetailUsersViewModel
    private var users: String? = null

    companion object {
        private const val USERNAME = "username_follow"

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
        setHasOptionsMenu(true)

        progress_bar.visibility = View.GONE

        (activity as AppCompatActivity?)!!.supportActionBar?.title = getString(R.string.detail_user)
        usersViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailUsersViewModel::class.java]

        users = arguments?.getString(USERNAME)

        val sectionPageAdapter = ViewPagerAdapter(
            view.context,
            users.toString(),
            childFragmentManager
        )
        viewPager.adapter = sectionPageAdapter
        tabLayout.setupWithViewPager(viewPager)

        dataDetail(users.toString(), view)
        loadAndError()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.removeItem(R.id.searchView)
    }

    private fun loadAndError() {
        usersViewModel.error.observe(viewLifecycleOwner, { isError ->
            isError.let {
                text_error.visibility = if (it) View.VISIBLE else View.GONE

                if (it) {
                    group_item.visibility = View.GONE
                }

                if (it) {
                    Toast.makeText(activity, getString(R.string.errorCode), Toast.LENGTH_SHORT).show()
                }
            }
        })

        usersViewModel.loading.observe(viewLifecycleOwner, { isLoading ->
            isLoading.let {
                progress_bar.visibility = if (it) View.VISIBLE else View.GONE

                if (it) {
                    text_error.visibility = View.GONE
                } else {
                    group_item.visibility = View.VISIBLE
                    progress_bar.visibility = View.GONE
                }
            }

        })
    }

    private fun dataDetail(users: String, view: View) {
        usersViewModel.users(users)
        usersViewModel._users.observe(viewLifecycleOwner, {
            view.iv_Avatar?.loadImage(
                it.avatarUrl,
                getProgresDrawable(view.iv_Avatar.context.applicationContext)
            )
            view.tv_Name.text = it.login.capitalize(Locale.ROOT)
            view.tv_Company.text = it.company
            view.tv_Username.text = it.updatedAt
            view.tv_Location.text = it.location
            view.tv_Repo.text = it.publicRepos.toString()
            view.tv_Follower.text = it.followers.toString()
            view.tv_Following.text = it.following.toString()
        })
    }
}