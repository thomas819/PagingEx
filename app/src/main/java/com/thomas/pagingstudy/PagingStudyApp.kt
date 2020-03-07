package com.thomas.pagingstudy

import android.app.Application
import com.thomas.pagingstudy.di.apiModule
import com.thomas.pagingstudy.di.networkModule
import com.thomas.pagingstudy.di.roomModule
import com.thomas.pagingstudy.di.viewModelModule
import org.koin.android.ext.android.startKoin


class PagingStudyApp : Application() {
    private val mainModule = listOf(apiModule,networkModule,roomModule,viewModelModule)


        override fun onCreate() {
        super.onCreate()
        startKoin(this, mainModule)
    }
}