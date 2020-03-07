package com.thomas.pagingstudy.ui.network

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.MenuItem
import com.thomas.pagingstudy.R
import com.thomas.pagingstudy.databinding.ActivityNetworkPagingBinding
import com.thomas.pagingstudy.ui.BindingActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.coroutines.CoroutineContext

class NetworkPagingActivity : BindingActivity<ActivityNetworkPagingBinding>() {

    override fun getLayoutResId() = R.layout.activity_network_paging

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.vm=getViewModel()
        binding.lifecycleOwner = this

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
