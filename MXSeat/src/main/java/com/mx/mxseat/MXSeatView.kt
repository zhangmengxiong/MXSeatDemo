package com.mx.mxseat

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.mx.mxseat.drawer.HeaderDrawer
import com.mx.mxseat.drawer.LineNumberDrawer
import com.mx.mxseat.drawer.SeatDrawer

class MXSeatView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val seatConfig: SeatConfig by lazy { SeatConfig(context) }
    private val headerDrawer: HeaderDrawer by lazy { HeaderDrawer(this, seatConfig) }
    private val seatDrawer: SeatDrawer by lazy { SeatDrawer(this, seatConfig) }

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MXSeatView)
        val seatCheckedResID =
                typedArray.getResourceId(R.styleable.MXSeatView_seat_checked, R.drawable.seat_gray)
        val seatSoldResID =
                typedArray.getResourceId(R.styleable.MXSeatView_overview_sold, R.drawable.seat_sold)
        val seatAvailableResID =
                typedArray.getResourceId(R.styleable.MXSeatView_seat_available, R.drawable.seat_green)
        typedArray.recycle()

        seatConfig.seatBitmap = BitmapFactory.decodeResource(resources, seatCheckedResID)
        seatConfig.seatSoldBitmap = BitmapFactory.decodeResource(resources, seatSoldResID)
        seatConfig.seatCheckedBitmap = BitmapFactory.decodeResource(resources, seatAvailableResID)
    }

    fun setSeatSize(row: Int, col: Int) {
        if (row <= 0 || col <= 0) throw IllegalArgumentException("座位设置错误")
        seatConfig.rowSize = row
        seatConfig.columnSize = col

        initView()
        postInvalidate()
    }

    /**
     * Seat图初始化
     */
    private fun initView() {
    }

    private fun getScale() = 1f

    override fun onDraw(canvas: Canvas?) {
        if (seatConfig.rowSize <= 0 || seatConfig.columnSize <= 0 || canvas == null) return
        val headBitmap = headerDrawer.drawHeader()
        canvas.drawBitmap(headBitmap, 0f, 0f, null)
        val headHeight = headBitmap.height
        seatDrawer.drawSeats(canvas, getScale())
    }

}