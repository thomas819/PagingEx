package com.thomas.pagingstudy.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.thomas.pagingstudy.R
import com.thomas.pagingstudy.databinding.ActivityMainBinding
import com.thomas.pagingstudy.ui.BindingActivity
import com.thomas.pagingstudy.ui.network.NetworkPagingActivity
import com.thomas.pagingstudy.ui.networkroom.RoomNetworkPagingActivity
import org.koin.android.ext.android.inject

class MainActivity : BindingActivity<ActivityMainBinding>() {

    private val mainViewModel: MainViewModel by inject()

    override fun getLayoutResId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.vm = mainViewModel
        binding.lifecycleOwner = this
        event()
    }

    private fun event() {
        mainViewModel.showPagingPage.observe(this, Observer {
            startActivity(Intent(this, NetworkPagingActivity::class.java))
        })

        mainViewModel.showRoomPagingPage.observe(this, Observer {
            startActivity(Intent(this, RoomNetworkPagingActivity::class.java))
        })
    }

}
