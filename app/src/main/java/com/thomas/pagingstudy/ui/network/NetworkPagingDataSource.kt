package com.thomas.pagingstudy.ui.network

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.thomas.pagingstudy.data.remote.api.Api
import com.thomas.pagingstudy.data.remote.domain.Item
import com.thomas.pagingstudy.util.NetworkState
import com.thomas.pagingstudy.extensions.plusAssign
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors

class NetworkPagingDataSource(private val api: Api, private val param:MutableMap<String,Any>,private val disposable: CompositeDisposable) : PageKeyedDataSource<Int, Item>() {
    private var retryExecutor = Executors.newSingleThreadExecutor()
    private var retry: (() -> Any)? = null
    val networkState = MutableLiveData<NetworkState>()
    val initialLoad = MutableLiveData<NetworkState>()

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            retryExecutor.execute {
                it.invoke()
            }
        }
    }


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Item>) {
        disposable+=api.search(param,1)
                .flatMap {
                    if(0==it.totalCount){
                        networkState.postValue(NetworkState.EMPTY)
                        Single.error(IllegalStateException("no result"))
                    }else{
                        retry = null
                        Single.just(it.items)
                    }
                }
                .doOnSubscribe {
                    networkState.postValue(NetworkState.LOADING)
                    initialLoad.postValue(NetworkState.LOADING)
                }
                .subscribeOn(Schedulers.io())
                .subscribe({
                    networkState.postValue(NetworkState.LOADED)
                    initialLoad.postValue(NetworkState.LOADED)
                    callback.onResult(it, null, 2)
                },{
                    retry={
                        loadInitial(params,callback)
                    }
                    val error=NetworkState.error(it.message ?:"unknown error")
                    networkState.postValue(error)
                    initialLoad.postValue(error)
                })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
        disposable+=api.search(param,params.key)
                .doOnSubscribe {
                    networkState.postValue(NetworkState.LOADING)
                    initialLoad.postValue(NetworkState.LOADING)
                }
                .subscribeOn(Schedulers.io())
                .subscribe({
                    retry = null
                    val nextKey = if (params.key == it.totalCount) null else params.key + 1
                    callback.onResult(it.items, nextKey)
                    networkState.postValue(NetworkState.LOADED)
                },{
                    retry={
                        loadAfter(params,callback)
                    }
                    networkState.postValue(NetworkState.error(it.message ?:"unknown error"))
                })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {

    }

}



