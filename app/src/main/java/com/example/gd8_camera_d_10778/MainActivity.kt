package com.example.gd8_camera_d_10778

import android.annotation.SuppressLint
import android.hardware.Camera
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private var mCamera: Camera? = null
    private var mCameraView: CameraView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            mCamera = Camera.open()
        }catch (e: IOException){
            Log.d("Error", "Failed to get Camera" + e.message)
        }

        if (mCamera != null){
            mCameraView = CameraView(this, mCamera!!)
            val camera_view = findViewById<View>(R.id.FLCamera) as FrameLayout
            camera_view.addView(mCameraView)
        }
        @SuppressLint("MissingInflatedId", "LocalSuppress")
        val imageClose = findViewById<View>(R.id.imgClose) as ImageButton
        imageClose.setOnClickListener{view: View? -> System.exit(0)}
    }
}