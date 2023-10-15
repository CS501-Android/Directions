package com.example.direction

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.abs


@Suppress("DEPRECATION")
class Home : AppCompatActivity(), GestureDetector.OnGestureListener {
    private lateinit var gestureDetector: GestureDetector
    private lateinit var sensorManager: SensorManager
    private lateinit var acclerometer: Sensor
    private lateinit var context: Context
    private val threshold: Int = 200

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        val intent = Intent(this, West::class.java)
        startActivity(intent)
    }

    fun swipeRight() {
        val intent = Intent(this, East::class.java)
        startActivity(intent)
    }

    fun swipeTop() {
        val intent = Intent(this, South::class.java)
        startActivity(intent)
    }

    fun swipeBottom() {
        val intent = Intent(this, North::class.java)
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
            swipeRight()
        } else if (e1 != null && e1.x < e2.x && (abs(e1.x - e2.x) > threshold)) {
            // Left to Right | Swipe Left
            swipeLeft()
        } else if (e1 != null && e1.y > e2.y) {
            // Bottom to Top | Swipe Up
            swipeTop()
        } else {
            // Top to Bottom | Swipe Down
            swipeBottom()
        }

        return true
    }

    private val sensorListener: SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            /*
             @Rish (https://www.geeksforgeeks.org/how-to-detect-shake-event-in-android/)
             This link is to implement Acceleration and what not
            */
            // Detect Acceleration / Shaking -> then utilize
            /*
                final Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shake);
                btn_done = (Button) findViewById(R.id.btn_act_confirm_done);
                btn_done.startAnimation(animShake);
             */
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            TODO("Not yet implemented")
        }
    }
}