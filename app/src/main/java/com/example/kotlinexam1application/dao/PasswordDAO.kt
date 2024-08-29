package com.example.kotlinexam1application.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.kotlinexam1application.model.PasswordEntity

@Dao
interface PasswordDAO {

    @Insert
    fun passInsert(model:PasswordEntity)

    @Update
    fun passUpdate(model: MutableList<PasswordEntity>)

    @Delete
    fun passDelete(model: PasswordEntity)

    @Query("SELECT * FROM Password")
    fun passRead() : MutableList<PasswordEntity>
}