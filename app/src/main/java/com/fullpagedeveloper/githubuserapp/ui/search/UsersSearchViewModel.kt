package com.fullpagedeveloper.githubuserapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fullpagedeveloper.githubuserapp.data.Constant.Companion.AUTHKEY
import com.fullpagedeveloper.githubuserapp.data.ServiceGenerator
import com.fullpagedeveloper.githubuserapp.data.model.Item
import com.fullpagedeveloper.githubuserapp.data.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UsersSearchViewModel: ViewModel() {

    private var _searchListUsers = MutableLiveData<List<Item>>()
    private var _listUsers = MutableLiveData<ArrayList<Item>>()
    private val serviceGenerator = ServiceGenerator()
    private val repositories = "repositories"
    private val order = "asc"

    val isi : LiveData<List<Item>> get() = _searchListUsers

    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun getSearchList(username: String) {
        loading.value = true
        serviceGenerator.getApiRequestSearch(AUTHKEY, username,repositories, order).enqueue(
            object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        try {
                            _searchListUsers.value = response.body()?.items
                            error.value = false
                            loading.value = false
                        } catch (e: Exception) {
                            println(e.localizedMessage)
                        }
                    } else {
                        if (response.code() in 400..511){
                            loading.value = false
                            error.value = true
                        }
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    error.value = true
                    loading.value = false
                    println(t.localizedMessage)
                }

            })
//        return _searchListUsers
    }

    fun respSearchList(): LiveData<List<Item>>{
        return  _searchListUsers
    }


    fun listUser(): LiveData<ArrayList<Item>> {
        loading.value = true
        serviceGenerator.getApiRequestUsersLis().enqueue(
            object : Callback<ArrayList<Item>> {
                override fun onResponse(
                    call: Call<ArrayList<Item>>,
                    response: Response<ArrayList<Item>>
                ) {
                    if (response.isSuccessful) {
                        try {
                            _listUsers.value = response.body()
                            error.value = false
                            loading.value = false
                        } catch (e: Exception) {
                            println(e.localizedMessage)
                        }
                    } else {
                        if (response.code() in 400..511){
                            loading.value = false
                            error.value = true
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<Item>>, t: Throwable) {
                    error.value = true
                    loading.value = false
                    println(t.printStackTrace())
                }

            })
        return _listUsers
    }
}