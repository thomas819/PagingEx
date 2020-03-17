package com.thomas.pagingstudy.extensions

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.thomas.pagingstudy.util.NetworkState

@BindingAdapter("refreshing")
fun SwipeRefreshLayout.refreshing(visible: NetworkState?) {
    isRefreshing = visible == NetworkState.LOADING
}
