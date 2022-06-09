package com.example.prectical2.ui.activity

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.prectical2.ui.compose.BottomSheetContent
import com.example.prectical2.ui.compose.UserListCompose
import com.example.prectical2.ui.theme.Prectical2Theme
import com.example.prectical2.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val userViewModel : UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Prectical2Theme {
                MainScreenCompose(viewModel = userViewModel,context = this,lifecycleOwner = this)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreenCompose(
    viewModel: UserViewModel,
    context : Context,
    lifecycleOwner: LifecycleOwner
){
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed))
    // Declaing Coroutine scope
    val coroutineScope = rememberCoroutineScope()

    val buttonText = remember { mutableStateOf("Sort") }

    viewModel.sortBy.observe(lifecycleOwner, Observer {
        if (it != null){
            buttonText.value = "Sort : "+it.toString()
        }else{
            buttonText.value = "Sort"
        }
    })


    BottomSheetScaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {Text(text = "Practical 2")}
            )
        },
        scaffoldState = bottomSheetScaffoldState,
        sheetContent =  {
            BottomSheetContent(
                bottomSheetScaffoldState = bottomSheetScaffoldState,
                coroutineScope = coroutineScope,
                viewModel = viewModel
            )
        },
        sheetPeekHeight = 0.dp
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Button(
                onClick = {
                    coroutineScope.launch {
                        if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                            bottomSheetScaffoldState.bottomSheetState.expand()
                        } else {
                            bottomSheetScaffoldState.bottomSheetState.collapse()
                        }
                    }
                },
                modifier = Modifier.align(alignment = Alignment.End).padding(vertical = 10.dp,horizontal = 5.dp),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(text = buttonText.value, color = Color.White)
            }

            //Pagination List View
            UserListCompose(viewModel = viewModel, context = context,lifecycleOwner = lifecycleOwner)
        }
    }
}