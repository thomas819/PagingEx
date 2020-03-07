package com.thomas.pagingstudy.data.remote.api

import com.thomas.pagingstudy.data.remote.response.RepoResponse
import io.reactivex.Single
import retrofit2.http.*

interface Api{

    @GET("/search/repositories")
    fun search(@FieldMap params : MutableMap<String, String>): Single<RepoResponse>

}