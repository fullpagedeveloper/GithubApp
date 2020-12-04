package com.fullpagedeveloper.githubuserapp.ui.users

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fullpagedeveloper.githubuserapp.R
import com.fullpagedeveloper.githubuserapp.data.model.Item
import kotlinx.android.synthetic.main.activity_users.*


class UsersActivity : AppCompatActivity() {

    var viewModel = UsersSearchViewModel()
    //private val usersAdapter = SearchAdapter()
    private val userLisAdapter = UsersListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        usersListView()

        supportActionBar?.title = ("Users")

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[UsersSearchViewModel::class.java]

        recyclerView_User.apply {
            layoutManager = LinearLayoutManager(context)
           // adapter = usersAdapter
            adapter = userLisAdapter
        }
    }

    private fun usersListView() {
        recyclerView_User.visibility = View.VISIBLE
        Handler(Looper.getMainLooper()).removeCallbacksAndMessages(null)
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.getUsersList().observe(this, { usersListView ->
                for (users in usersListView) {
                    viewModel.getSearchList(users.login).observe(this, Observer {
                        userLisAdapter.setDataUsersList(ArrayList(usersListView))

                    })
                }
            })
        },300)
    }

    private fun doClick(itemList: Item) {
        supportFragmentManager.beginTransaction().apply {
            //val fragment = DetailUsersFragment.newInstance(itemList)
            // replace(R.id.framRoot, fragment)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            addToBackStack(null)
            commit()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.popBackStack()
        supportActionBar?.title = ("Users")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.searchView).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_all_users)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("GOKIL", "onQueryTextSubmit: $query")
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                if (query.isEmpty()) {
                } else {
                    Log.d("GOKIL", "onQueryTextChange: ----> $query")
                    searchListUsersView(query)
                }
                return false
            }
        })
        return true
    }

    private fun searchListUsersView(newText: String) {
        recyclerView_User.visibility = View.VISIBLE
        Handler(Looper.getMainLooper()).removeCallbacksAndMessages(null)
        Handler(Looper.getMainLooper()).postDelayed({
//            viewModel.getSearchList(newText).observe(this@UsersActivity, Observer {
//                usersAdapter.setDataSearchView(ArrayList(it))
//                usersAdapter.filter.filter(newText)
//            })
        }, 300)
    }
}