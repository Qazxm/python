package com.example.python

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("MyApp", "onCreate called")

        // Chaquopy Python 환경 초기화
        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }

        // Python 인스턴스와 모듈 가져오기
        val python = Python.getInstance()
        val py = python.getModule("add") // add.py 모듈을 가져옴

        // add 함수 호출 및 결과 처리
        val addResult = try {
            py.callAttr("add", 3, 4).toString() // Python의 add 함수 호출
        } catch (e: Exception) {
            Log.e("MyApp", "Python execution error: ${e.message}", e)
            "Error"
        }

        // main 함수 호출 및 결과 처리
        val mainResult = try {
            py.callAttr("main").toString() // Python의 main 함수 호출 및 결과 반환
        } catch (e: Exception) {
            Log.e("MyApp", "Python execution error: ${e.message}", e)
            "Error"
        }

        // 결과를 TextView에 표시
        val textView = findViewById<TextView>(R.id.resultTextView)
        textView.text = "Add Result: $addResult\nMain Result: $mainResult"
    }
}
