package com.thomas.pagingstudy.di

import com.thomas.pagingstudy.data.remote.api.Api
import org.koin.dsl.module.module
import retrofit2.Retrofit

val apiModule = module {
    single(createOnStart = false) { get<Retrofit>().create(Api::class.java) }
}