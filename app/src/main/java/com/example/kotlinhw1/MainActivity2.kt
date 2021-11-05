package com.example.kotlinhw1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kotlinhw1.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListener()
    }

    private fun setupListener() {

        binding.etData.setText(intent.getStringExtra(TEXT))

        binding.btnSecond.setOnClickListener {
            if (binding.etData.text.toString().isEmpty()) {
                Toast.makeText(this, getString(R.string.toast_text), Toast.LENGTH_SHORT).show()
            } else openActivity()
        }
    }

    private fun openActivity() {
        Intent(this, MainActivity::class.java).apply {
            putExtra(TEXT, binding.etData.text.toString())
            setResult(Activity.RESULT_OK, this)
            finish()
        }
    }

    companion object {
        const val TEXT = "text"
    }
}