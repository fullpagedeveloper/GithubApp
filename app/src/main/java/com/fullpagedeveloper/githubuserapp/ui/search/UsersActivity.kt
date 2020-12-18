package com.fullpagedeveloper.githubuserapp.ui.search

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fullpagedeveloper.githubuserapp.R
import com.fullpagedeveloper.githubuserapp.data.model.Item
import com.fullpagedeveloper.githubuserapp.ui.users.detail.DetailUsersFragment
import kotlinx.android.synthetic.main.activity_users.*


class UsersActivity : AppCompatActivity() {

    private lateinit var usersSearchViewModel : UsersSearchViewModel
    private val searchAdapter = SearchAdapter{ click -> doClick(click)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        supportActionBar?.title = getString(R.string.users)

        usersSearchViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(UsersSearchViewModel::class.java)

        setupSearchView()
        showRecyclerView()
        loadAndError()

//         usersSearchViewModel.searchListUsers.observe(this, {
//             Log.i("HAHAH", "queyy ->:11 ")
//             searchAdapter.addAll(ArrayList(it))
//             recyclerView_User.visibility = View.VISIBLE
//         })
    }

    private fun showRecyclerView() {
        recyclerView_User.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchAdapter
        }
    }


    private fun doClick(itemList: Item) {
        supportFragmentManager.beginTransaction().apply {
            val usersFragment = DetailUsersFragment.newInstance(itemList)
            replace(R.id.framRoot, usersFragment)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            addToBackStack(null)
            commit()
        }
    }

    private fun loadAndError() {
        usersSearchViewModel.error.observe(this@UsersActivity, { isError ->
            isError?.let {
                textView_Message.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        usersSearchViewModel.loading.observe(this@UsersActivity, { isLoading ->
            isLoading?.let {
                Log.d("Tessss", "load $isLoading")
                progress_bar.visibility = if (it) View.VISIBLE else View.GONE

                if (it) {
                    Log.d("Tessss", "load uu $it")
                    textView_Message.visibility = View.GONE
                    recyclerView_User.visibility = View.GONE
                }
            }
        })
    }


    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount < 0) {
            supportFragmentManager.popBackStack()
            supportActionBar?.title = getString(R.string.users)
        } else {
            super.onBackPressed()
        }
    }

    private fun setupSearchView() {
        searchView.queryHint = resources.getString(R.string.search_all_users)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    searchUsers(it)
                } ?: run {
                    Toast.makeText(this@UsersActivity, "Enter search criteria", Toast.LENGTH_SHORT).show()
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

//                if (newText.isNotEmpty()) {
//                    //usersSearchViewModel.getSearchList(newText)
//
//                } else {
//                    recyclerView_User.visibility = View.GONE
//                    showNoResults()
//                }
                return false
            }

        })
    }

    private fun searchUsers(query: String) {

    }

    private fun showNoResults() {
        searchAdapter.clearAll()
        textView_Message.visibility = View.VISIBLE
        textView_Message.text = getString(R.string.no_results_found)
    }

    private fun searchListUsersView(newText: String) {

    }
}