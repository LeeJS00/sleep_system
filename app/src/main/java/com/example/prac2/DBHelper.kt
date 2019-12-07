package com.example.prac2

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.openOrCreateDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.net.URL


class DBHelper(context: Context) :SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VER) {
    companion object {
        private val IP_ADDRESS = "141.223.203.253"
        lateinit var url:URL

        private val DATABASE_VER = 1
        private val DATABASE_NAME = "arduino.db"
        private val TABLE_NAME = "sensor"
        private val COLL_TP = "temperature"
        private val COLL_POS1 = "pos1"
        private val COLL_POS2 = "pos2"
        private val COLL_POS3 = "pos3"
        private val COLL_POS4 = "pos4"
        private val COLL_POS5 = "pos5"
        private val COLL_POS6 = "pos6"
        private val COLL_POS7 = "pos7"
        private val COLL_POS8 = "pos8"
        private val COLL_POS9 = "pos9"
        private val COLL_POS10 = "pos10"
        private val COLL_POS11 = "pos11"
        private val COLL_POS12 = "pos12"
        private val TIME = "sleep_time"
        private val COLL_HUM = "humadity"
        private val COLL_LIGHT = "light"
        private val COLL_SOUND = "sound"
        private val COLL_HUMTP = "humantp"
        private val COLL_HB = "heartbeat"
        private val COLL_MUSCLE = "muscle"

    }

    override fun onCreate(db:SQLiteDatabase?) {
    }
    override fun onUpgrade(db:SQLiteDatabase?, oldVersion:Int, newVersion:Int) {
    }
    val sleepVar:List<sleep_var>
        get() {
            val listvar = ArrayList <sleep_var>()
            val selectQueryHandler = "SELECT * FROM $TABLE_NAME"
            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQueryHandler, null)
            if(cursor.moveToFirst()) {
                do {
                    val tp:Double = cursor.getDouble(cursor.getColumnIndex(COLL_TP))
                    val hum:Double = cursor.getDouble(cursor.getColumnIndex(COLL_HUM))
                    val light:Int = cursor.getInt(cursor.getColumnIndex(COLL_LIGHT))
                    val sound:Int = cursor.getInt(cursor.getColumnIndex(COLL_SOUND))
                    val last5 = sleep_var(tp,hum,light,sound)
                    listvar.add(last5)
                }while (cursor.moveToNext())
            }
            db.close()
            return listvar

        }

}