package com.proway.projeto_crud_di.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Speciality(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "spe_id")
    var id: Int = 0,
    @ColumnInfo(name = "spe_name")
    val name: String
)
