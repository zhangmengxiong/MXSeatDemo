package com.mx.mxseat

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Matrix
import kotlin.math.roundToInt

class SeatConfig(context: Context) {
    var backgroundColor: Int = Color.WHITE
    var seatBitmap: Bitmap? = null // 默认座位图
    var seatSoldBitmap: Bitmap? = null // 已售图
    var seatCheckedBitmap: Bitmap? = null // 已选图

    var defaultImgWidth = context.dp2px(40) //头部高度
    var defaultImgHeight = context.dp2px(34) //头部高度

    var headHeight = context.dp2px(30).roundToInt() //头部高度

    var rowSize = 0 //行数
    var columnSize = 0 //列数


    var scaleMin = 0.6f
    var scaleMax = 1.8f

    var seatMatrix = Matrix()

    fun getSeatTranslateX(): Float {
//        val arr=
        val arr = FloatArray(9)
        seatMatrix.getValues(arr)
        return arr[Matrix.MTRANS_X]
    }

    fun getSeatTranslateY(): Float {
//        val arr=
        val arr = FloatArray(9)
        seatMatrix.getValues(arr)
        return arr[Matrix.MTRANS_Y]
    }

    fun getScale(): Float {
        val arr = FloatArray(9)
        seatMatrix.getValues(arr)
        return arr[Matrix.MSCALE_X]
    }
}