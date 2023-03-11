package com.example.programmercalculator.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.text.InputType
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.example.programmercalculator.R
import com.example.programmercalculator.utils.Constants.EMPTY_STRING
import com.example.programmercalculator.utils.Constants.ERORR_TYPE_MESSAGE
import com.example.programmercalculator.utils.Constants.INDEX_ONE
import com.example.programmercalculator.utils.Constants.INDEX_TWO
import com.example.programmercalculator.utils.Constants.INDEX_ZERO
import com.example.programmercalculator.utils.Constants.INTIAL_VALUE
import com.example.programmercalculator.utils.TypeConverter
import com.example.programmercalculator.utils.TypeConverter.convertTypesToBinary
import com.example.programmercalculator.utils.TypeConverter.isBinary
import com.example.programmercalculator.utils.TypeConverter.isDecimal
import com.example.programmercalculator.utils.TypeConverter.isHexaDecimal
import com.example.programmercalculator.utils.TypeConverter.isOctal
import com.skydoves.powerspinner.PowerSpinnerView

class MainActivity : AppCompatActivity() {

    private lateinit var spinnerFrom: PowerSpinnerView
    private lateinit var spinnerTo: PowerSpinnerView
    private lateinit var tvResult: TextView
    private var spinnerIndexFrom = INTIAL_VALUE
    private var spinnerIndexTo = INTIAL_VALUE
    private var selecedType = EMPTY_STRING
    private lateinit var btnSeven: AppCompatButton
    private lateinit var btnEight: AppCompatButton
    private lateinit var btnThree: AppCompatButton
    private lateinit var btnEquel: AppCompatButton
    private lateinit var btnNine: AppCompatButton
    private lateinit var btnFour: AppCompatButton
    private lateinit var btnFive: AppCompatButton
    private lateinit var btnZero: AppCompatButton
    private lateinit var btnOne: AppCompatButton
    private lateinit var inputEditText: EditText
    private lateinit var btnTwo: AppCompatButton
    private lateinit var btnSix: AppCompatButton
    private lateinit var btnAC: AppCompatButton
    private lateinit var btnB: AppCompatButton
    private lateinit var btnA: AppCompatButton
    private lateinit var btnC: AppCompatButton
    private lateinit var btnD: AppCompatButton
    private lateinit var btnE: AppCompatButton
    private lateinit var btnF: AppCompatButton




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        setUpKeyboard()
        disbleSystemKeyboard()

        spinnerFrom.setOnSpinnerItemSelectedListener<String> { oldIndex, oldItem, newIndex, newText ->
            spinnerIndexFrom = newIndex
            selecedType = newText

//            when(newIndex){
//                0 -> btnZero.isEnabled = true
//            }

        }
        spinnerTo.setOnSpinnerItemSelectedListener<String> { oldIndex, oldItem, newIndex, newText ->
            spinnerIndexTo = newIndex
        }
        btnEquel.setOnClickListener {
            val input = inputEditText.text.toString()
            if (checkTypeMatching(input)) {
                val inputType = convertTypesToBinary(spinnerIndexFrom, input)
                val result = TypeConverter.convertBinaryToSelectedType(spinnerIndexTo, inputType)
                tvResult.text = result.toString()

            } else {
                inputEditText.error = "$ERORR_TYPE_MESSAGE $selecedType"

            }
        }
        btnAC.setOnClickListener {
            inputEditText.setText(EMPTY_STRING)
            tvResult.text = EMPTY_STRING
        }
    }

    private fun init() {
        spinnerFrom = findViewById(R.id.spinner_from)
        btnEquel = findViewById(R.id.btn_equal)
        inputEditText = findViewById(R.id.ed_input)
        tvResult = findViewById(R.id.tv_result)
        spinnerTo = findViewById(R.id.spinner_to)
        btnAC = findViewById(R.id.btnAC)
        btnZero = findViewById(R.id.btn_zero)
        btnA = findViewById(R.id.btnA)
        btnB = findViewById(R.id.btnB)
        btnC = findViewById(R.id.btnC)
        btnD = findViewById(R.id.btnD)
        btnE = findViewById(R.id.btnE)
        btnF = findViewById(R.id.btnF)
        btnOne = findViewById(R.id.btn_one)
        btnTwo = findViewById(R.id.btn_two)
        btnThree = findViewById(R.id.btn_three)
        btnFour = findViewById(R.id.btn_four)
        btnFive = findViewById(R.id.btn_five)
        btnSix = findViewById(R.id.btn_six)
        btnSeven = findViewById(R.id.btn_seven)
        btnEight = findViewById(R.id.btn_eight)
        btnNine = findViewById(R.id.btn_nine)
    }

    @SuppressLint("ClickableViewAccessibility")
    fun disbleSystemKeyboard() {
        inputEditText.setOnTouchListener { v, motionEvent ->
            inputEditText.setRawInputType(InputType.TYPE_CLASS_TEXT)
            inputEditText.setTextIsSelectable(true)
            if (motionEvent.actionMasked == MotionEvent.ACTION_UP) {
                v.requestFocus()
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
            }
            true
        }
    }

    private fun setUpKeyboard() {
        val buttons = listOf(
            btnA,
            btnB,
            btnC,
            btnD,
            btnE,
            btnF,
            btnZero,
            btnOne,
            btnTwo,
            btnThree,
            btnFour,
            btnFive,
            btnSix,
            btnSeven,
            btnEight,
            btnNine
        )
        for (button in buttons) {
            button.setOnClickListener {
                val buttonText = button.text.toString()
                inputEditText.append(buttonText)
            }
        }
    }

    private fun checkTypeMatching(input: String) =
        when (spinnerIndexFrom) {
            INDEX_ZERO -> {
                isHexaDecimal(input)
            }
            INDEX_ONE -> {
                isDecimal(input)
            }
            INDEX_TWO -> {
                isOctal(input)
            }
            else -> {
                isBinary(input)
            }
        }


}