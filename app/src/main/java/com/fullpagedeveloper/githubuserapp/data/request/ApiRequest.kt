package com.fullpagedeveloper.githubuserapp.data.request

import com.fullpagedeveloper.githubuserapp.data.model.Item
import com.fullpagedeveloper.githubuserapp.data.model.User
import com.fullpagedeveloper.githubuserapp.data.model.Users
import retrofit2.Call
import retrofit2.http.*

interface ApiRequest {

    //search/users?q={username}
    @GET("search/users")
    fun getSearch(
        @Header("Authorization") token: String,
        @Query("q") queryName: String,
        @Query("sort") sort: String,
        @Query("order") orders: String
    ): Call<User>

    @GET("/users/{username}")
    fun getDetail(
        @Header("Authorization") token: String,
        @Path("username") username: String
    ) :Call<Users>

    @GET("users")
    fun getDataListUsers(): Call<ArrayList<Item>>
}