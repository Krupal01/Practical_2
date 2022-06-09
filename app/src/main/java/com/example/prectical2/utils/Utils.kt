package com.example.prectical2.utils

class Utils {
    companion object{
        const val BASE_URL = "https://api.stackexchange.com"
        const val URL_PARAM = "/2.3/users"
        const val QUERY_PAGE_TAG = "page"
        const val QUERY_PAGESIZE_TAG = "pagesize"
        const val QUERY_SITE_TAG = "site"
        const val QUERY_SORT_TAG = "sort"

        var FIRST_PAGE = 1
        var QUERY_PAGESIZE_VALUE = 10
        var QUERY_SITE_VALUE = "stackoverflow"
        var SORT_BY_NAME = "name"
        var SORT_BY_REPUTATION = "reputation"
    }
}