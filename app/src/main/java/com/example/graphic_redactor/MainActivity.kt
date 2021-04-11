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
        when (item.itemId) {
            R.id.clear -> {
                canvasView?.clear()
                return true
            }
            R.id.green -> {
                item.isChecked = !item.isChecked
                canvasView?.changeColor(ResourcesCompat.getColor(resources, R.color.green, null))
                return true
            }
            R.id.red -> {
                item.isChecked = !item.isChecked
                canvasView?.changeColor(ResourcesCompat.getColor(resources, R.color.red, null))
                return true
            }
            R.id.blue -> {
                item.isChecked = !item.isChecked
                canvasView?.changeColor(ResourcesCompat.getColor(resources, R.color.blue, null))
                return true
            }
            R.id.black -> {
                item.isChecked = !item.isChecked
                canvasView?.changeColor(ResourcesCompat.getColor(resources, R.color.black, null))
                return true
            }
            R.id.eraser -> {
                canvasView?.changeColor(ResourcesCompat.getColor(resources, R.color.white, null))
                return true
            }
            R.id.thin -> {
                item.isChecked = !item.isChecked
                canvasView?.changeWidth(6f)
                return true
            }
            R.id.normal -> {
                item.isChecked = !item.isChecked
                canvasView?.changeWidth(12f)
                return true
            }
            R.id.wide -> {
                item.isChecked = !item.isChecked
                canvasView?.changeWidth(24f)
                return true
            }
            R.id.point -> {
                item.isChecked = !item.isChecked
                canvasView?.changeFigure("point")
                return true
            }
            R.id.rect ->{
                item.isChecked = !item.isChecked
                canvasView?.changeFigure("rect")
                return true
            }
            R.id.oval ->{
                item.isChecked = !item.isChecked
                canvasView?.changeFigure("oval")
                return true
            }
            R.id.line ->{
                item.isChecked = !item.isChecked
                canvasView?.changeFigure("line")
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}

