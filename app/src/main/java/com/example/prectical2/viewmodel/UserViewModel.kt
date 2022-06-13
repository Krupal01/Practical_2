package com.example.prectical2.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.prectical2.db.ItemDao
import com.example.prectical2.model.ItemsItem
import com.example.prectical2.repository.UserRepository
import com.example.prectical2.utils.LocalPermission
import com.example.prectical2.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val itemDao: ItemDao,
    @ApplicationContext context:Context
    ) : ViewModel()  {


    var userFlow : Flow<PagingData<ItemsItem>> =
        if (LocalPermission.checkForInternet(context = context)){
            Pager(PagingConfig(pageSize = Utils.QUERY_PAGESIZE_VALUE)){
                userRepository.getUserSource()
            }.flow.cachedIn(viewModelScope)
        }else{
            Pager(PagingConfig(pageSize = Utils.QUERY_PAGESIZE_VALUE)){
                itemDao.getSavedItemsPagingSource()
            }.flow.cachedIn(viewModelScope)
        }


    var sortBy = MutableLiveData(Utils.SORT_BY_NAME)
    var BookmaredUsers = MutableLiveData<List<ItemsItem>>(listOf())

    fun clearSort(){
        sortBy.value = Utils.SORT_BY_NAME
        Utils.sortBy = Utils.SORT_BY_NAME
    }

    fun setSort(sortBy : String){
        this.sortBy.value = sortBy
        Utils.sortBy = sortBy
    }

    fun saveBookmark(itemsItem: ItemsItem) = viewModelScope.launch {
        itemDao.insertItem(itemsItem)
        updateLocalBookmarked()
    }

    fun removeBookmark(itemsItem: ItemsItem) = viewModelScope.launch {
        itemDao.deleteItems(itemsItem = itemsItem)
        updateLocalBookmarked()
    }

    private fun updateLocalBookmarked() = viewModelScope.launch {
        BookmaredUsers.value = itemDao.getSavedItems()
    }

    init {
        updateLocalBookmarked()
    }
}