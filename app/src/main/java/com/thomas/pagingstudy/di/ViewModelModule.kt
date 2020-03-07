package com.thomas.pagingstudy.di


import com.thomas.pagingstudy.ui.main.MainViewModel
import com.thomas.pagingstudy.ui.network.NetworkPagingViewModel
import org.koin.dsl.module.module
import kotlin.math.sin

val viewModelModule= module {
    single { MainViewModel() }
    single { NetworkPagingViewModel(get()) }
}