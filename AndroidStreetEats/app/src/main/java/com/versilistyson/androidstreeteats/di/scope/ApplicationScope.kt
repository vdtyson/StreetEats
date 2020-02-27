package com.versilistyson.androidstreeteats.di.scope

import java.lang.annotation.RetentionPolicy
import javax.inject.Scope
import javax.inject.Singleton

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
@Singleton
annotation class ApplicationScope