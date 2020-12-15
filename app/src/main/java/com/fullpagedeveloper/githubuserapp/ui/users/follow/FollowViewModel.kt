package com.fullpagedeveloper.githubuserapp.ui.users.follow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fullpagedeveloper.githubuserapp.data.Constant.Companion.AUTHKEY
import com.fullpagedeveloper.githubuserapp.data.ServiceGenerator
import com.fullpagedeveloper.githubuserapp.data.model.Follow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class FollowViewModel: ViewModel() {

    private val follow = MutableLiveData<ArrayList<Follow>>()
    private val serviceGenerator = ServiceGenerator()
    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun getFollowers(username: String): MutableLiveData<ArrayList<Follow>> {
        loading.value = true
        serviceGenerator.getFollowers(AUTHKEY, username).enqueue(object : Callback<ArrayList<Follow>>{
            override fun onResponse(
                call: Call<ArrayList<Follow>>,
                response: Response<ArrayList<Follow>>
            ) {
                if (response.isSuccessful) {
                    try {
                        follow.value = response.body()
                        loading.value = false
                        error.value = false
                    }catch (e: Exception){
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<Follow>>, t: Throwable) {
                println(t.localizedMessage)
                loading.value = false
                error.value = true
            }

        })
        return follow
    }

    fun getFollowing(username: String): MutableLiveData<ArrayList<Follow>> {
        loading.value = true
        serviceGenerator.getFollowing(AUTHKEY, username).enqueue(object : Callback<ArrayList<Follow>>{
            override fun onResponse(
                call: Call<ArrayList<Follow>>,
                response: Response<ArrayList<Follow>>
            ) {
                if (response.isSuccessful) {
                    follow.value = response.body()
                    loading.value = false
                    error.value = false
                } else {
                    if (response.code() in 400..511){
                        loading.value = false
                        error.value = true
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<Follow>>, t: Throwable) {
                println(t.localizedMessage)
                loading.value = false
                error.value = true
            }

        })

        return follow
    }
}