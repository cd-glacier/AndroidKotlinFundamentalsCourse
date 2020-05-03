package com.android.example.aboutme

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val myName: MyName = MyName("Aleks Haecky")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.myName = myName

        binding.doneButton.setOnClickListener {
            updateNickname(it)
        }

        binding.nicknameText.setOnClickListener {
            makeNicknameEditable(it)
        }
    }

    private fun updateNickname(doneButton: View) {
        val editText: EditText = binding.nicknameEdit
        val nicknameTextView: TextView = binding.nicknameText

        binding.apply {
            myName?.nickname = editText.text.toString()
            invalidateAll()
            editText.visibility = View.GONE
            doneButton.visibility = View.GONE
            nicknameTextView.visibility = View.VISIBLE
        }

        // Hide the keyboard.
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(doneButton.windowToken, 0)
    }

    private fun makeNicknameEditable(nickNameTextView: View) {
        val editText: EditText = binding.nicknameEdit
        val doneButton: Button = binding.doneButton

        binding.apply {
            editText.visibility = View.VISIBLE
            doneButton.visibility = View.VISIBLE
            nickNameTextView.visibility = View.GONE
        }

        editText.requestFocus()

        // Show the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, 0)
    }
}
