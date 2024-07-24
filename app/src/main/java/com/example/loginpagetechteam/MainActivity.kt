package com.example.loginpagetechteam

import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginpagetechteam.R.drawable.password_show

class MainActivity : AppCompatActivity() {
    private lateinit var password:EditText
    private var isPasswordVisible: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        password = findViewById(R.id.password)
        password.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= (password.right - password.compoundDrawables[2].bounds.width())) {
                    // Toggle password visibility
                    isPasswordVisible = !isPasswordVisible
                    password.inputType = if (isPasswordVisible) {
                        InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    } else {
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    }
                    password.setSelection(password.text.length) // Move cursor to the end
                    password.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.baseline_lock_outline_24,
                        0,
                        if (isPasswordVisible) password_show else R.drawable.password_hide,
                        0
                    )
                    return@setOnTouchListener true
                }
            }
            false
        }
    }
}