package com.example.comp3025assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.comp3025assignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Will direct user to the dashboard activity after login
        binding.loginButton.setOnClickListener{
            if(binding.usernameEditText.text.toString().isEmpty())
                Toast.makeText(this, "Please enter your username!", Toast.LENGTH_LONG).show()
        }
    }
}