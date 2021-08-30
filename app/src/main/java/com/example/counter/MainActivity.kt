package com.example.counter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.counter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //declaring text & edit text view using lateinit bcz of scope
//    private lateinit var numberText : TextView
//    private lateinit var numberInput : EditText
//    private lateinit var interval : EditText
//    private lateinit var summary : TextView

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(LayoutInflater.from(applicationContext))
        setContentView(binding.root)

        //referencing all buttons
//        val submitButton : Button = findViewById(R.id.main_act_bt_submit)
//        val randomButton : Button = findViewById(R.id.main_act_bt_random)
//        val incrementButton : Button = findViewById(R.id.main_act_bt_increment)
//        val decrementButton : Button = findViewById(R.id.main_act_bt_decrement)

        //setting clicklisteners for each button

        binding.mainActBtSubmit.setOnClickListener { submitNumber() }
        binding.mainActBtRandom.setOnClickListener { generateRandomNumber() }
        binding.mainActBtIncrement.setOnClickListener { changeNumber("+") }
        binding.mainActBtDecrement.setOnClickListener { changeNumber("-") }

        //referencing lateinit views here
//        numberText = findViewById(R.id.main_act_tv_number)
//        numberInput = findViewById(R.id.main_act_et_input)
//        interval = findViewById(R.id.main_act_et_interval)
//        summary = findViewById(R.id.main_act_tv_summary)

        if(savedInstanceState != null){
            binding.mainActTvNumber.text = savedInstanceState.getString("numberText")
            binding.mainActEtInterval.setText(savedInstanceState.getString("interval"))
            binding.mainActTvSummary.text = savedInstanceState.getString("summary")
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("numberText",binding.mainActTvNumber.text.toString())
        outState.putString("interval",binding.mainActEtInterval.text.toString())
        outState.putString("summary",binding.mainActTvSummary.text.toString())
    }

//    get a number from the editText and display to the screen
    private fun submitNumber(){
        var startingNumber = binding.mainActEtInput.text.toString()

        if (startingNumber == ""){
            startingNumber = (-100..100).random().toString()

        }
        binding.mainActTvNumber.text = startingNumber
        binding.mainActEtInput.setText("")

        hidekeyboard()
    }
    //generate random number and display to screen
    private fun generateRandomNumber(){
    //random number from -100 to 100
        val randomNumber = (-100..100).random()
        binding.mainActTvNumber.text = randomNumber.toString()
    }


    //increment or decrement
    private  fun changeNumber(operation : String){
        //get the current number & increment number
        val currentNumber = binding.mainActTvNumber.text.toString().toInt()
        var incrementValue = binding.mainActEtInterval.text.toString()

        //check if increment value is blank
        if(incrementValue == "")
        {
            incrementValue="1"
        }
        //decrement or increment a value
        if (operation == "+"){
            //determine new number to display
            val newNumber = currentNumber + incrementValue.toInt()
            binding.mainActTvNumber.text = newNumber.toString()

            //update summary view
            binding.mainActTvSummary.text = "$currentNumber + $incrementValue = $newNumber"
        }
        else{
            //determine new number to display
            val newNumber = currentNumber - incrementValue.toInt()
            binding.mainActTvNumber.text = newNumber.toString()

            //update summary view
            binding.mainActTvSummary.text = "$currentNumber - $incrementValue = $newNumber"
        }
        hidekeyboard()
    }


    private fun hidekeyboard(){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.mainActTvNumber.windowToken, 0)
    }
}