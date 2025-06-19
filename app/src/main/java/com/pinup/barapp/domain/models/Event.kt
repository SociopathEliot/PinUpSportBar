package com.pinup.barapp.domain.models

data class Event(
    val id: Int,
    val title: String,
    val description: String,
    val imageRes: Int,
    val detail: EventDetail? = null
)


