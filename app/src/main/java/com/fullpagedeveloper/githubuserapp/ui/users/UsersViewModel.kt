package com.fullpagedeveloper.githubuserapp.ui.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fullpagedeveloper.githubuserapp.data.ServiceGenerator
import com.fullpagedeveloper.githubuserapp.data.model.Users
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersViewModel : ViewModel() {

    private val users = MutableLiveData<Users>()
    private val serviceGenerator = ServiceGenerator()

    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun users(username: String): MutableLiveData<Users> {
        loading.value = true
        serviceGenerator.getDetail("b4a9ac8e5947c60af7ece29de72523eada7ac885", username).enqueue(
            object : Callback<Users> {
                override fun onResponse(call: Call<Users>, response: Response<Users>) {
                    if (response.isSuccessful) {
                        users.value = response.body()
                        loading.value = false
                        error.value = false
                    }
                }

                override fun onFailure(call: Call<Users>, t: Throwable) {
                    t.printStackTrace()
                    loading.value = false
                    error.value = true
                }

            })
        return users
    }

}