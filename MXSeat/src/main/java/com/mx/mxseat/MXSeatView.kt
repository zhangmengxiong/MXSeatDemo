package com.mx.mxseat

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.mx.mxseat.drawer.HeaderDrawer
import com.mx.mxseat.drawer.SeatDrawer
import com.mx.mxseat.gesture.ScaleGesture

class MXSeatView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val seatConfig: SeatConfig by lazy { SeatConfig(context) }
    private val headerDrawer: HeaderDrawer by lazy { HeaderDrawer(this, seatConfig) }
    private val seatDrawer: SeatDrawer by lazy { SeatDrawer(this, seatConfig) }
    private val scaleGesture: ScaleGesture by lazy { ScaleGesture(this, seatConfig) }

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MXSeatView)
        val seatCheckedResID =
            typedArray.getResourceId(R.styleable.MXSeatView_seat_checked, R.drawable.seat_gray)
        val seatSoldResID =
            typedArray.getResourceId(R.styleable.MXSeatView_overview_sold, R.drawable.seat_sold)
        val seatAvailableResID =
            typedArray.getResourceId(R.styleable.MXSeatView_seat_available, R.drawable.seat_green)
        val backgroundColor = typedArray.getColor(R.styleable.MXSeatView_background, Color.WHITE)
        typedArray.recycle()

        seatConfig.seatBitmap = BitmapFactory.decodeResource(resources, seatCheckedResID)
        seatConfig.seatSoldBitmap = BitmapFactory.decodeResource(resources, seatSoldResID)
        seatConfig.seatCheckedBitmap = BitmapFactory.decodeResource(resources, seatAvailableResID)
        seatConfig.backgroundColor = backgroundColor
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


    override fun onDraw(canvas: Canvas?) {
        canvas?.drawColor(seatConfig.backgroundColor)
        if (seatConfig.rowSize <= 0 || seatConfig.columnSize <= 0 || canvas == null) return
        seatDrawer.drawSeats(canvas, seatConfig.getScale())

        val headBitmap = headerDrawer.drawHeader()
        canvas.drawBitmap(headBitmap, 0f, 0f, null)
    }

    private var downX = 0f
    private var downY = 0f
    private var lastX = 0f
    private var lastY = 0f
    private var multiPoint = false

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        super.onTouchEvent(event)

        scaleGesture.onTouchEvent(event)

        val point = event.pointerCount
        if (point > 1) {
            multiPoint = true
        }
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                multiPoint = false
                downX = x
                downY = y
            }
            MotionEvent.ACTION_MOVE -> {
                val dx = x - lastX
                val dy = y - lastY
                if (!multiPoint) {
                    seatConfig.seatMatrix.postTranslate(dx, dy)
                }
                invalidate()
            }
            else -> {
            }
        }
        lastX = x
        lastY = y
        return true
    }
}