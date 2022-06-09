package com.example.prectical2.pagingsource

import android.util.Log
import android.widget.Toast
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.prectical2.model.ItemsItem
import com.example.prectical2.network.UserService
import com.example.prectical2.utils.Utils
import javax.inject.Inject

class UserPagingSource @Inject constructor(val userService: UserService) : PagingSource<Int, ItemsItem>() {
    override fun getRefreshKey(state: PagingState<Int, ItemsItem>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemsItem> {
        return try {

            val page = params.key?:Utils.FIRST_PAGE
            val data = userService.getUser(page,Utils.QUERY_PAGESIZE_VALUE,Utils.QUERY_SITE_VALUE,Utils.SORT_BY_NAME)
            LoadResult.Page(
                data = data.items,
                prevKey = if (page == Utils.FIRST_PAGE) null else page-1,
                nextKey = if (data.hasMore==true) page+1 else null
            )

        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}