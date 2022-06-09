package com.example.prectical2.network

import com.example.prectical2.model.UserModel
import com.example.prectical2.utils.Utils
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET(Utils.URL_PARAM)
    suspend fun getUser(
        @Query(Utils.QUERY_PAGE_TAG)page:Int ,
        @Query(Utils.QUERY_PAGESIZE_TAG)page_size:Int,
        @Query(Utils.QUERY_SITE_TAG)site : String
    ) : UserModel                                       //without sort

    @GET(Utils.URL_PARAM)
    suspend fun getUserSort(
        @Query(Utils.QUERY_PAGE_TAG)page:Int ,
        @Query(Utils.QUERY_PAGESIZE_TAG)page_size:Int,
        @Query(Utils.QUERY_SITE_TAG)site : String,
        @Query(Utils.QUERY_SORT_TAG)sort : String,
    ) : UserModel                                       //with sort

}