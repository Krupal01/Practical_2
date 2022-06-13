package com.example.prectical2.ui.compose

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.prectical2.model.ItemsItem
import com.example.prectical2.navigation.Screens
import com.example.prectical2.ui.activity.MainScreenCompose
import com.example.prectical2.utils.ARG_ITEMSITEM
import com.example.prectical2.utils.Utils
import com.example.prectical2.viewmodel.UserViewModel
import com.google.gson.Gson

@Composable
fun NavHostScreen(
    navController : NavHostController,
    viewModel : UserViewModel,
    context : Context,
    lifecycleOwner: LifecycleOwner
    ) {

    NavHost(navController = navController, startDestination = Screens.Main.route){
        composable(
            route = Screens.Main.route
        ){
            MainScreenCompose(
                viewModel = viewModel,
                context = context ,
                lifecycleOwner = lifecycleOwner,
                navController = navController
            )
        }

        composable(
            route = Screens.Details.route,
            arguments = listOf(
                navArgument(ARG_ITEMSITEM){
                    type = NavType.StringType
                }
            )
        ){
            val arg = it.arguments?.getString(ARG_ITEMSITEM).toString()
            val itemsItem = Gson().fromJson(arg, ItemsItem::class.java)
            Log.i(Utils.LOG_TAG, arg)
            DetailScreen(itemsItem = itemsItem)
        }
    }

}