package com.example.kotlinexam1application.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.kotlinexam1application.R
import com.example.kotlinexam1application.activity.UpdateInfoActivity
import com.example.kotlinexam1application.databinding.ShowInfoLayoutBinding
import com.example.kotlinexam1application.helper.PasswordHelper.Companion.initDB
import com.example.kotlinexam1application.model.PasswordEntity

class InformationAdapter(private var passList: MutableList<PasswordEntity>) : Adapter<InformationAdapter.PassViewHolder>() {

    class PassViewHolder(itemView: View) : ViewHolder(itemView) {

        val binding = ShowInfoLayoutBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PassViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.show_info_layout,parent,false)
        return PassViewHolder(view)
    }

    override fun getItemCount(): Int {
        return passList.size
    }

    override fun onBindViewHolder(holder: PassViewHolder, position: Int) {

        holder.binding.setSiteNameRv.text = passList[position].siteName
        holder.binding.setEmailRv.text = passList[position].email
        holder.binding.setSiteFirstTxtRv.text = passList[position].siteName.first().toString()

        holder.binding.cardBackground.setOnClickListener {
            val intent = Intent(holder.itemView.context,UpdateInfoActivity::class.java)
            intent.putExtra("siteName",passList[position].siteName)
            intent.putExtra("email",passList[position].email)
            intent.putExtra("password",passList[position].password)
            intent.putExtra("category",passList[position].category)
            holder.itemView.context.startActivity(intent)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun dataChange(list: MutableList<PasswordEntity>)
    {
        passList = list
        notifyDataSetChanged()
    }
}