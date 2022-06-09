package com.example.prectical2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.prectical2.model.ItemsItem
import com.example.prectical2.repository.UserRepository
import com.example.prectical2.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel()  {

    var userFlow : Flow<PagingData<ItemsItem>> = Pager(PagingConfig(pageSize = Utils.QUERY_PAGESIZE_VALUE)){
        userRepository.getUserSource()
    }.flow.cachedIn(viewModelScope)

}