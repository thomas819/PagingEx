package com.thomas.pagingstudy.ui.network

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.thomas.pagingstudy.R
import com.thomas.pagingstudy.data.remote.domain.Item
import com.thomas.pagingstudy.databinding.ItemNetworkPagingBinding
import com.thomas.pagingstudy.databinding.ItemNetworkStateBinding
import com.thomas.pagingstudy.ui.BindingViewHolder
import com.thomas.pagingstudy.util.NetworkState

class NetworkPagingAdapter(val vm:NetworkPagingViewModel):PagedListAdapter<Item, RecyclerView.ViewHolder>(DIFF_CALLBACK){
    private var networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_network_paging -> NetworkPagingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_network_paging,parent,false))
            R.layout.item_network_state -> NetworkStateViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_network_state,parent,false))
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            R.layout.item_network_paging->{
                (holder as NetworkPagingViewHolder).run {
                    binding.items=getItem(position)
                    binding.vm=vm
                }
            }
            R.layout.item_network_state->{
                (holder as NetworkStateViewHolder).run {
                    binding.networkState=networkState
                    binding.vm=vm
                }
            }
        }
    }

    private fun hasExtraRow() = networkState != null && networkState != NetworkState.LOADED

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.item_network_state
        } else {
            R.layout.item_network_paging
        }
    }
    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    fun setNetworkState(newNetworkState: NetworkState?) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    class NetworkPagingViewHolder(view: View):BindingViewHolder<ItemNetworkPagingBinding>(view)
    class NetworkStateViewHolder(view:View):BindingViewHolder<ItemNetworkStateBinding>(view)

    companion object{
        private val DIFF_CALLBACK = object :DiffUtil.ItemCallback<Item>(){
            override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem.id==newItem.id
            override fun areContentsTheSame(oldItem: Item, newItem: Item)=oldItem==newItem
        }

    }
}