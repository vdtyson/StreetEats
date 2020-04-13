package com.versilistyson.androidstreeteats.data.firebase.models.business

data class StorefrontDto(
    val businessId: String,
    val schedule: WeekSchedule,
    val isOpen: Boolean
)