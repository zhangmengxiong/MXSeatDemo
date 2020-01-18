package com.mx.mxseat

import android.content.Context
import android.graphics.Bitmap
import kotlin.math.roundToInt

class SeatConfig(context: Context) {
    var seatBitmap: Bitmap? = null // 默认座位图
        set(value) {
            value?.let {
                val w = it.width.toFloat()
                val h = it.height.toFloat()
                imgScaleWidth = defaultImgWidth / w
                imgScaleHeight = defaultImgHeight / h
            }

            field = value
        }
    var seatSoldBitmap: Bitmap? = null // 已售图
    var seatCheckedBitmap: Bitmap? = null // 已选图

    var defaultImgWidth = context.dp2px(40).roundToInt() //头部高度
    var defaultImgHeight = context.dp2px(34).roundToInt() //头部高度
    var imgScaleWidth = 1f //默认图片X缩放
    var imgScaleHeight = 1f //默认图片Y缩放

    var headHeight = context.dp2px(30).roundToInt() //头部高度

    var rowSize = 0 //行数
    var columnSize = 0 //列数
}