package com.proway.projeto_crud_di.database.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.ABORT
import com.proway.projeto_crud_di.model.*

@Dao
interface SchedulingDao {

    @Transaction
    @Query("Select * from Scheduling s, Physician p where s.physicianFK = p.phy_id order by p.phy_name")
    fun fetch(): List<SchedulingPOJO>

    @Transaction
    @Query("Select * from Scheduling where sch_id = :id")
    fun fetch(id: Int): SchedulingPOJO

    @Transaction
    @Query("Select * from Scheduling inner join Physician on physician.phy_id = physicianFK where physician.phy_name like '%' || :name || '%'")
    fun fetchByPhysician(name: String): List<SchedulingPOJO>

    @Transaction
    @Query("Select * from Scheduling inner join Physician on physician.phy_id = physicianFK where physician.specialityPk in (:ids)")
    fun fetchByPhysicianSpeciality(ids: List<Int>): List<SchedulingPOJO>

    @Transaction
    @Query("Select * from Scheduling inner join Patient on patient.pat_id = patientFK where patient.pat_name like '%' || :name || '%'")
    fun fetchByPatient(name: String): List<SchedulingPOJO>

    @Transaction
    @Query("Select * from Scheduling inner join Patient on patient.pat_id = patientFK where pat_gender = :gender")
    fun fetchByGender(gender: String): List<SchedulingPOJO>

    @Insert(onConflict = ABORT)
    fun insert(list: List<Scheduling>)

    @Delete
    fun delete(scheduling: Scheduling)

    @Update
    fun update(scheduling: Scheduling)

}