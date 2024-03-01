package com.example.newcalculator

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

import android.text.SpannableStringBuilder
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.os.HandlerCompat.postDelayed
import org.mariuszgromada.math.mxparser.Expression



class MainActivity : AppCompatActivity() {
    
    private lateinit var previousCal: TextView
    private lateinit var display: EditText

  

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        previousCal = findViewById(R.id.result_tv)
        display = findViewById(R.id.data_tv)
        display.showSoftInputOnFocus = false
       
        val cleanButton = findViewById<Button>(R.id.btn_clear)?: throw IllegalStateException("button_clear not found")

        cleanButton.setOnLongClickListener {
          
            display.setText("")
            previousCal.text = ""
            true
        }
    }

    private fun updateText(strToAdd:String){

        val oldStr = display.text.toString()
        val cursorPs = display.selectionStart
        val leftStr = oldStr.substring(0,cursorPs)
        val rightStr = oldStr.substring(cursorPs)
        display.setText(String.format("%s%s%s",leftStr,strToAdd,rightStr))
        display.setSelection(cursorPs + strToAdd.length)
    }
    
    fun numberBtn(view:View){
       
        updateText((view as Button).text.toString())
    }
    fun openBracketBtn(v: View){
      
        updateText(resources.getString(R.string.parenthesesOpenText))
    }
    fun closeBracketBtn(v: View){
      
        updateText(resources.getString(R.string.parenthesesCloseText))
    }
    fun divideBtn(v: View){
     
        updateText(resources.getString(R.string.divideText))
    }
    fun multiplyBtn(v: View){
        
        updateText(resources.getString(R.string.multiplyText))
    }
    fun subtractBtn(v: View){
     
        updateText(resources.getString(R.string.subtractText))
    }

    fun decimialBtn(v: View){
       
        updateText(resources.getString(R.string.decimalText))
    }
    fun addBtn(v: View){
       
        updateText(resources.getString(R.string.addText))
    }
    fun equalBtn(v: View){
        
        var userExp = display.text.toString()
        
        userExp = userExp.replace(resources.getString(R.string.divideText).toRegex(), "/")
        userExp = userExp.replace(resources.getString(R.string.multiplyText).toRegex(), "*")
        val exp = Expression(userExp)
        val result = exp.calculate().toString()
        previousCal.setText(result)
    }

    fun clearBtn(v: View){
       
        display.setText("")
        previousCal.text = ""
    }
    fun sinBtn(v: View){
       
        updateText("sin(")
    }
    fun cosBtn(v: View){
       
        updateText("cos(")
    }
    fun tanBtn(v: View){
     
        updateText("tan(")
    }
    fun arcsinBtn(v: View){
      
        updateText("arcsin(")
    }
    fun arccosBtn(v: View){
      
        updateText("arccos(")
    }
    fun arctanBtn(v: View){
        
        updateText("arctan(")
    }
    fun logTwoBtn(v: View){
       
        updateText("log2(")
    }
    fun naturalLogBtn(v: View){
      
        updateText("ln(")
    }
    fun absBtn(v: View){
       
        updateText("abs(")
    }
    fun piBtn(v: View){
        
        updateText("pi")
    }
    fun eBtn(v: View){
       
        updateText("e")
    }
    fun sqrtBtn(v: View){
        
        updateText("sqrt(")
    }
    fun xSquareBtn(v: View){
        
        updateText("^(2)")
    }
    fun xPowYBtn(v: View){
        
        updateText("^(")
    }
    fun percentBtn(v: View){
      
        updateText("%")
    }
    fun backSpaceBtn(v: View){
        
        val cursorPos = display.selectionStart
        val textLen = display.text.length
        if (cursorPos != 0 && textLen != 0){
            val selection = display.text as SpannableStringBuilder
            selection.replace(cursorPos -1,cursorPos,"")
            display.text = selection
            display.setSelection(cursorPos -1)
        }

    }

}