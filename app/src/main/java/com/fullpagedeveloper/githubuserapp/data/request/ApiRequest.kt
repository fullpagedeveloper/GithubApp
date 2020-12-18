package com.fullpagedeveloper.githubuserapp.data.request

import com.fullpagedeveloper.githubuserapp.data.model.Follow
import com.fullpagedeveloper.githubuserapp.data.model.Item
import com.fullpagedeveloper.githubuserapp.data.model.User
import com.fullpagedeveloper.githubuserapp.data.model.Users
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

interface ApiRequest {

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

    @GET("users/{username}/followers")
    fun getFollowers(
        @Header("Authorization") token: String,
        @Path("username") username: String
    ): Call<ArrayList<Follow>>

    @GET("users/{username}/following")
    fun getFollowing(
        @Header("Authorization") token: String,
        @Path("username") username: String
    ): Call<ArrayList<Follow>>
}
