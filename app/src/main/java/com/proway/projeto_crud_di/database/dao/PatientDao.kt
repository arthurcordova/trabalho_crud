package com.proway.projeto_crud_di.database.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.ABORT
import com.proway.projeto_crud_di.model.Patient

@Dao
interface PatientDao {

    @Query("Select * from Patient order by pat_name")
    fun fetch(): List<Patient>

    @Query("Select * from Patient where pat_id = :id")
    fun fetch(id: Int): Patient

    @Query("Select * from Patient where pat_gender = :gender")
    fun fetch(gender: String): List<Patient>

    @Insert(onConflict = ABORT)
    fun insert(list: List<Patient>)

    @Delete
    fun delete(patient: Patient)

    @Update
    fun update(patient: Patient) : Int

}