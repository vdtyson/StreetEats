package com.versilistyson.androidstreeteats.data.util

interface Mappable<out MapTo> {
    fun map(): MapTo
}