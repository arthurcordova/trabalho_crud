package com.proway.projeto_crud_di.database.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.ABORT
import com.proway.projeto_crud_di.model.Patient
import com.proway.projeto_crud_di.model.Physician
import com.proway.projeto_crud_di.model.PhysicianPOJO
import com.proway.projeto_crud_di.model.Speciality

@Dao
interface PhysicianDao {

    @Transaction
    @Query("Select * from Physician order by phy_name")
    fun fetch(): List<PhysicianPOJO>

    @Transaction
    @Query("Select * from Physician where phy_id = :id")
    fun fetch(id: Int): PhysicianPOJO

    @Transaction
    @Query("Select * from Physician where phy_name like '%' || :name || '%'")
    fun fetch(name: String): List<PhysicianPOJO>

    @Insert(onConflict = ABORT)
    fun insert(list: List<Physician>)

    @Delete
    fun delete(speciality: Physician)

    @Update
    fun update(speciality: Physician)

}