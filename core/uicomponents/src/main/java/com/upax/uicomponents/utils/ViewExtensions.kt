package com.upax.uicomponents.utils

import android.view.View

fun View.isVisible(isVisible: Boolean){
    if(isVisible) visibility = View.VISIBLE else View.GONE
}