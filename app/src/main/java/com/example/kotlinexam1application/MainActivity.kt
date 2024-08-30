package com.example.kotlinexam1application

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.util.query
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
        filterData()
        searchSite()
    }

    override fun onResume() {

        passList = initDB(this).passDAO().passRead()
        passAdapter!!.dataChange(passList)
        super.onResume()
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



    private fun searchSite()
    {
        binding.searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(newText: String): Boolean {
                searchFilter(newText)
                return false
            }

        })

    }

    fun searchFilter(text:String)
    {
        val filteredList = mutableListOf<PasswordEntity>()

        for(item in passList)
        {
            if(item.siteName.lowercase().contains(text.lowercase()))
            {
                filteredList.add(item)
            }
        }
        passAdapter!!.dataChange(filteredList)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun filterData()
    {
        binding.filterBtn.setOnClickListener {

            val dialog = Dialog(this)
            dialog.setContentView(R.layout.filter_dialog)
            dialog.setCanceledOnTouchOutside(false)
            val aToz:CardView = dialog.findViewById(R.id.aToz)
            val zToa:CardView = dialog.findViewById(R.id.zToa)
            val dialogCancelBtn:ImageView = dialog.findViewById(R.id.dialogCancelBtn)

            aToz.setOnClickListener {
                passList.sortBy { it.siteName }
                passAdapter!!.notifyDataSetChanged()
                dialog.dismiss()
            }
            zToa.setOnClickListener{
                passList.sortByDescending { it.siteName }
                passAdapter!!.notifyDataSetChanged()
                dialog.dismiss()
            }
            dialogCancelBtn.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }
    }
}