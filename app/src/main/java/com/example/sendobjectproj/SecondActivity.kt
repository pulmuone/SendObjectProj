package com.example.sendobjectproj

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.os.ResultReceiver
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        //var t1 = intent.getParcelableExtra<TestClass>("test1")
        var t1 = intent.getParcelableArrayListExtra<TestClass>("test1")

        textView2.text = ""

        for (t in t1) {
            textView2.append("t1.data10 : ${t.data10} \n")
            textView2.append("t1.data10 : ${t.data20} \n")
        }

        button3.setOnClickListener { view ->
            var receiver = intent.getParcelableExtra<ResultReceiver>("RECEIVER")
            var bundle = Bundle()
            bundle.putString("msg", "Success !")
            receiver.send(1, bundle)
        }

        button2.setOnClickListener { view ->
            var t2 = TestClass()
            t2.data10 = 200
            t2.data20 = "문자열2"

            var intent2 = Intent()
            intent2.putExtra("test2", t2)

            setResult(Activity.RESULT_OK, intent2)

            finish()
        }
    }
}

