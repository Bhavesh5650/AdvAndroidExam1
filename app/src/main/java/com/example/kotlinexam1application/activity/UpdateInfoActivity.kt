package com.example.kotlinexam1application.activity

import android.annotation.SuppressLint
import android.os.Bundle
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

        val siteName = intent.getStringExtra("siteName")
        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")
        val category = intent.getStringExtra("category")

        binding.setSiteNameEdt.setText(siteName)
        binding.setEmailEdt.setText(email)
        binding.setPasswordEdt.setText(password)
        binding.setCategoryEdt.setText(category)

        val model = PasswordEntity(siteName = siteName.toString(), email = email.toString(), password = password.toString(), category = category.toString())

        binding.infoUpdateBtn.setOnClickListener {
            initDB(this).passDAO().passUpdate(model)
        }

        binding.infoDeleteBtn.setOnClickListener {

            initDB(this).passDAO().passDelete(model)
            adapter!!.notifyDataSetChanged()
        }
    }

    private fun initBackClick()
    {
        binding.updateInfoBackBtn.setOnClickListener { finish() }
    }
}