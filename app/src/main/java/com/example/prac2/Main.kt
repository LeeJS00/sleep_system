package com.example.prac2


import android.content.Intent
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main2.*
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

class Main : AppCompatActivity() {
    val IP_ADDRESS = "141.223.202.161"
    var sleep:ArrayList<sleep_var> = arrayListOf()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val dateAndtime: LocalDateTime = LocalDateTime.now()
        val dt:String = dateAndtime.toString()
        Global.sleepstart = System.currentTimeMillis()
        Toast.makeText(applicationContext,"안녕하세요! 버튼을 눌러 데이터를 확인하세요.", Toast.LENGTH_SHORT).show()
        button_load.setOnClickListener{
            val s:String = load_cache()
            Toast.makeText(applicationContext,"Loading Past Data", Toast.LENGTH_SHORT).show()
            val result = s.split("\\s".toRegex())
            /*
            txt_tp.text = result[0]+"ºC"
            //txt_ps.text = result[1]+"%"
            txt_hum.text = result[1]+"%"
            txt_light.text = result[2]+"%"
            txt_sound.text = result[3]+"/min"
            //txt_humtp.text = result[5]+"ºC"
            //txt_heart.text = result[6]+"bpm"
            //txt_muscle.text = result[7]+"%"
            //text_score.text = "= "+result[5]+result[6]+result[7]*/
            txt_tp.text = "14℃"
            txt_hum.text = "40.0%"
            txt_light.text = "67%"
            txt_sound.text = "24/min"
            txt_humtp.text = "34.4℃"
            txt_heart.text = "64bpm"
            txt_muscle.text = "88%"
            text_score.text = "= 74"
            txt_ps.text = "0"
            Toast.makeText(applicationContext,"지난 데이터 받아오기...", Toast.LENGTH_SHORT).show()
        }
        button_save.setOnClickListener {
            Toast.makeText(applicationContext,"데이터 저장중...", Toast.LENGTH_SHORT)
            //save_cache(now.show())
        }
        img_ps.setOnClickListener {

            Toast.makeText(applicationContext,"수면중 뒤척임 확인", Toast.LENGTH_SHORT).show()
            var intent1 = Intent(baseContext,posture::class.java)
            startActivity(intent1)
        }
        button_get.setOnClickListener {
            var s :ArrayList<sleep_var> = arrayListOf()
            val task:GetData = GetData(this,sleep)
            task.execute("http://"+IP_ADDRESS+"/get_data.php","")
            Toast.makeText(applicationContext,"서버로부터 데이터를 받아옵니다.", Toast.LENGTH_SHORT).show()
        }
        button_fin.setOnClickListener {
            Toast.makeText(applicationContext,"추천 시스템!", Toast.LENGTH_SHORT).show()
            Global.sleepend = System.currentTimeMillis()
            var intent1 = Intent(baseContext,ShowResult::class.java)
            startActivity(intent1)

        }
    }

    private fun save_cache(data :String){
        try {
            val file = File(cacheDir, "myCache")
            val outputStream = FileOutputStream(file)
            outputStream.write(data.toByteArray())
            outputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun load_cache() :String {
        var text :String= ""
        try {
            val file = File(cacheDir, "myCache")
            if (!file.exists()) file.createNewFile()
            val inputStream = FileInputStream(file)
            val s = Scanner(inputStream)

            while (s.hasNext()) {
                text += s.nextLine()
            }
            inputStream.close()

        } catch (e: IOException) {
            e.printStackTrace()
            text ="error"
        }
        return text
    }

    override fun onDestroy() {
        super.onDestroy()
    }
    class GetData:AsyncTask<String,Void,String> {
        private lateinit var activity:Main
        constructor() {}
        var sleep:ArrayList<sleep_var> = arrayListOf()
        constructor(acti: Main,sleep1:ArrayList<sleep_var>) {
            activity=acti
            sleep = sleep1
        }
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: String?): String? {
            val serverURL = params[0]
            try {
                val url = URL(serverURL)
                val httpURLConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
                httpURLConnection.setReadTimeout(5000)
                httpURLConnection.setConnectTimeout(5000)
                httpURLConnection.setRequestMethod("POST")
                httpURLConnection.setDoInput(true)
                httpURLConnection.connect()
                val outputStream: OutputStream = httpURLConnection.outputStream
                outputStream.flush()
                outputStream.close()
                Log.i("showResult", "do in bg")
                val responseStatusCode = httpURLConnection.responseCode
                val inputStream: InputStream
                inputStream = if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    httpURLConnection.inputStream
                } else {
                    httpURLConnection.errorStream
                }
                val inputStreamReader = InputStreamReader(inputStream, "UTF-8")
                val bufferedReader = BufferedReader(inputStreamReader)

                val sb = StringBuilder()
                var line: String = ""
                line = bufferedReader.readLine()
                while (line != "}" ) {
                    line = bufferedReader.readLine()
                    sb.append(line)
                }
                bufferedReader.close()
                return sb.toString().trim()
            }catch (e:Exception) {
                return "no"
            }
        }

        override fun onPostExecute(result: String){
            super.onPostExecute(result)
            if (result == null) {
            } else {
                Log.i("showResult","go showresult")
                showResult(result)
                Log.i("showResult",sleep.size.toString())
                activity.txt_tp.text= Global.sleep.get_tp()+" C"
                activity.txt_hum.text= Global.sleep.get_hum()+"%"
                activity.txt_light.text= Global.sleep.get_light()+"%"
                activity.txt_sound.text= Global.sleep.get_sound()+"/min"
                activity.txt_ps.text = Global.sleep.avg_pos()
            }
        }
        fun showResult(mJsonString:String){
            val COLL_TP = "temperature"
            val COLL_POS1 = "pos1"
            val COLL_POS2 = "pos2"
            val COLL_POS3 = "pos3"
            val COLL_POS4 = "pos4"
            val COLL_POS5 = "pos5"
            val COLL_POS6 = "pos6"
            val COLL_POS7 = "pos7"
            val COLL_POS8 = "pos8"
            val COLL_POS9 = "pos9"
            val COLL_POS10 = "pos10"
            val COLL_POS11 = "pos11"
            val COLL_POS12 = "pos12"
            val TIME = "sleep_time"
            val COLL_HUM = "humadity"
            val COLL_LIGHT = "light"
            val COLL_SOUND = "sound"
            val COLL_HUMTP = "humantp"
            val COLL_HB = "heartbeat"
            val COLL_MUSCLE = "muscle"
            try {
                val jsonObject = JSONObject("{"+mJsonString)
                val jsonArray = jsonObject.getJSONArray("webnautes")
                Log.i("showResult",jsonArray.length().toString())
                for (i in 0 until jsonArray.length()) {
                    val item = jsonArray.getJSONObject(i)
                    val tp = item.getString(COLL_TP)
                    val hum = item.getString(COLL_HUM)
                    val light = item.getString(COLL_LIGHT)
                    val sound = item.getString(COLL_SOUND)
                    val pos1 = item.getString(COLL_POS1)
                    val pos2 = item.getString(COLL_POS2)
                    val pos3 = item.getString(COLL_POS3)
                    val pos4 = item.getString(COLL_POS4)
                    val pos5 = item.getString(COLL_POS5)
                    val pos6 = item.getString(COLL_POS6)
                    val pos7 = item.getString(COLL_POS7)
                    val pos8 = item.getString(COLL_POS8)
                    val pos9 = item.getString(COLL_POS9)
                    val pos10 = item.getString(COLL_POS10)
                    val pos11 = item.getString(COLL_POS11)
                    val pos12 = item.getString(COLL_POS12)
                    var personalData = sleep_var(tp.toDouble(),hum.toDouble(),light.toInt(),sound.toInt())
                    personalData.pos[0] = pos1.toInt()
                    personalData.pos[1] = pos2.toInt()
                    personalData.pos[2] = pos3.toInt()
                    personalData.pos[3] = pos4.toInt()
                    personalData.pos[4] = pos5.toInt()
                    personalData.pos[5] = pos6.toInt()
                    personalData.pos[6] = pos7.toInt()
                    personalData.pos[7] = pos8.toInt()
                    personalData.pos[8] = pos9.toInt()
                    personalData.pos[9] = pos10.toInt()
                    personalData.pos[10] = pos11.toInt()
                    personalData.pos[11] = pos12.toInt()
                    sleep.add(personalData)
                    Log.i("showResult",personalData.show())
                }
                val last=  sleep.size-1
                Global.sleep = sleep[last]
            } catch (e: JSONException) {
                Log.i("showResult", e.toString())
            }
        }
    }
}

