package com.proway.projeto_crud_di.model

import androidx.room.*

@Entity
data class Physician(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "phy_id")
    var id: Int = 0,
    @ColumnInfo(name = "phy_name")
    val name: String,
    val specialityPk: Int
)

data class PhysicianPOJO(
    @Embedded
    val physician: Physician?,
    @Relation(
        parentColumn = "specialityPk",
        entityColumn = "spe_id"
    )
    val speciality: Speciality?
)
