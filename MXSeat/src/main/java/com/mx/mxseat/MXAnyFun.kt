package com.mx.mxseat

import android.content.Context

fun Context.dp2px(dp: Float): Float {
    return resources.displayMetrics.density * dp
}

fun Context.dp2px(dp: Int): Float {
    return resources.displayMetrics.density * dp
}