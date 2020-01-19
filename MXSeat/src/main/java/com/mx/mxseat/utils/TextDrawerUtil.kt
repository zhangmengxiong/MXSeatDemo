package com.mx.mxseat.utils

import android.graphics.Paint

object TextDrawerUtil {
    /**
     * 获取画Text的BaseLine位置
     * @param paint 画笔
     * @param fromY Y轴起始高度
     * @param toY Y轴结束高度
     */
    fun getBaseLine(paint: Paint, fromY: Int, toY: Int): Float {
        val fontMetrics = paint.fontMetrics
        return ((fromY + toY - fontMetrics.bottom - fontMetrics.top) / 2)
    }

    fun getTextWidth(paint: Paint, text: String): Float {
        return paint.measureText(text)
    }
}