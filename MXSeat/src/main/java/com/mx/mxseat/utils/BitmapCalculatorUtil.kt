package com.mx.mxseat.utils

import android.graphics.Bitmap

object BitmapCalculatorUtil {
    fun getScaleBySize(bitmap: Bitmap, w: Float, h: Float): Pair<Float, Float> {
        val scaleX = w / bitmap.width
        val scaleY = h / bitmap.height
        return Pair(scaleX, scaleY)
    }
}