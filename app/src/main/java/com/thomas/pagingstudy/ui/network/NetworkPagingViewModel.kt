package com.thomas.pagingstudy.ui.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import com.thomas.pagingstudy.core.BaseViewModel
import com.thomas.pagingstudy.data.remote.api.Api
import com.thomas.pagingstudy.data.remote.domain.Item
import com.thomas.pagingstudy.util.NetworkState
import com.thomas.pagingstudy.util.pagedListConfig
import io.reactivex.disposables.CompositeDisposable

class NetworkPagingViewModel(private val api: Api) : BaseViewModel() {
    private var query: String = "android"
        get() = if (field.isEmpty()) "" else field

    private val _items = MutableLiveData<List<Item>?>()
    val items: MutableLiveData<List<Item>?> get() = _items

    val source : NetworkPagingDataSourceFactory by lazy {  NetworkPagingDataSourceFactory(api, param, getDisposable()) }

    private val param = mutableMapOf<String, Any>().apply {
        this["q"] = "$query+language:kotlin"
        this["sort"] = "stars"
        this["per_page"] = 10
    }

    val data = LivePagedListBuilder(source, pagedListConfig()).build()

    fun networkState(): MutableLiveData<NetworkState>? {
        return source.sourceLiveData.value?.networkState
    }

    fun retry() {
        source.sourceLiveData.value?.retryAllFailed()
    }

    fun refresh() {
        source.sourceLiveData.value?.invalidate()
    }

    fun onQueryChange(query: CharSequence) {
        this.query = query.toString()
        print("@@${query.toString()}")
    }


}