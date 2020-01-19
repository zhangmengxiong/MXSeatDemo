package com.mx.mxseat.gesture

import android.view.MotionEvent
import android.view.ScaleGestureDetector
import com.mx.mxseat.MXSeatView
import com.mx.mxseat.SeatConfig
import kotlin.math.max
import kotlin.math.min

class ScaleGesture(private val seatView: MXSeatView, private val config: SeatConfig) {
    private val scaleGestureDetector: ScaleGestureDetector by lazy {
        ScaleGestureDetector(seatView.context, listener)
    }
    var isInScale = false
    var scaleStart = 0f

    var minScale = 1f
    var maxScale = 1f
    private val listener = object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
            isInScale = true
            scaleStart = config.getScale()
            minScale = config.scaleMin
            maxScale = config.scaleMax
            return true
        }

        override fun onScaleEnd(detector: ScaleGestureDetector?) {
            isInScale = false
        }

        override fun onScale(detector: ScaleGestureDetector?): Boolean {
            val s = detector?.scaleFactor ?: return false
            var scale = scaleStart + s - 1
            scale = max(min(scale, maxScale), minScale)
            config.seatMatrix.setScale(scale, scale)
            seatView.invalidate()
            return false
        }
    }

    fun onTouchEvent(event: MotionEvent) {
        scaleGestureDetector.onTouchEvent(event)
    }
}