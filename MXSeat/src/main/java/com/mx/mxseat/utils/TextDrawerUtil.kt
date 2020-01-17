package com.mx.mxseat.utils

import android.graphics.Paint

object TextDrawerUtil {
    fun getBaseLine(paint: Paint, fromY: Int, toY: Int): Float {
        val fontMetrics = paint.fontMetrics
        return ((fromY + toY - fontMetrics.bottom - fontMetrics.top) / 2)
    }

    fun getTextWidth(paint: Paint, text: String): Float {
        return paint.measureText(text)
    }
}