package com.fullpagedeveloper.githubuserapp.ui.users

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fullpagedeveloper.githubuserapp.data.ServiceGenerator
import com.fullpagedeveloper.githubuserapp.data.model.Item
import com.fullpagedeveloper.githubuserapp.data.model.User
import com.fullpagedeveloper.githubuserapp.data.model.UsersList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UsersSearchViewModel: ViewModel() {

    private var _searchListUsers = MutableLiveData<List<Item>>()
    private var _usersList = MutableLiveData<List<UsersList>>()
    private val serviceGenerator = ServiceGenerator()


    fun getUsersList():MutableLiveData<List<UsersList>> {
        serviceGenerator.getApiRequestUsersLis().enqueue(object : Callback<List<UsersList>>{
            override fun onResponse(
                call: Call<List<UsersList>>,
                response: Response<List<UsersList>>
            ) {
                if (response.isSuccessful) {
                    _usersList.value = response.body()
                    Log.d("GOKIL", "=====> ${response.body() as List<UsersList>}")
                }
            }

            override fun onFailure(call: Call<List<UsersList>>, t: Throwable) {
                println(t.localizedMessage)
            }

        })
        return _usersList
    }


    fun getSearchList(username: String): MutableLiveData<List<Item>> {
        serviceGenerator.getApiRequestSearch(username,"repositories", "asc").enqueue(
            object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        Log.d("TAG", "onResponse: ${response.body()?.items.toString()}")
                        try {
                            _searchListUsers.value = response.body()?.items
                        } catch (e: Exception) {
                            println(e.localizedMessage)
                        }
                    } else {
                        Log.d("TAG", "onResponse: ===> ${response.body().toString()}")
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    t.localizedMessage
                    println(t.printStackTrace())
                }

            })
        return _searchListUsers
    }
}