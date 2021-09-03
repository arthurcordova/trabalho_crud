package com.proway.projeto_crud_di.model

import androidx.room.*

@Entity
data class Scheduling(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "sch_id")
    var id: Int = 0,
    val patientFK: Int,
    val physicianFK: Int
)

data class SchedulingPOJO(
    @Embedded
    val scheduling: Scheduling,

    @Relation(
        parentColumn = "patientFK",
        entityColumn = "pat_id"
    )
    val patient: Patient,

    @Relation(
        parentColumn = "physicianFK",
        entityColumn = "phy_id"
    )
    val physician: PhysicianPOJO
)
