package com.thomas.pagingstudy.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.thomas.pagingstudy.core.BaseViewModel
import com.thomas.pagingstudy.data.remote.api.Api

class MainViewModel() : BaseViewModel() {
    private val _showPagingPage = MutableLiveData<Boolean?>()
    val showPagingPage: LiveData<Boolean?> get() = _showPagingPage

    private val _showRoomPagingPage = MutableLiveData<Boolean?>()
    val showRoomPagingPage: LiveData<Boolean?> get() = _showRoomPagingPage

    fun callShowPagingPage() {
        _showPagingPage.value = true
    }

    fun callShowRoomPagingPage() {
        _showRoomPagingPage.value = true
    }


}