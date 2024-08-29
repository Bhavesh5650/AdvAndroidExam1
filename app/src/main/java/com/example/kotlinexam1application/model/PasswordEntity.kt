package com.example.kotlinexam1application.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Password")
data class PasswordEntity(
    @PrimaryKey(autoGenerate = true)
    var passId:Int=0,
    @ColumnInfo
    var siteName:String,
    @ColumnInfo
    var email:String,
    @ColumnInfo
    var password:String,
    @ColumnInfo
    var category:String
)
