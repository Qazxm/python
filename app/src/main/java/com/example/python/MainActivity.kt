package com.example.python

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.chaquo.python.Python
import com.chaquo.python.PyObject
import com.chaquo.python.android.AndroidPlatform

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Chaquopy Python 환경 초기화
        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }

        // Python 스크립트 실행
        val python = Python.getInstance()
        val py = python.getModule("add") // add.py 모듈을 가져옴

        // add 함수 호출
        val result = try {
            py.callAttr("add", 5, 4) // Python의 add 함수 호출
        } catch (e: Exception) {
            e.printStackTrace()
            "Error"
        }

        // 결과를 TextView에 표시
        val textView = findViewById<TextView>(R.id.resultTextView)
        textView.text = "Result: ${result.toString()}"
    }
}
