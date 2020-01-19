package com.mx.mxseat.drawer

import android.graphics.*
import com.mx.mxseat.MXSeatView
import com.mx.mxseat.SeatConfig
import com.mx.mxseat.dp2px
import kotlin.math.max

class SeatDrawer(private val seatView: MXSeatView, private val config: SeatConfig) {
    private val backgroundPaint = Paint()
    private val headTextPaint = Paint()
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

        val margin = marginBetweenSeat * scale

        val transX = config.getSeatTranslateX()
        val transY = config.getSeatTranslateY()
        val imgWidth = (config.defaultImgWidth * scale)
        val imgHeight = (config.defaultImgHeight * scale)
        val imgScaleX = imgWidth / seatBitmap.width
        val imgScaleY = imgHeight / seatBitmap.height

        val rowSize = config.rowSize
        val columnSize = config.columnSize
        for (row in (0 until rowSize)) {
            val top = transY + config.headHeight + row * imgHeight + (row + 1) * margin
            for (column in (0 until columnSize)) {
                val left = transX + column * imgWidth + (column + 1) * margin
                imgMatrix.reset()
                imgMatrix.setScale(imgScaleX, imgScaleY)
                imgMatrix.postTranslate(left, top)
                canvas.drawBitmap(seatBitmap, imgMatrix, null)
            }
        }
    }
}