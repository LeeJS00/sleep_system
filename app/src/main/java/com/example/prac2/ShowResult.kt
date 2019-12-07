package com.example.prac2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_show_result.*

class ShowResult : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_result)
        val st = Global.sleepend-Global.sleepstart
        var _sec = st/1000
        val min = _sec/60
        val sec =_sec%60
        result_time.text = " Total sleep time : 00:"+min+":"+sec+" "
        result_tp.text = " AVG temperature : "+Global.sleep.get_tp()+"℃ "
        result_hum.text = " AVG humadity : "+Global.sleep.get_hum()+"% "
        result_light.text = " AVG light : "+Global.sleep.get_light()+"% "
        result_sound.text = " AVG sound : "+Global.sleep.get_sound()+"% "
        result_pos.text = " Posture score : "+Global.sleep.avg_pos()+" "

        if(sec < 21600) {
            text_time.setTextColor(Color.RED)
            text_time.text =" Not enough sleep! "
        }
        if(Global.sleep.tp <24.0) {
            text_tp.setTextColor(Color.RED)
            text_tp.text =" Too cold! "
        }
        if(Global.sleep.hum <73.0) {
            text_hum.setTextColor(Color.RED)
            text_hum.text =" Moisturize the air! "
        }
        if(Global.sleep.sound >30) {
            text_sound.setTextColor(Color.RED)
            text_sound.text =" Too noisy! "
        }
        if(Global.sleep.avg_pos().toDouble() > 10.0) {
            text_pos.setTextColor(Color.RED)
            text_pos.text =" Too many movements! "
        }

    }

}
