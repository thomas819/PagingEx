package com.thomas.pagingstudy.ui.network

import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.thomas.pagingstudy.data.remote.domain.Item

@BindingAdapter(value = ["items", "viewModel"])
fun setNetworkPaging(view: RecyclerView, items: PagedList<Item>?, vm: NetworkPagingViewModel) {
    view.adapter?.run {
        if(this is NetworkPagingAdapter) this.submitList(items)
    } ?: run{
        NetworkPagingAdapter(vm).apply {
            view.adapter = this
            this.submitList(items)
        }
    }
}