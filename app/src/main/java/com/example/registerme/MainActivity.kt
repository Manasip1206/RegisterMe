package com.example.registerme// com.example.registerme.MainActivity.kt
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class  MainActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etAge: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var checkBox: CheckBox
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.et_name)
        etAge = findViewById(R.id.et_age)
        radioGroup = findViewById(R.id.radio_group)
        checkBox = findViewById(R.id.checkbox)
        btnRegister = findViewById(R.id.btn_register)

        btnRegister.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val name = etName.text.toString().trim()
        val ageString = etAge.text.toString().trim()

        if (TextUtils.isEmpty(name)) {
            showAlert("Name cannot be empty")
            return
        }

        if (!name.matches("[a-zA-Z]+".toRegex())) {
            showAlert("Name should not contain numbers or special characters")
            return
        }

        val age: Int
        try {
            age = ageString.toInt()
        } catch (e: NumberFormatException) {
            showAlert("Invalid age format")
            return
        }

        if (age < 0) {
            showAlert("Age cannot be negative")
            return
        }

        val selectedId = radioGroup.checkedRadioButtonId
        if (selectedId == -1) {
            showAlert("Please select gender")
            return
        }

        val radioButton = findViewById<RadioButton>(selectedId)
        val gender = radioButton.text.toString()

        val termsChecked = checkBox.isChecked
        if (!termsChecked) {
            showAlert("Please agree to terms and conditions")
            return
        }

        // If all validations pass, proceed to next activity
        val intent = Intent(this, UserDetailsActivity::class.java).apply {
            putExtra("name", name)
            putExtra("age", age)
            putExtra("gender", gender)
        }
        startActivity(intent)
    }

    private fun showAlert(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
            .setTitle("Alert")
            .setPositiveButton("OK", null)
        val dialog = builder.create()
        dialog.show()
    }
}
