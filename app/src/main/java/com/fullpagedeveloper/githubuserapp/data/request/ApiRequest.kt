package com.fullpagedeveloper.githubuserapp.data.request

import com.fullpagedeveloper.githubuserapp.data.model.Item
import com.fullpagedeveloper.githubuserapp.data.model.User
import com.fullpagedeveloper.githubuserapp.data.model.UsersList
import retrofit2.Call
import retrofit2.http.*

interface ApiRequest {

    //search/users?q={username}
    @GET("search/users")
    fun getSearch(
        @Query("q") queryName: String,
        @Query("sort") sort: String,
        @Query("order") orders: String
    ): Call<User>

    @GET("/users/{username}")
    fun findUserDetailByUsername(
        @Path("username") username: String,
        @Header("Authorization") token: String
    ) :Call<User>

    @GET("users")
    fun getDataListUsers(): Call<List<UsersList>>
}