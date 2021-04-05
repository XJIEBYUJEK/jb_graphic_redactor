package com.example.graphic_redactor

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity





class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val canvasView = CanvasView(this)
        setContentView(canvasView)
    }
}
/*
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val canvasView = CanvasView(this)

        val clearButton =  findViewById<Button>(R.id.clear)

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction().add(
                    R.id.frameLayout,
                    CanvasFragment()
            ).commit()
        }
        clearButton.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(
                    R.id.frameLayout,
                    UpgradesFragment()
            ).commit()
        }

    }
}*/
