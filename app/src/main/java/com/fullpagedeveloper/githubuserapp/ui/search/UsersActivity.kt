package com.fullpagedeveloper.githubuserapp.ui.search

import android.os.Bundle
import android.os.Handler
import android.os.Looper
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

        //listUsers()
        showRecyclerView()
        searchViewQuery()

//        if (savedInstanceState != null) {
//            usersSearchViewModel.getSearchList(searchView.query.toString()).observe(this@UsersActivity, {
//                Log.d("HHHHHH", "GET DATA savedInstanceState $it")
//                searchAdapter.setDataSearchView(ArrayList(it))
//            })
//        }

//        usersSearchViewModel.respSearchList().observe(this@UsersActivity, {
//            Log.i("HAHAH", "queyy ->:11 ")
//            searchAdapter.setDataSearchView(ArrayList(it))
//            recyclerView_User.visibility = View.VISIBLE
//        })
         usersSearchViewModel.isi.observe(this, {
             Log.i("HAHAH", "queyy ->:11 ")
             searchAdapter.setDataSearchView(ArrayList(it))
             recyclerView_User.visibility = View.VISIBLE
         })
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

                if (it) {
                    Toast.makeText(
                        this@UsersActivity,
                        getString(R.string.errorCode),
                        Toast.LENGTH_SHORT
                    ).show()
                }
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

//    private fun listUsers() {
//        usersSearchViewModel.listUser().observe(this, {
//            recyclerView_User.visibility = View.VISIBLE
//            searchAdapter.setDataSearchView(ArrayList(it))
//
//        })
//    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount < 0) {
            supportFragmentManager.popBackStack()
            supportActionBar?.title = getString(R.string.users)
        } else {
            super.onBackPressed()
        }
    }


    private fun searchViewQuery() {
        searchView.queryHint = resources.getString(R.string.search_all_users)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Log.d("HHHHHH", "GET QUERY ===> $query")

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isBlank()) {
                    Toast.makeText(this@UsersActivity, "kosong", Toast.LENGTH_SHORT).show()
                    Log.d("HAHAH", " Search -> anjayy kosong")
                    recyclerView_User.visibility = View.GONE
                } else {
                    usersSearchViewModel.getSearchList(newText)
                }
                return false
            }

        })
    }

    private fun searchListUsersView(newText: String) {

    }
}