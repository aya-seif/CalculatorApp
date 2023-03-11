package com.example.programmercalculator.utils

import com.example.programmercalculator.utils.Constants.EMPTY_STRING
import com.example.programmercalculator.utils.Constants.INDEX_ONE
import com.example.programmercalculator.utils.Constants.INDEX_TWO
import com.example.programmercalculator.utils.Constants.INDEX_ZERO


object TypeConverter {

    fun convertBinaryToSelectedType(selectedItem: Int, input: String) =
        when (selectedItem) {
            INDEX_ZERO -> convertFromBinaryToHexaDecimel(input)
            INDEX_ONE -> convertFromBinaryToDecimal(input)
            INDEX_TWO -> convertFromBinaryToOctal(input)
            else -> input
        }

    fun convertFromBinaryToDecimal(input: String) = Integer.parseInt(input, 2)
    fun convertFromBinaryToOctal(binary: String): String {
        var octal = ""
        var i = binary.length - 1
        var digit = 0
        var count = 0
        while (i >= 0) {
            digit += (binary[i] - '0') * Math.pow(2.0, count.toDouble()).toInt()
            count++
            if (count == 3) {
                octal = digit.toString() + octal
                digit = 0
                count = 0
            }
            i--
        }
        if (count > 0) {
            octal = digit.toString() + octal
        }
        return octal
    }

    fun convertFromBinaryToHexaDecimel(binary: String) =
        Integer.toHexString(binary.toLong(2).toInt()).toUpperCase()

    fun isHexaDecimal(input: String): Boolean {
        val regex = Regex("[0-9a-fA-F]+")
        return regex.matches(input)
    }

    fun isDecimal(input: String) = input.toDoubleOrNull() != null
    fun isOctal(input: String): Boolean {
        val regex = Regex("^0[1-7][0-7]*$")
        return regex.matches(input)
    }


    fun isBinary(input: String) =
        input.toIntOrNull(2) != null

    fun convertTypesToBinary(index: Int, input: String) =
        when (index) {
            INDEX_ZERO -> convertHexaDecimelToBinary(input)
            INDEX_ONE -> convertDecimelToBinary(input)
            INDEX_TWO -> convertOctalToBinary(input)
            else -> EMPTY_STRING
        }

    fun convertOctalToBinary(input: String) = Integer.toBinaryString(input.toInt(8))
    fun convertDecimelToBinary(input: String) = input.toInt().toString(2)
    fun convertHexaDecimelToBinary(input: String) = input.toInt(16).toString(2)
}