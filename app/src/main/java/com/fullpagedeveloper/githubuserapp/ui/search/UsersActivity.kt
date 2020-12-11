package com.fullpagedeveloper.githubuserapp.ui.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fullpagedeveloper.githubuserapp.R
import com.fullpagedeveloper.githubuserapp.data.model.Item
import kotlinx.android.synthetic.main.activity_users.*


class UsersActivity : AppCompatActivity() {

    private lateinit var usersSearchViewModel : UsersSearchViewModel
    private val searchAdapter = SearchAdapter{click -> doClick(click)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        supportActionBar?.title = getString(R.string.users)

        usersSearchViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[UsersSearchViewModel::class.java]

        recyclerView_User.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchAdapter
        }

        listUsers()

        loadAndError()
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
                progress_bar.visibility = if (it) View.VISIBLE else View.GONE

                if (it) {
                    textView_Message.visibility = View.GONE
                    recyclerView_User.visibility = View.GONE
                }
            }
        })
    }

    private fun listUsers() {
        usersSearchViewModel.listUser().observe(this, {
            recyclerView_User.visibility = View.VISIBLE
            searchAdapter.setDataSearchView(ArrayList(it))

        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.popBackStack()
        supportActionBar?.title = getString(R.string.users)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.searchView).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_all_users)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean { return false }

            override fun onQueryTextChange(query: String): Boolean {
                if(query != ""){
                    searchListUsersView(query)
                }
                return false
            }
        })
        return true
    }

    private fun searchListUsersView(newText: String) {
        Handler(Looper.getMainLooper()).removeCallbacksAndMessages(null)
        Handler(Looper.getMainLooper()).postDelayed({
            usersSearchViewModel.getSearchList(newText).observe(this@UsersActivity, {
                if (it.isEmpty()) {
                    textView_Message.visibility = View.VISIBLE
                    progress_bar.visibility = View.GONE
                } else {
                    recyclerView_User.visibility = View.VISIBLE
                    searchAdapter.setDataSearchView(ArrayList(it))
                    searchAdapter.filter.filter(newText)
                }
            })
        }, 300)
    }
}