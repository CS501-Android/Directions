package com.example.direction

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import kotlin.math.abs

@Suppress("DEPRECATION")
class East : AppCompatActivity(), GestureDetector.OnGestureListener {
    private lateinit var gestureDetector: GestureDetector
    private lateinit var sensorManager: SensorManager
    private lateinit var acclerometer: Sensor
    private lateinit var context: Context
    private val threshold: Int = 200

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_east)
        context = applicationContext;
        // Gesture detection
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        acclerometer = sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!!
        gestureDetector = GestureDetector(this)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (gestureDetector.onTouchEvent(event)) {
            true
        }
        else {
            super.onTouchEvent(event)
        }
        return super.onTouchEvent(event)
    }

    fun swipeRight() {
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
    }

    override fun onDown(e: MotionEvent): Boolean {
        return false
    }

    override fun onShowPress(e: MotionEvent) {
        return
    }

    override fun onSingleTapUp(e: MotionEvent): Boolean {
        return false
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        return false
    }

    override fun onLongPress(e: MotionEvent) {
        return
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        // @Rish | Change the threshold here (otherwise small movement cause this to trigger)
        if (e1 != null && e1.x < e2.x && (abs(e1.x - e2.x) > threshold)) {
            // Right to left | Swipe Right
            swipeRight()
        }

        return true
    }

    private val sensorListener: SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            /*
             @Rish (https://www.geeksforgeeks.org/how-to-detect-shake-event-in-android/)
             This link is to implement Acceleration and what not
            */
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            TODO("Not yet implemented")
        }
    }
}