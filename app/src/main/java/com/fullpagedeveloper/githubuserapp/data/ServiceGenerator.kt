package com.fullpagedeveloper.githubuserapp.data
import com.fullpagedeveloper.githubuserapp.data.model.Follow
import com.fullpagedeveloper.githubuserapp.data.model.User
import com.fullpagedeveloper.githubuserapp.data.model.Users
import com.fullpagedeveloper.githubuserapp.data.request.ApiRequest
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceGenerator {

    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiRequest::class.java)

    fun getApiRequestSearch(token: String, q: String, sort: String, order: String): Call<User> {
        return retrofitBuilder.getSearch(token, q, sort, order)
    }

    fun getDetail(auth: String, username: String): Call<Users> {
        return retrofitBuilder.getDetail(auth, username)
    }

    fun getFollowers(auth: String, username: String): Call<ArrayList<Follow>> {
        return retrofitBuilder.getFollowers(auth, username)
    }

    fun getFollowing(auth: String, username: String): Call<ArrayList<Follow>> {
        return retrofitBuilder.getFollowing(auth, username)
    }
}
