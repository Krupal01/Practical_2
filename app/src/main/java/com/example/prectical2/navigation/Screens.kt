package com.example.prectical2.navigation

import com.example.prectical2.model.ItemsItem
import com.example.prectical2.utils.ARG_ITEMSITEM
import com.example.prectical2.utils.DETAILS_SCR_ROUTE
import com.example.prectical2.utils.MAIN_SCR_ROUTE
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class Screens(val route : String){
    object Main : Screens(route = MAIN_SCR_ROUTE)
    object Details : Screens(route = "$DETAILS_SCR_ROUTE/{$ARG_ITEMSITEM}"){
        fun passItemsItem(itemsItem: ItemsItem):String{

            // Before passing an URL as an argument , must encode it
            itemsItem.link = URLEncoder.encode(itemsItem.link, StandardCharsets.UTF_8.toString())
            itemsItem.profileImage = URLEncoder.encode(itemsItem.profileImage, StandardCharsets.UTF_8.toString())

            val itemsString = Gson().toJson(itemsItem)
            return this.route.replace(
                oldValue = "{$ARG_ITEMSITEM}",
                newValue = itemsString.toString()
            )
        }
    }
}
