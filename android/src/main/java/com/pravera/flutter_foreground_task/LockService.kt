package com.pravera.flutter_foreground_task

import android.app.KeyguardManager
import android.app.Service
import android.content.Intent
import android.content.Intent.ACTION_SCREEN_OFF
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager


class LockService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent == null || intent.action == null) return START_NOT_STICKY

        val action = intent.action

        if (action == ACTION_SCREEN_OFF) {
            val k1: KeyguardManager.KeyguardLock
            val km: KeyguardManager = getSystemService(KEYGUARD_SERVICE) as KeyguardManager
            k1 = km.newKeyguardLock("IN")
            k1.disableKeyguard()
        }

        val mWindowManager: WindowManager = getSystemService(WINDOW_SERVICE) as WindowManager

        val mView: View = LayoutInflater.from(this).inflate(R.layout.activity_alert, null)

        val mLayoutParams: WindowManager.LayoutParams = WindowManager.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT, 0, 0,
            WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                    or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                    or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON /* | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON */,
            PixelFormat.RGBA_8888
        )

        mWindowManager.addView(mView, mLayoutParams)
        return START_NOT_STICKY
    }

    companion object {
        fun alertDialog() {

        }
    }



}