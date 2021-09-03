package com.proway.projeto_crud_di.database.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.ABORT
import com.proway.projeto_crud_di.model.Patient
import com.proway.projeto_crud_di.model.Speciality

@Dao
interface SpecialityDao {

    @Query("Select * from Speciality order by spe_name")
    fun fetch(): List<Speciality>

    @Query("Select * from Speciality where spe_id = :id")
    fun fetch(id: Int): Speciality

    @Query("Select * from Speciality where spe_name like '%' || :name || '%'")
    fun fetch(name: String): List<Speciality>

    @Insert(onConflict = ABORT)
    fun insert(list: List<Speciality>)

    @Delete
    fun delete(speciality: Speciality)

    @Update
    fun update(speciality: Speciality)

}