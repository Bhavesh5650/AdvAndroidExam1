package com.example.kotlinexam1application.helper

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.RoomOpenHelper
import com.example.kotlinexam1application.dao.PasswordDAO
import com.example.kotlinexam1application.model.PasswordEntity

@Database(entities = [PasswordEntity::class], version = 1)
abstract class PasswordHelper : RoomDatabase() {

    abstract fun passDAO() : PasswordDAO

    companion object{

        private var db:PasswordHelper?=null

        fun initDB(context: Context) : PasswordHelper
        {
            if(db == null)
            {
                db = Room.databaseBuilder(context,PasswordHelper::class.java,"PassDatabase.db").allowMainThreadQueries().build()
            }
            return db!!
        }
    }

}