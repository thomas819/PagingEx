package com.thomas.pagingstudy.util

@Suppress("DataClassPrivateConstructor")
data class NetworkState private constructor(
    val status: Status,
    val msg: String? = null
) {

    enum class Status {
        RUNNING,
        SUCCESS,
        EMPTY,
        FAILED
    }

    companion object {
        val EMPTY = NetworkState(Status.EMPTY)
        val LOADED = NetworkState(Status.SUCCESS)
        val LOADING = NetworkState(Status.RUNNING)
        fun error(msg: String?) = NetworkState(Status.FAILED, msg)
    }
}