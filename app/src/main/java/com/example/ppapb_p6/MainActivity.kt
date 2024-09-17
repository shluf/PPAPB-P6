package com.example.ppapb_p6

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.ppapb_p6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var provinces: Array<String>
    private val countries = arrayOf(
        "Indonesia",
        "United States",
        "United Kingdom",
        "Germany",
        "France",
        "Australia",
        "Japan",
        "China",
        "Brazil",
        "Canada"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        provinces = resources.getStringArray(R.array.provinces)
        with(binding){
            val adapterCountry = ArrayAdapter(this@MainActivity,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, countries)
            adapterCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerCountry.adapter = adapterCountry
            val adapterProvinces = ArrayAdapter(this@MainActivity,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, provinces)
            adapterProvinces.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerProvinces.adapter = adapterProvinces

            spinnerCountry.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View, position: Int, id: Long
                    ) {
                        Toast.makeText(
                            this@MainActivity,
                            countries[position], Toast.LENGTH_SHORT
                        ).show()
                    }
                    override fun onNothingSelected(parent: AdapterView<*>) {
                        // write code to perform some action
                    }
                }

            datePicker.init(
                datePicker.year,
                datePicker.month,
                datePicker.dayOfMonth
            ) { _, year, monthOfYear, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
                Toast.makeText(this@MainActivity, selectedDate, Toast.LENGTH_SHORT).show()
// Gunakan selectedDate sesuai kebutuhan Anda
            }

            timePicker.setOnTimeChangedListener { view, hourOfDay, minute ->
                val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                Toast.makeText(this@MainActivity, selectedTime, Toast.LENGTH_SHORT).show()
// Gunakan selectedTime sesuai kebutuhan Anda
            }

            btnShowCalendar.setOnClickListener {
                val datePicker = DatePicker()
                datePicker.show(supportFragmentManager, "datePicker")
            }

            btnShowTimePicker.setOnClickListener {
                val timePicker = TimePicker()
                timePicker.show(supportFragmentManager, "timePicker")
            }

            btnShowAlertDialog.setOnClickListener {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("Keluar")
                builder.setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
                builder.setPositiveButton("Ya") { dialog, which ->
//lakukan sesuatu ketika tombol positif diklik
                    finish()
                }
                builder.setNegativeButton("Tidak") { dialog, _ ->
//lakukan sesuatu ketika tombol negatif diklik
                    dialog.dismiss()
                }
// Membuat dan menampilkan dialog
                val dialog = builder.create()
                dialog.show()
            }

            btnShowCustomDialog.setOnClickListener {
                val dialog = DialogExit()
                dialog.show(supportFragmentManager, "dialogExit")
            }


        }
    }

    override fun onDateSet(p0: android.widget.DatePicker?, p1: Int, p2: Int, p3:
    Int) {
// Gunakan p1, p2, p3 untuk mendapatkan tanggal, bulan, dan tahun
        val selectedDate = "$p3/${p2 + 1}/$p1"
        Toast.makeText(this@MainActivity, selectedDate,
            Toast.LENGTH_SHORT).show()
    }

    override fun onTimeSet(p0: android.widget.TimePicker?, p1: Int, p2: Int) {
// Gunakan p1 dan p2 untuk mendapatkan jam dan menit
        val selectedTime = String.format("%02d:%02d", p1, p2)
        Toast.makeText(this@MainActivity, selectedTime,
            Toast.LENGTH_SHORT).show()
    }
}
