package com.example.prectical2.ui.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.prectical2.utils.Utils
import com.example.prectical2.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetContent(
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    coroutineScope: CoroutineScope,
    viewModel : UserViewModel,
    lifecycleOwner: LifecycleOwner
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 20.dp,topEnd = 20.dp)
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "SORT",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .weight(1F)
                        .padding(horizontal = 10.dp)
                )
                IconButton(onClick = {
                    coroutineScope.launch {
                        bottomSheetScaffoldState.bottomSheetState.collapse()
                    }
                }) {
                    Icon(Icons.Filled.Close, contentDescription = "close" ,tint = Color.Black)
                }
            }
            Divider(
                color = Color.Gray ,
                modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
            )
            RadioGroupBottomSheetItem(
                mItems = listOf(
                    Utils.SORT_BY_NAME,
                    Utils.SORT_BY_REPUTATION
                ),
                viewModel = viewModel,
                lifecycleOwner = lifecycleOwner,
                bottomSheetScaffoldState = bottomSheetScaffoldState,
                coroutineScope = coroutineScope
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RadioGroupBottomSheetItem(
    mItems: List<String>,
    viewModel: UserViewModel,
    lifecycleOwner: LifecycleOwner,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    coroutineScope: CoroutineScope,
){
    val mRememberObserver = remember { mutableStateOf(viewModel.sortBy.value) }

    viewModel.sortBy.observe(lifecycleOwner, Observer {
        mRememberObserver.value = it
    })

    Column {
        mItems.forEach { mItem ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(all = 5.dp)
            ) {
                Text(
                    text = mItem,
                    modifier = Modifier.padding(start = 8.dp).weight(1f),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                RadioButton(
                    selected = mRememberObserver.value == mItem,
                    onClick = {
                        mRememberObserver.value = mItem
                        viewModel.setSort(mItem)
                        coroutineScope.launch {
                            bottomSheetScaffoldState.bottomSheetState.collapse()
                        }
                    },
                    enabled = true,
                    colors = RadioButtonDefaults.colors(selectedColor = Color.Black)
                )

            }
        }
    }
}

