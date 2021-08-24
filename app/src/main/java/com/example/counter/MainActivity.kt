package com.example.counter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    //declaring text & edit text view using lateinit bcz of scope
    private lateinit var numberText : TextView
    private lateinit var numberInput : EditText
    private lateinit var interval : EditText
    private lateinit var summary : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //referencing all buttons
        val submitButton : Button = findViewById(R.id.main_act_bt_submit)
        val randomButton : Button = findViewById(R.id.main_act_bt_random)
        val incrementButton : Button = findViewById(R.id.main_act_bt_increment)
        val decrementButton : Button = findViewById(R.id.main_act_bt_decrement)

        //setting clicklisteners for each button

        submitButton.setOnClickListener { submitNumber() }
        randomButton.setOnClickListener { generateRandomNumber() }
        incrementButton.setOnClickListener { changeNumber("+") }
        decrementButton.setOnClickListener { changeNumber("-") }

        //referencing lateinit views here
        numberText = findViewById(R.id.main_act_tv_number)
        numberInput = findViewById(R.id.main_act_et_input)
        interval = findViewById(R.id.main_act_et_interval)
        summary = findViewById(R.id.main_act_tv_summary)

    }
    


//    get a number from the editText and display to the screen
    private fun submitNumber(){
        var startingNumber = numberInput.text.toString()

        if (startingNumber == ""){
            startingNumber = (-100..100).random().toString()

        }
        numberText.text = startingNumber
        numberInput.setText("")

        hidekeyboard()
    }
    //generate random number and display to screen
    private fun generateRandomNumber(){
    //random number from -100 to 100
        val randomNumber = (-100..100).random()
        numberText.text = randomNumber.toString()
    }


    //increment or decrement
    private  fun changeNumber(operation : String){
        //get the current number & increment number
        val currentNumber = numberText.text.toString().toInt()
        var incrementValue = interval.text.toString()

        //check if increment value is blank
        if(incrementValue == "")
        {
            incrementValue="1"
        }
        //decrement or increment a value
        if (operation == "+"){
            //determine new number to display
            val newNumber = currentNumber + incrementValue.toInt()
            numberText.text = newNumber.toString()

            //update summary view
            summary.text = "$currentNumber + $incrementValue = $newNumber"
        }
        else{
            //determine new number to display
            val newNumber = currentNumber - incrementValue.toInt()
            numberText.text = newNumber.toString()

            //update summary view
            summary.text = "$currentNumber - $incrementValue = $newNumber"
        }
        hidekeyboard()
    }


    private fun hidekeyboard(){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(numberText.windowToken, 0)
    }
}