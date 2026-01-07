package com.example.jetpackmasterclass

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {

    var display by mutableStateOf("")

    fun onInput(num:String){
        display+=num
    }
    fun onClear(){
        display = ""
    }

    fun onEquals(){
        display = CalculatorEngine.calculate(display).toString()

    }

}