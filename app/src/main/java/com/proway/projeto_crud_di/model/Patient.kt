package com.proway.projeto_crud_di.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Patient(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pat_id")
    var id: Int = 0,
    @ColumnInfo(name = "pat_name")
    val name: String,
    @ColumnInfo(name = "pat_age")
    val age: Int,

    /**
     * M or F
     */
    @ColumnInfo(name = "pat_gender")
    val gender: String
)
