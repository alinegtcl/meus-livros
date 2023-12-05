package com.luisitolentino.meuslivros.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.luisitolentino.meuslivros.databinding.ActivityMyBooksBinding

class MyBooksActivity : AppCompatActivity() {
    private val binding: ActivityMyBooksBinding by lazy {
        ActivityMyBooksBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}