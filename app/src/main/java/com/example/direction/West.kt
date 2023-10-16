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
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import java.lang.Math.abs
import kotlin.math.sqrt

@Suppress("DEPRECATION")
class West : AppCompatActivity(), GestureDetector.OnGestureListener, SensorEventListener {
    private lateinit var gestureDetector: GestureDetector
    private lateinit var sensorManager: SensorManager
    private lateinit var acclerometer: Sensor
    private lateinit var context: Context
    private var acceleration = 0f
    private var lastAcceleration = 0f
    private var currentAcceleration = 0f
    private val threshold: Int = 200

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_west)
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

    fun swipeLeft() {
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
        if (e1 != null && e1.x > e2.x && (abs(e1.x - e2.x) > threshold)) {
            // Right to left | Swipe Right
            swipeLeft()
        } else if (e1 != null && e1.x < e2.x && (abs(e1.x - e2.x) > threshold)) {
            // Left to Right | Swipe Left
        } else if (e1 != null && e1.y > e2.y) {
            // Bottom to Top | Swipe Up
        } else {
            // Top to Bottom | Swipe Down
        }

        return true
    }

    override fun onResume() {
        super.onResume()
        // Register the sensor listener
        sensorManager.registerListener(this, acclerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    // Override onPause method
    override fun onPause() {
        super.onPause()
        // Unregister the sensor listener
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        // Get the sensor type
        val x = event?.values?.get(0) ?: 0f
        val y = event?.values?.get(1) ?: 0f
        val z = event?.values?.get(2) ?: 0f

        lastAcceleration = currentAcceleration

        currentAcceleration = sqrt((x * x + y * y + z * z).toDouble()).toFloat()
        val delta: Float = currentAcceleration - lastAcceleration
        acceleration = acceleration * 0.9f + delta

        if (acceleration > 14) {
            Toast.makeText(applicationContext, "Shake event detected", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        //Log.e("HmM", "Changedssdfd")
    }
}
