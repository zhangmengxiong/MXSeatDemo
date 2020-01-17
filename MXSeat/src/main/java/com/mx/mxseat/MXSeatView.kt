package com.mx.mxseat

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.mx.mxseat.drawer.HeaderDrawer

class MXSeatView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    var seatBitmap: Bitmap // 默认座位图
    var seatSoldBitmap: Bitmap // 已售图
    var seatCheckedBitmap: Bitmap // 已选图

    private var spaceX = context.dp2px(5)
    private var spaceY = context.dp2px(5)

    private var rowSize = 0
    private var columnSize = 0

    private val headerDrawer = HeaderDrawer(this)

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MXSeatView)
        val seatCheckedResID =
                typedArray.getResourceId(R.styleable.MXSeatView_seat_checked, R.drawable.seat_gray)
        val seatSoldResID =
                typedArray.getResourceId(R.styleable.MXSeatView_overview_sold, R.drawable.seat_sold)
        val seatAvailableResID =
                typedArray.getResourceId(R.styleable.MXSeatView_seat_available, R.drawable.seat_green)
        typedArray.recycle()

        seatBitmap = BitmapFactory.decodeResource(resources, seatCheckedResID)
        seatSoldBitmap = BitmapFactory.decodeResource(resources, seatSoldResID)
        seatCheckedBitmap = BitmapFactory.decodeResource(resources, seatAvailableResID)
    }

    fun setSeatSize(row: Int, col: Int) {
        if (row <= 0 || col <= 0) throw IllegalArgumentException("座位设置错误")
        rowSize = row
        columnSize = col

        initView()
        postInvalidate()
    }

    /**
     * Seat图初始化
     */
    private fun initView() {
    }

    override fun onDraw(canvas: Canvas?) {
        if (rowSize <= 0 || columnSize <= 0 || canvas == null) return
        val headBitmap = headerDrawer.drawHeader()
        canvas.drawBitmap(headBitmap, 0f, 0f, null)
    }

}