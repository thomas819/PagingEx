package com.thomas.pagingstudy.ui.network

import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.thomas.pagingstudy.data.remote.domain.Item
import com.thomas.pagingstudy.util.NetworkState

@BindingAdapter(value = ["items", "viewModel","networkState"])
fun setNetworkPaging(view: RecyclerView, items: PagedList<Item>?, vm: NetworkPagingViewModel,networkState:NetworkState?) {
    view.adapter?.run {
        if(this is NetworkPagingAdapter) {
            setNetworkState(networkState)
            submitList(items)
        }
    } ?: run{
        NetworkPagingAdapter(vm).apply {
            view.adapter = this
            this.submitList(items)
        }
    }
}