package com.example.kotlinexam1application.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlinexam1application.R
import com.example.kotlinexam1application.adapter.InformationAdapter
import com.example.kotlinexam1application.databinding.ActivityUpdateInfoBinding
import com.example.kotlinexam1application.helper.PasswordHelper.Companion.initDB
import com.example.kotlinexam1application.model.PasswordEntity

class UpdateInfoActivity : AppCompatActivity() {

    private var id: Int=0
    private lateinit var binding: ActivityUpdateInfoBinding
    var passList = mutableListOf<PasswordEntity>()
    private var adapter:InformationAdapter?=null

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUpdateInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initBackClick()
        setDataAndUpdateDelete()

    }

    private fun initBackClick()
    {
        binding.updateInfoBackBtn.setOnClickListener { finish() }
    }


    private fun setDataAndUpdateDelete()
    {

        val siteName1 = intent.getStringExtra("siteName")
        val email1 = intent.getStringExtra("email")
        val password1 = intent.getStringExtra("password")
        val category1 = intent.getStringExtra("category")
        id = intent.getIntExtra("passId",0)

        binding.setSiteNameEdt.setText(siteName1)
        binding.setEmailEdt.setText(email1)
        binding.setPasswordEdt.setText(password1)
        binding.setCategoryEdt.setText(category1)

        binding.infoUpdateBtn.setOnClickListener {

            val siteName = binding.setSiteNameEdt.text.toString()
            val email = binding.setEmailEdt.text.toString()
            val password = binding.setPasswordEdt.text.toString()
            val category = binding.setCategoryEdt.text.toString()

            val model = PasswordEntity(passId = id, siteName = siteName, email = email, password = password, category = category)
            initDB(this).passDAO().passUpdate(model)
            finish()
        }

        binding.infoDeleteBtn.setOnClickListener {

            val siteName = binding.setSiteNameEdt.text.toString()
            val email = binding.setEmailEdt.text.toString()
            val password = binding.setPasswordEdt.text.toString()
            val category = binding.setCategoryEdt.text.toString()

            val model = PasswordEntity(passId = id, siteName = siteName, email = email, password = password, category = category)
            initDB(this).passDAO().passDelete(model)
            finish()
        }
    }
}