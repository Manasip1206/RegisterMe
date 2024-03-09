package com.example.registerme

// UserDetailsActivity.kt
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class UserDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        val name = intent.getStringExtra("name")
        val age = intent.getIntExtra("age", 0)
        val gender = intent.getStringExtra("gender")

        val tvName = findViewById<TextView>(R.id.tv_name)
        val tvAge = findViewById<TextView>(R.id.tv_age)
        val tvGender = findViewById<TextView>(R.id.tv_gender)

        tvName.text = "Name: $name"
        tvAge.text = "Age: $age"
        tvGender.text = "Gender: $gender"
    }
}
