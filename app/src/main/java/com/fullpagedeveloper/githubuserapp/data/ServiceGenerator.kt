package com.fullpagedeveloper.githubuserapp.data
import com.fullpagedeveloper.githubuserapp.data.model.User
import com.fullpagedeveloper.githubuserapp.data.model.UsersList
import com.fullpagedeveloper.githubuserapp.data.request.ApiRequest
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceGenerator {

    /**
    Search : https://api.github.com/search/users?q={username}
    Detail user : https://api.github.com/users/{username}
    List Follower : https://api.github.com/users/{username}/followers
    List Following : https://api.github.com/users/{username}/following
    * */

    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiRequest::class.java)

    fun getApiRequestSearch(q: String, sort: String, order: String): Call<User> {
        return retrofitBuilder.getSearch(q, sort, order)
    }

    fun getApiRequestUsersLis(): Call<List<UsersList>>{
        return retrofitBuilder.getDataListUsers()
    }

    fun getUserDetailByUsername(username: String, auth: String): Call<User> {
        return retrofitBuilder.findUserDetailByUsername(username, auth)
    }
}