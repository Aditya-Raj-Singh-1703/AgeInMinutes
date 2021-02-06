package com.aditya.dobcalulator

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var btn : Button
    lateinit var TVselectedDate : TextView
    lateinit var SelectedDateInMinutes : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn = findViewById(R.id.BtnSelectBday)
        TVselectedDate = findViewById(R.id.tvSelectedDate)
        SelectedDateInMinutes = findViewById(R.id.tvSelectedDateInMinutes)
        btn.setOnClickListener {view ->
            clickDatePicker(view)
        }
    }
    fun clickDatePicker(view: View){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, {
            view, selectedYear, selectedMonth, selectedDayOfMonth ->
            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            TVselectedDate.text = selectedDate

            //Giving proper format to Date
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            //Converting the selected date from string to 'Date' data type
            val theDate = sdf.parse(selectedDate)

            //Calculating Age in minutes
            val selectedDateInMinutes = theDate!!.time / 60000

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes = currentDate!!.time / 60000
            val difference = currentDateInMinutes - selectedDateInMinutes
            SelectedDateInMinutes.text = difference.toString()

        }, year, month, day).show()

    }
}