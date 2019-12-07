package com.example.prac2

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class Global:Application() {
    companion object {
        var sleep:sleep_var = sleep_var(0.0,0.0,0,0)
        @RequiresApi(Build.VERSION_CODES.O)
        var sleepstart: Long = System.currentTimeMillis()
        var sleepend:Long = System.currentTimeMillis()
    }
}