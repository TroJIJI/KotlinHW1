package com.example.kotlinhw1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.kotlinhw1.MainActivity2.Companion.TEXT
import com.example.kotlinhw1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerForActivity()
        setupListener()
    }

    private fun setupListener() {
        binding.btnFirst.setOnClickListener {
            if (binding.etData.text.toString().isEmpty()) {
                Toast.makeText(this, getString(R.string.toast_text), Toast.LENGTH_SHORT).show()
            } else openActivity()
        }
    }

    private fun registerForActivity() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    binding.etData.setText(result.data?.getStringExtra(TEXT))
                }
            }
    }

    private fun openActivity() {
        Intent(this, MainActivity2::class.java).apply {
            putExtra(TEXT, binding.etData.text.toString())
            resultLauncher.launch(this)
        }
    }
}