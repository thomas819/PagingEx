package com.thomas.pagingstudy.data.remote.response

import com.google.gson.annotations.SerializedName
import com.thomas.pagingstudy.data.remote.domain.Item

data class RepoResponse(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("total_count")
    val totalCount: Int
)