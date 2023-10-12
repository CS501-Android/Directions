package com.example.direction

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.core.view.GestureDetectorCompat

class Home : AppCompatActivity() {
    private lateinit var mDetector: GestureDetectorCompat
    private lateinit var sensorManager: SensorManager
    private lateinit var acclerometer: Sensor

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Gesture detection
        mDetector = GestureDetectorCompat(this, MyGestureListener())
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        acclerometer = sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!!
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        mDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    private class MyGestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onDown(event: MotionEvent): Boolean {
            Log.d("a", "onDown: $event")
            return true
        }

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            // @Rish | Change the threshold here (otherwise small movement cause this to trigger)
            if (e1 != null && e1.x > e2.x) {
                // Right to left
                Log.d("a", "right to left")
            } else if (e1 != null && e1.x < e2.x) {
                // Right to left
                Log.d("a", "left to right")
            } else if (e1 != null && e1.y > e2.y) {
                Log.d("a", "Bottom to top")
            } else {
                Log.d("a", "Bottom to top")
            }
            return true
        }
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