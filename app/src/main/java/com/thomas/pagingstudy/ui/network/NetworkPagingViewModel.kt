package com.thomas.pagingstudy.ui.network

import com.thomas.pagingstudy.core.BaseViewModel
import com.thomas.pagingstudy.data.remote.api.Api

class NetworkPagingViewModel(private val api: Api) :BaseViewModel(){
    private var query: String = ""
        get() = if (field.isEmpty()) "" else field

    fun doSearch(){

        val params = mutableMapOf<String, String>().apply {
            this["q"] = query
            this["sort"] = "stars"
        }

        addToDisposable(
            api.search(params)
            .subscribe({

            },{

            }))

        getDisposable()
    }

    fun onQueryChange(query: CharSequence) {
        this.query = query.toString()
    }

}