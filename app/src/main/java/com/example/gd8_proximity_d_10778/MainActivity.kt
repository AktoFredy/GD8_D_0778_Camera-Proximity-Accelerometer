package com.example.gd8_proximity_d_10778

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var sensorStatusTV: TextView
    lateinit var proximitySensor: Sensor
    lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // on below line we are initializing our all variables.
        sensorStatusTV = findViewById(R.id.idTVSensorStatus)

        // on below line we are initializing our sensor manager
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        // on below line we are initializing our proximity sensor variable
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

        // on below line we are checking if the proximity sensor is null
        if (proximitySensor == null) {
            // on below line we are displaying a toast if no sensor is available
            Toast.makeText(this, "No proximity sensor found in device..", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            // on below line we are registering
            // our sensor with sensor manager
            sensorManager.registerListener(
                proximitySensorEventListener,
                proximitySensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    var proximitySensorEventListener: SensorEventListener? = object : SensorEventListener{
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

        }

        override fun onSensorChanged(event: SensorEvent) {
            if (event.sensor.type == Sensor.TYPE_PROXIMITY) {
                if (event.values[0] == 0f) {
                    // here we are setting our status to our textview..
                    // if sensor event return 0 then object is closed
                    // to sensor else object is away from sensor.
                    sensorStatusTV.text = "<<< Near >>>"
                } else {
                    // on below line we are setting text for text view
                    // as object is away from sensor.
                    sensorStatusTV.text = "<<<< Away >>>>"
                }
            }
        }
    }
}