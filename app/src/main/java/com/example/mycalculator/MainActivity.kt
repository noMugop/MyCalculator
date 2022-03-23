package com.example.mycalculator

import android.app.assist.AssistContent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import com.example.mycalculator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)

    }

    private var firstValue: String = ""
    private var input: String = ""
    private var sign: String = ""
    private var answer: Double? = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setListeners()
        signBtnClick()
        equalBtnClick()

        if (savedInstanceState != null) {
            firstValue = savedInstanceState.getString("sign") ?: ""
            input = savedInstanceState.getString("firstValue") ?: ""
            sign = savedInstanceState.getString("input") ?: ""
            answer = savedInstanceState.getDouble("answer")
            setInput()
            setSign(sign)
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putString("sign", sign)
        outState.putString("firstValue", firstValue)
        outState.putString("input", input)
        outState.putDouble("answer", answer ?: 0.0)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getString("sign")
        savedInstanceState.getString("firstValue")
        savedInstanceState.getString("input")
        savedInstanceState.getDouble("answer")
    }

    private fun setListeners() {
        binding.btn0.setOnClickListener {

            input += "0"
            setInput()

        }
        binding.btn1.setOnClickListener {

            input += "1"
            setInput()

        }
        binding.btn2.setOnClickListener {

            input += "2"
            setInput()

        }
        binding.btn3.setOnClickListener {

            input += "3"
            setInput()

        }
        binding.btn4.setOnClickListener {

            input += "4"
            setInput()

        }
        binding.btn5.setOnClickListener {

            input += "5"

        }
        binding.btn6.setOnClickListener {

            input += "6"
            setInput()

            setInput()
        }
        binding.btn7.setOnClickListener {

            input += "7"
            setInput()

        }
        binding.btn8.setOnClickListener {

            input += "8"
            setInput()

        }
        binding.btn9.setOnClickListener {

            input += "9"
            setInput()

        }

        binding.btnDot.setOnClickListener {
            input += "."
            setInput()
        }

        binding.btnAC.setOnClickListener {
            firstValue = ""
            input = ""
            sign = ""
            setSign("")
            setInput()
            cleanAnswer()
        }

        binding.btnC.setOnClickListener {
            if (!input.isNullOrBlank()) {
                input = input.dropLast(1)
                setInput()
            } else if (input == "") {
                setInput()
            }
        }

    }

    private fun signBtnClick() {
        binding.btnPlus.setOnClickListener {
            if (answer != 0.0) {
                moreEqualBtnClick()
                setSign("+")
            } else {
                setSign("+")
            }
        }
        binding.btnMinus.setOnClickListener {
            setSign("-")
        }
        binding.btnDiv.setOnClickListener {
            setSign("/")
        }
        binding.btnMulti.setOnClickListener {
            setSign("*")
        }
    }

    private fun moreEqualBtnClick() {
        binding.btnEquals.setOnClickListener {
            if (answer != 0.0) {
                when (sign) {
                    SignEnums.PLUS.id -> {
                        answer = answer?.plus(input.toDouble())
                    }
                    SignEnums.MINUS.id -> {
                        answer = firstValue.toDouble().minus(input.toDouble())
                    }
                    SignEnums.DIVIDE.id -> {
                        answer = firstValue.toDouble() / (input.toDouble())
                    }
                    SignEnums.MULTIPLY.id -> {
                        answer = firstValue.toDouble() * (input.toDouble())
                    }
                    else -> {

                    }
                }
                setAnswer()
                firstValue = answer.toString()
            } else {
                if (firstValue.isNullOrBlank()) {
                    binding.tvAnswer.text = input
                }
            }
        }
    }

    private fun equalBtnClick() {
        binding.btnEquals.setOnClickListener {
            if (!input.isNullOrBlank() && !firstValue.isNullOrBlank()) {
                when (sign) {
                    SignEnums.PLUS.id -> {
                        answer = firstValue.toDouble().plus(input.toDouble())
                    }
                    SignEnums.MINUS.id -> {
                        answer = firstValue.toDouble().minus(input.toDouble())
                    }
                    SignEnums.DIVIDE.id -> {
                        answer = firstValue.toDouble() / (input.toDouble())
                    }
                    SignEnums.MULTIPLY.id -> {
                        answer = firstValue.toDouble() * (input.toDouble())
                    }
                    else-> {

                    }
                }
                setAnswer()
            } else {
                if (firstValue.isNullOrBlank()) {
                    binding.tvAnswer.text = input
                }
            }
        }
    }

    private fun setInput() {
        binding.etInput.setText(input)
    }

    private fun setSign(sign: String) {
        firstValue = input
        input = ""
        this.sign = sign
        setInput()
        binding.tvSign.text = sign
    }

    private fun setAnswer() {
        binding.tvAnswer.text = answer.toString()
        firstValue = ""
        input = ""
        sign = ""
    }

    fun cleanAnswer() {
        answer = 0.0
        binding.tvAnswer.text = ""
    }

}