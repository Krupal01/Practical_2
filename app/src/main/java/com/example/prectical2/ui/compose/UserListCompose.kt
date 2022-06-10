package com.example.prectical2.ui.compose

import android.content.Context
import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.prectical2.model.ItemsItem
import com.example.prectical2.utils.Utils
import com.example.prectical2.viewmodel.UserViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun UserListCompose(
    viewModel: UserViewModel,
    context: Context,
    lifecycleOwner: LifecycleOwner
){

    val userItemList : LazyPagingItems<ItemsItem> = viewModel.userFlow.collectAsLazyPagingItems()
    val rememberSwipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)

    viewModel.sortBy.observe(lifecycleOwner, Observer {
        userItemList.refresh()
    })

    SwipeRefresh(
        state = rememberSwipeRefreshState,
        onRefresh = {
            viewModel.clearSort()
            userItemList.refresh()
        }
    ) {
        rememberSwipeRefreshState.isRefreshing = userItemList.loadState.refresh is LoadState.Loading

        LazyColumn{
            items(userItemList){item: ItemsItem? ->
                if (item != null) {
                    val (isChecked, setChecked) = remember { mutableStateOf(false) }
                    UserRowItem(
                        itemsItem = item,
                        onCardClick = {},
                        isSaved = isChecked,
                        onCheckedChange = {
                            setChecked(!isChecked)
                        })
                }
            }
        }
    }



}