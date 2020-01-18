package com.mx.mxseat.drawer

import android.graphics.*
import com.mx.mxseat.MXSeatView
import com.mx.mxseat.SeatConfig
import com.mx.mxseat.dp2px

class SeatDrawer(private val seatView: MXSeatView, private val config: SeatConfig) {
    private val backgroundPaint = Paint()
    private val headTextPaint = Paint()

    private val defaultSeatWidth = seatView.context.dp2px(20)
    private val marginBetweenSeat = seatView.context.dp2px(5)
    private val imgMatrix = Matrix()

    init {
        headTextPaint.style = Paint.Style.FILL
        headTextPaint.textSize = 29f
        headTextPaint.color = Color.BLACK
        headTextPaint.isAntiAlias = true

        backgroundPaint.color = Color.WHITE
    }

    fun drawSeats(canvas: Canvas, scale: Float) {
        val width = seatView.width
        val seatBitmap = config.seatBitmap!!
        val selectBitmap = config.seatCheckedBitmap!!
        val soldBitmap = config.seatSoldBitmap!!

        val rowSize = config.rowSize
        val columnSize = config.columnSize
        imgMatrix.setScale(config.imgScaleWidth, config.imgScaleHeight)

        var left = 0
        var top = config.headHeight
        for (row in (0 until rowSize)) {
            for (column in (0 until columnSize)) {
//                imgMatrix.postTranslate()
            }
        }
    }
}