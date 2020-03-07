package com.thomas.pagingstudy.ui.network

import androidx.paging.PageKeyedDataSource
import com.thomas.pagingstudy.data.remote.domain.Item

class NetworkPagingDataSource ():PageKeyedDataSource<Int,Item>(){
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Item>
    ) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {

    }

}