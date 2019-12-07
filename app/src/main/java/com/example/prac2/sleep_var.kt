package com.example.prac2

import android.app.Application

class sleep_var(var tp:Double, var hum:Double, var light:Int, var sound:Int){
    var pos:Array<Int> = arrayOf(0,0,0,0,0,0,0,0,0,0,0,0)
    fun set_data(tp:Double, hum:Double, light:Int, sound:Int, humantp: Double, heartbeat:Int, muscle:Double) {
        this.tp = tp
        this.hum= hum
        this.light = light
        this.sound = sound

        //this.humantp = humantp
        //this.heartbeat = heartbeat
        //this.muscle =muscle
    }
    fun get_tp() :String {
        return tp.toString()
    }
    fun get_hum():String {
        return hum.toString()
    }
    fun get_light():String {
        return light.toString()
    }
    fun get_sound() :String {
        return sound.toString()
    }
    fun avg_pos(): String {
        var sum =0
        for(i in 0..11) {
            sum+= pos[i]
        }
        return (sum/12.0).toString()
    }
    /*
    fun get_humantp() :Double {
        return humantp
    }
    fun get_heartbeat():Int {
        return heartbeat
    }
    fun get_muscle():Double {
        return muscle
    }
    fun get_cnt():Int {
        return cnt
    }

     */
    fun show():String {
        var tmp :String =""
        tmp += tp
        tmp += " "
        tmp += hum
        tmp += " "
        //tmp += posture
        //tmp += " "
        tmp += light
        tmp += " "
        tmp += sound
        tmp += " "
        //tmp += humantp
        //tmp += " "
        //tmp += heartbeat
        //tmp += " "
        //tmp += muscle
        //tmp += " "
        return tmp
    }
}