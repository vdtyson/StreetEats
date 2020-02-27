package com.versilistyson.androidstreeteats.domain.common

interface Mappable<out MapTo> {
    fun map(): MapTo
}