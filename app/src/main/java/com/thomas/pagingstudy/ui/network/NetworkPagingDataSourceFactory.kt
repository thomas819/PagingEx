package com.thomas.pagingstudy.ui.network

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.thomas.pagingstudy.data.remote.api.Api
import com.thomas.pagingstudy.data.remote.domain.Item
import io.reactivex.disposables.CompositeDisposable

class NetworkPagingDataSourceFactory(private val api: Api, private val param:MutableMap<String,Any>, private val disposable: CompositeDisposable) : DataSource.Factory<Int, Item>() {
    val sourceLiveData = MutableLiveData<NetworkPagingDataSource>()

    override fun create(): DataSource<Int, Item> {
        val source = NetworkPagingDataSource(api, param, disposable)
        sourceLiveData.postValue(source)
        return source
    }

}