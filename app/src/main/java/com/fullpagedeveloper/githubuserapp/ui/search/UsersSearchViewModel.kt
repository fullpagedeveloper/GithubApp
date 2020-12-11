package com.fullpagedeveloper.githubuserapp.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun getSearchList(username: String): MutableLiveData<List<Item>> {
        loading.value = true
        serviceGenerator.getApiRequestSearch("b4a9ac8e5947c60af7ece29de72523eada7ac885", username,"repositories", "asc").enqueue(
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
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    error.value = true
                    loading.value = false
                    println(t.printStackTrace())
                }

            })
        return _searchListUsers
    }


    fun listUser(): MutableLiveData<ArrayList<Item>> {
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