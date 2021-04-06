package com.example.graphic_redactor


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private var canvasView: CanvasView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        canvasView = CanvasView(this)
        setContentView(canvasView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //val canvasView = CanvasView(this)
        when (item.itemId) {
            R.id.clear -> {
                canvasView?.clear()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}

