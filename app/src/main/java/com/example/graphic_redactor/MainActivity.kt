package com.example.graphic_redactor


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat


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
            R.id.green -> {
                canvasView?.changeColor(ResourcesCompat.getColor(resources, R.color.green, null))
                return true
            }
            R.id.red -> {
                canvasView?.changeColor(ResourcesCompat.getColor(resources, R.color.red, null))
                return true
            }
            R.id.blue -> {
                canvasView?.changeColor(ResourcesCompat.getColor(resources, R.color.blue, null))
                return true
            }
            R.id.black -> {
                canvasView?.changeColor(ResourcesCompat.getColor(resources, R.color.black, null))
                return true
            }
            R.id.eraser -> {
                canvasView?.changeColor(ResourcesCompat.getColor(resources, R.color.white, null))
                return true
            }
            R.id.thin -> {
                canvasView?.changeWidth(6f)
                return true
            }
            R.id.normal -> {
                canvasView?.changeWidth(12f)
                return true
            }
            R.id.wide -> {
                canvasView?.changeWidth(24f)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}

