package com.example.prectical2.ui.compose

import android.content.Context
import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.prectical2.model.ItemsItem
import com.example.prectical2.viewmodel.UserViewModel
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun UserListCompose(
    modifier: Modifier = Modifier,
    viewModel: UserViewModel,
    context: Context
){

    val userItemList : LazyPagingItems<ItemsItem> = viewModel.userFlow.collectAsLazyPagingItems()

    LazyColumn{
        items(userItemList){item: ItemsItem? ->
            if (item != null) {
                Log.i("krupal",item.displayName.toString())
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