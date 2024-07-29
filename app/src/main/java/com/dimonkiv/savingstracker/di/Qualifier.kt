package com.dimonkiv.savingstracker.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IO

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Default
