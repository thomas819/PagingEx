package com.thomas.pagingstudy.ui.network

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.thomas.pagingstudy.R
import com.thomas.pagingstudy.data.remote.domain.Item
import com.thomas.pagingstudy.databinding.ItemNetworkPagingBinding
import com.thomas.pagingstudy.ui.BindingViewHolder

class NetworkPagingAdapter(val vm:NetworkPagingViewModel):PagedListAdapter<Item, NetworkPagingAdapter.NetworkPagingViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NetworkPagingViewHolder {
        return NetworkPagingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_network_paging,parent,false))
    }

    override fun onBindViewHolder(holder: NetworkPagingViewHolder, position: Int) {
        getItem(position)?.run {
            holder.binding.items=this
            holder.binding.vm=vm
        }
    }

    class NetworkPagingViewHolder(view: View):BindingViewHolder<ItemNetworkPagingBinding>(view)

    companion object{
        private val DIFF_CALLBACK = object :DiffUtil.ItemCallback<Item>(){
            override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem.id==newItem.id
            override fun areContentsTheSame(oldItem: Item, newItem: Item)=oldItem==newItem
        }

    }
}