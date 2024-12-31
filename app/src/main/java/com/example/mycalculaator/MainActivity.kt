package com.example.mycalculaator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var display: TextView
    private var currentNumber = ""
    private var operator = ""
    private var firstNumber = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.textView1)

        val buttons = listOf(
            R.id.button, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8,
            R.id.button9, R.id.button10, R.id.button11, R.id.button12,
            R.id.button13, R.id.button14, R.id.button15, R.id.button16,
            R.id.button17, R.id.button18, R.id.button19, R.id.button20
        )

        for (id in buttons) {
            findViewById<Button>(id).setOnClickListener { onButtonClick(it) }
        }
    }

    private fun onButtonClick(view: View) {
        when (view.id) {
            R.id.button -> clearEntry()
            R.id.button2 -> clear()
            R.id.button3 -> backspace()
            R.id.button4 -> setOperator("/")
            R.id.button8 -> setOperator("*")
            R.id.button12 -> setOperator("-")
            R.id.button16 -> setOperator("+")
            R.id.button20 -> calculate()
            else -> appendNumber((view as Button).text.toString())
        }
    }

    private fun appendNumber(number: String) {
        currentNumber += number
        display.text = currentNumber
    }

    private fun setOperator(op: String) {
        if (currentNumber.isNotEmpty()) {
            firstNumber = currentNumber
            currentNumber = ""
            display.text = op
            operator = op
        }
    }

    private fun calculate() {
        if (firstNumber.isNotEmpty() && currentNumber.isNotEmpty()) {
            val result = when (operator) {
                "+" -> firstNumber.toDouble() + currentNumber.toDouble()
                "-" -> firstNumber.toDouble() - currentNumber.toDouble()
                "*" -> firstNumber.toDouble() * currentNumber.toDouble()
                "/" -> firstNumber.toDouble() / currentNumber.toDouble()
                else -> 0.0
            }
            display.text = result.toString()
            currentNumber = result.toString()
            firstNumber = ""
            operator = ""
        }
    }

    private fun clearEntry() {
        currentNumber = ""
        display.text = ""
    }

    private fun clear() {
        currentNumber = ""
        firstNumber = ""
        operator = ""
        display.text = ""
    }

    private fun backspace() {
        if (currentNumber.isNotEmpty()) {
            currentNumber = currentNumber.dropLast(1)
            display.text = currentNumber
        }
    }
}