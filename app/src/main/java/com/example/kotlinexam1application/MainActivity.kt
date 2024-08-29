package com.example.kotlinexam1application

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlinexam1application.activity.AddInfoActivity
import com.example.kotlinexam1application.adapter.InformationAdapter
import com.example.kotlinexam1application.databinding.ActivityMainBinding
import com.example.kotlinexam1application.helper.PasswordHelper.Companion.initDB
import com.example.kotlinexam1application.model.PasswordEntity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var passAdapter:InformationAdapter?=null
    private var passList = mutableListOf<PasswordEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initFloatClick()
        setRecycleView()
    }

    private fun initFloatClick()
    {
        binding.infoFlotBtn.setOnClickListener{

            val intent = Intent(this@MainActivity,AddInfoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setRecycleView()
    {
        passAdapter = InformationAdapter(passList)
        binding.infoRv.adapter = passAdapter
    }

    override fun onResume() {

        passList = initDB(this).passDAO().passRead()
        passAdapter!!.dataChange(passList)
        super.onResume()
    }
}