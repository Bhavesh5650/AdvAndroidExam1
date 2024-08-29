package com.example.kotlinexam1application.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlinexam1application.R
import com.example.kotlinexam1application.databinding.ActivityAddInfoBinding
import com.example.kotlinexam1application.helper.PasswordHelper.Companion.initDB
import com.example.kotlinexam1application.model.PasswordEntity

class AddInfoActivity : AppCompatActivity() {

    private lateinit var binding:ActivityAddInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddInfoBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initBackClick()
        infoInsert()
    }

    private fun initBackClick()
    {
        binding.addInfoBackBtn.setOnClickListener { finish() }
    }

    private fun infoInsert()
    {
        binding.infoSubmitBtn.setOnClickListener {

            val siteName = binding.setSiteNameEdt.text.toString()
            val email = binding.setEmailEdt.text.toString()
            val password = binding.setPasswordEdt.text.toString()
            val category = binding.setCategoryEdt.text.toString()

            val model = PasswordEntity(siteName = siteName, email = email, password = password, category = category)

            initDB(this).passDAO().passInsert(model)

            finish()
        }
    }
}