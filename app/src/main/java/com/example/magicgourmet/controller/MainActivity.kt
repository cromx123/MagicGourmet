package com.example.magicgourmet.controller

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.magicgourmet.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_principal)

        val textView = findViewById<TextView>(R.id.myTextView)
        textView.text = "Texto cambiado desde MainActivity"
    }
}
