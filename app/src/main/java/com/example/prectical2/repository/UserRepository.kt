package com.example.prectical2.repository

import com.example.prectical2.network.UserService
import com.example.prectical2.pagingsource.UserPagingSource
import javax.inject.Inject

class UserRepository @Inject constructor(private val userService: UserService) {

    fun getUserSource(): UserPagingSource {
        return UserPagingSource(userService = userService)
    }
}