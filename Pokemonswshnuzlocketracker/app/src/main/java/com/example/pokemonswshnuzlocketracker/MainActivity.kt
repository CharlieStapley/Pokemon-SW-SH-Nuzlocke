package com.example.pokemonswshnuzlocketracker

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File


class MainActivity : AppCompatActivity() {
    private var arrayOfBooleans = arrayOf("0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0") //17
    private val radioButtonGroup = arrayOf(R.id.radioButton6, R.id.radioButton7, R.id.radioButton8, R.id.radioButton9, R.id.radioButton10, R.id.radioButton11, R.id.radioButton12, R.id.radioButton13, R.id.radioButton14, R.id.radioButton15, R.id.radioButton16, R.id.radioButton17, R.id.radioButton18,R.id.radioButton19, R.id.radioButton20, R.id.radioButton21, R.id.radioButton22 )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val files: Array<String> = this.fileList()
        if(files.isEmpty()){
            val file = File(this.filesDir, "localStorage")
            file.writeText(TextUtils.join(",", arrayOfBooleans))
        }
        else {
            val file = File(this.filesDir, "localStorage")
            arrayOfBooleans = file.readText().split(",").toTypedArray()
            Log.i(TAG, TextUtils.join(",", arrayOfBooleans))
            for (i in arrayOfBooleans.indices) {
                if(arrayOfBooleans[i] == "1"){
                    val radiobutton = findViewById<View>(radioButtonGroup[i]) as RadioButton
                    radiobutton.isChecked = true
                }
            }
        }
        for (i in radioButtonGroup.indices) {
            val radiobutton = findViewById<View>(radioButtonGroup[i]) as RadioButton
            radiobutton.setOnClickListener { radioButtonConverge(i) }
        }
    }
    private fun radioButtonConverge(number: Int) {
        var newNum = arrayOfBooleans[number].toInt() + 1
        if(newNum > 1){
            newNum = 0
        }
        arrayOfBooleans[number] = newNum.toString()
        if(arrayOfBooleans[number] == "0"){
            val radiobutton = findViewById<View>(radioButtonGroup[number]) as RadioButton
            radiobutton.isChecked = false
        }
        val file = File(this.filesDir, "localStorage")
        file.writeText(TextUtils.join(",", arrayOfBooleans))
        Log.i(TAG, TextUtils.join(",", arrayOfBooleans))
    }
}