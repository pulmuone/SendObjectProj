package com.example.sendobjectproj

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.os.ResultReceiver
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val SECOND_ACTIVITY = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { view ->

            //array : 크기 고정
            //arrayList : 크기 가변
            var t1 = ArrayList<TestClass>()
            t1.add(TestClass(100, "문자열1"))
            t1.add(TestClass(200, "문자열2"))
            t1.add(TestClass(300, "문자열3"))

            var intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("test1", t1)
            intent.putExtra("RECEIVER", resultReceiver)
            startActivityForResult(intent, SECOND_ACTIVITY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        textView.text = ""
        if(resultCode == Activity.RESULT_OK) {
            var t2 = data?.getParcelableExtra<TestClass>("test2")

            textView.text = "t2.data10 : ${t2?.data10}\n"
            textView.append("t2.data20 : ${t2?.data20}")
        }
    }

    val handler = Handler()
    val resultReceiver: ResultReceiver = object : ResultReceiver(handler) {
        override fun onReceiveResult(resultCode: Int, resultData: Bundle) {
            if (resultCode == 1) {
                val msg = resultData.getString("msg")
                print(msg)
            }
        }
    }
}
