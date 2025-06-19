package com.pinup.barapp.domain.models

data class EventDetail(
    val title: String,
    val subtitle: String,
    val mainInfo: String,
    val timeTitle: String,
    val timeValue: String,
    val featuresTitle: String?,
    val features: List<String>?,
    val promoTitle: String?,
    val promoList: List<String>?,
    val extraTitle: String?,
    val extraList: List<String>?,
    val howToTitle: String,
    val howTo: String
)
