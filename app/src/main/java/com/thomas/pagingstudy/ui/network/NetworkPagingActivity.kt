package com.thomas.pagingstudy.ui.network

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.thomas.pagingstudy.R
import com.thomas.pagingstudy.databinding.ActivityNetworkPagingBinding
import com.thomas.pagingstudy.ui.BindingActivity

class NetworkPagingActivity : BindingActivity<ActivityNetworkPagingBinding>() {

    override fun getLayoutResId() = R.layout.activity_network_paging

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network_paging)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
