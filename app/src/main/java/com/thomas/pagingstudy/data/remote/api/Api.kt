package com.thomas.pagingstudy.data.remote.api

import com.thomas.pagingstudy.data.remote.response.RepoResponse
import io.reactivex.Single
import retrofit2.http.*

interface Api{
    @GET("/search/repositories")
    fun search(@QueryMap params : MutableMap<String, Any>, @Query("page") page:Int): Single<RepoResponse>

}