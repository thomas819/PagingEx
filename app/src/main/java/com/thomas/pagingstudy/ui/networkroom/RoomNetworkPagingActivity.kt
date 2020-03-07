package com.thomas.pagingstudy.ui.networkroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thomas.pagingstudy.R
import com.thomas.pagingstudy.databinding.ActivityRoomNetworkPagingBinding
import com.thomas.pagingstudy.ui.BindingActivity

class RoomNetworkPagingActivity : BindingActivity<ActivityRoomNetworkPagingBinding>(){

    override fun getLayoutResId()=R.layout.activity_room_network_paging

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_network_paging)
    }


}
