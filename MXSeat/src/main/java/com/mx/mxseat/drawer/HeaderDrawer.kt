package com.mx.mxseat.drawer

import android.graphics.*
import com.mx.mxseat.MXSeatView
import com.mx.mxseat.dp2px
import com.mx.mxseat.utils.BitmapCalculatorUtil
import com.mx.mxseat.utils.TextDrawerUtil
import kotlin.math.roundToInt

class HeaderDrawer(private val seatView: MXSeatView) {
    private val backgroundPaint = Paint()
    private val headTextPaint = Paint()

    private val marginBetweenImgAndText = seatView.context.dp2px(5)
    private val marginBetweenTextAndImg = seatView.context.dp2px(12)
    private val headHeight = seatView.context.dp2px(30).roundToInt()
    private val imgMatrix = Matrix()

    init {
        headTextPaint.style = Paint.Style.FILL
        headTextPaint.textSize = 29f
        headTextPaint.color = Color.BLACK
        headTextPaint.isAntiAlias = true

        backgroundPaint.color = Color.WHITE
    }

    fun drawHeader(): Bitmap {
        val width = seatView.width
        val height = headHeight
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawRect(RectF(0f, 0f, width.toFloat(), height.toFloat()), backgroundPaint)
        val textWidth = TextDrawerUtil.getTextWidth(headTextPaint, "已选")
        val seatBitmap = seatView.seatBitmap
        val selectBitmap = seatView.seatCheckedBitmap
        val soldBitmap = seatView.seatSoldBitmap

        val bitmapSize = headHeight * 0.6f //图片绘制宽高固定为整个高度的60%
        // 计算出第一个图开始的绘制位置，整个居中
        var startX = seatView.width / 2f - (bitmapSize * 3 + textWidth * 3 + marginBetweenImgAndText * 3 + marginBetweenTextAndImg * 2) / 2f

        //绘制座位标记 未选、已选、已售
        kotlin.run {
            val text = "未选"
            val baseLine = TextDrawerUtil.getBaseLine(headTextPaint, 0, height)
            val scale = BitmapCalculatorUtil.getScaleBySize(seatBitmap, bitmapSize, bitmapSize)
            imgMatrix.setScale(scale.first, scale.second)
            imgMatrix.postTranslate(startX, ((height - bitmapSize) / 2f))
            canvas.drawBitmap(seatBitmap, imgMatrix, null)
            startX += bitmapSize + marginBetweenImgAndText
            canvas.drawText(text, startX, baseLine, headTextPaint)
            startX += textWidth + marginBetweenTextAndImg
        }
        kotlin.run {
            val text = "已选"
            val baseLine = TextDrawerUtil.getBaseLine(headTextPaint, 0, height)
            val scale = BitmapCalculatorUtil.getScaleBySize(selectBitmap, bitmapSize, bitmapSize)
            imgMatrix.setScale(scale.first, scale.second)
            imgMatrix.postTranslate(startX, ((height - bitmapSize) / 2f))
            canvas.drawBitmap(selectBitmap, imgMatrix, null)
            startX += bitmapSize + marginBetweenImgAndText
            canvas.drawText(text, startX, baseLine, headTextPaint)
            startX += textWidth + marginBetweenTextAndImg
        }
        kotlin.run {
            val text = "已售"
            val baseLine = TextDrawerUtil.getBaseLine(headTextPaint, 0, height)
            val scale = BitmapCalculatorUtil.getScaleBySize(soldBitmap, bitmapSize, bitmapSize)
            imgMatrix.setScale(scale.first, scale.second)
            imgMatrix.postTranslate(startX, ((height - bitmapSize) / 2f))
            canvas.drawBitmap(soldBitmap, imgMatrix, null)
            startX += bitmapSize + marginBetweenImgAndText
            canvas.drawText(text, startX, baseLine, headTextPaint)
        }

        return bitmap
    }
}