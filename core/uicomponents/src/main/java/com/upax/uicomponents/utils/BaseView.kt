package com.upax.uicomponents.utils

internal interface BaseView <T> {
    fun getView(type: Int = 0) : T
}