package com.example.prac2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_posture.*

class posture : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posture)
        val pos_img = arrayOf(pos_1, pos_2,pos_3,pos_4,pos_5,pos_6,pos_7,pos_8,pos_9,pos_10,pos_11,pos_12)

        for(i in 0..11) {
            if(Global.sleep.pos[i]<1) {
                pos_img[i].setImageResource(R.drawable.pos_gray)
            }
            else if(Global.sleep.pos[i]>1 && Global.sleep.pos[i]<3) {
                pos_img[i].setImageResource(R.drawable.pos_green)
            }
            else if(Global.sleep.pos[i]>=3 && Global.sleep.pos[i]<5) {
                pos_img[i].setImageResource(R.drawable.pos_yellow)
            }
            else if(Global.sleep.pos[i]>=5 && Global.sleep.pos[i]<7) {
                pos_img[i].setImageResource(R.drawable.pos_orange)
            }
            else {
                pos_img[i].setImageResource(R.drawable.pos_red)
            }
        }
    }
}
