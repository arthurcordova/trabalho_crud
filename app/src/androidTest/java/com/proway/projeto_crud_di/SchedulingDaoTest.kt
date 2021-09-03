package com.proway.projeto_crud_di

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.proway.projeto_crud_di.database.AppDatabase
import com.proway.projeto_crud_di.database.dao.PatientDao
import com.proway.projeto_crud_di.database.dao.PhysicianDao
import com.proway.projeto_crud_di.database.dao.SchedulingDao
import com.proway.projeto_crud_di.database.dao.SpecialityDao
import com.proway.projeto_crud_di.model.Patient
import com.proway.projeto_crud_di.model.Physician
import com.proway.projeto_crud_di.model.Scheduling
import com.proway.projeto_crud_di.model.Speciality
import org.junit.After
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class SchedulingDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var daoPhysician: PhysicianDao
    private lateinit var daoPatient: PatientDao
    private lateinit var daoScheduling: SchedulingDao
    private lateinit var daoSpeciality: SpecialityDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        daoPatient = database.getPatientDao()
        daoScheduling = database.getSchedulingDao()
        daoPhysician = database.getPhysicianDao()
        daoSpeciality = database.getSpecialityDao()
    }

    @After
    fun teardown() {
        database.close()
    }


    @Test
    fun testing_insert() {
        val pa1 = Patient(1, "pa1", 22, "M")
        val pa2 = Patient(2, "pa1", 22, "M")
        daoPatient.insert(listOf(pa1, pa2))

        val s1 = Speciality(1, "s1")
        val s2 = Speciality(2, "s2")
        daoSpeciality.insert(listOf(s1, s2))

        val phy1 = Physician(id = 1, name = "Jaime", specialityPk = s2.id)
        val phy2 = Physician(id = 2, name = "Botao", specialityPk = s1.id)
        daoPhysician.insert(listOf(phy1, phy2))

        val sche1 = Scheduling(id = 1, patientFK = pa1.id, physicianFK = phy1.id)
        val sche2 = Scheduling(id = 2, patientFK = pa2.id, physicianFK = phy2.id)
        val listToInsert = arrayListOf(sche1, sche2)
        daoScheduling.insert(listToInsert)


        val results = daoScheduling.fetch()
        assertThat(results).hasSize(listToInsert.size)
    }

    @Test
    fun testing_fetch_by_physician_name() {
        val pa1 = Patient(1, "pa1", 22, "M")
        val pa2 = Patient(2, "pa1", 22, "M")
        daoPatient.insert(listOf(pa1, pa2))

        val s1 = Speciality(1, "s1")
        val s2 = Speciality(2, "s2")
        daoSpeciality.insert(listOf(s1, s2))

        val phy1 = Physician(id = 1, name = "Jaime", specialityPk = s2.id)
        val phy2 = Physician(id = 2, name = "Botao", specialityPk = s1.id)
        daoPhysician.insert(listOf(phy1, phy2))

        val sche1 = Scheduling(id = 1, patientFK = pa1.id, physicianFK = phy1.id)
        val sche2 = Scheduling(id = 2, patientFK = pa2.id, physicianFK = phy2.id)
        val listToInsert = arrayListOf(sche1, sche2)
        daoScheduling.insert(listToInsert)

        val results = daoScheduling.fetchByPhysician("ime")
        assertThat(results).hasSize(1)
    }

    @Test
    fun testing_fetch_by_physician_specialities() {
        val pa1 = Patient(1, "pa1", 22, "M")
        val pa2 = Patient(2, "pa1", 22, "M")
        daoPatient.insert(listOf(pa1, pa2))

        val s1 = Speciality(1, "s1")
        val s2 = Speciality(2, "s2")
        daoSpeciality.insert(listOf(s1, s2))

        val phy1 = Physician(id = 1, name = "Jaime", specialityPk = s2.id)
        val phy2 = Physician(id = 2, name = "Botao", specialityPk = s1.id)
        daoPhysician.insert(listOf(phy1, phy2))

        val sche1 = Scheduling(id = 1, patientFK = pa1.id, physicianFK = phy1.id)
        val sche2 = Scheduling(id = 2, patientFK = pa2.id, physicianFK = phy2.id)
        val listToInsert = arrayListOf(sche1, sche2)
        daoScheduling.insert(listToInsert)

        val results = daoScheduling.fetchByPhysicianSpeciality(listOf(1,5,7))
        assertThat(results).hasSize(1)
    }

    @Test
    fun testing_fetch_by_patient_name() {
        val pa1 = Patient(1, "Joao", 22, "M")
        val pa2 = Patient(2, "Maria", 22, "M")
        daoPatient.insert(listOf(pa1, pa2))

        val s1 = Speciality(1, "s1")
        val s2 = Speciality(2, "s2")
        daoSpeciality.insert(listOf(s1, s2))

        val phy1 = Physician(id = 1, name = "Jaime", specialityPk = s2.id)
        val phy2 = Physician(id = 2, name = "Botao", specialityPk = s1.id)
        daoPhysician.insert(listOf(phy1, phy2))

        val sche1 = Scheduling(id = 1, patientFK = pa1.id, physicianFK = phy1.id)
        val sche2 = Scheduling(id = 2, patientFK = pa2.id, physicianFK = phy2.id)
        val listToInsert = arrayListOf(sche1, sche2)
        daoScheduling.insert(listToInsert)

        val results = daoScheduling.fetchByPatient("ria")
        assertThat(results).hasSize(1)
    }

    @Test
    fun testing_fetch_by_gender_name() {
        val pa1 = Patient(1, "Joao", 22, "M")
        val pa2 = Patient(2, "Maria", 22, "F")
        daoPatient.insert(listOf(pa1, pa2))

        val s1 = Speciality(1, "s1")
        val s2 = Speciality(2, "s2")
        daoSpeciality.insert(listOf(s1, s2))

        val phy1 = Physician(id = 1, name = "Jaime", specialityPk = s2.id)
        val phy2 = Physician(id = 2, name = "Botao", specialityPk = s1.id)
        daoPhysician.insert(listOf(phy1, phy2))

        val sche1 = Scheduling(id = 1, patientFK = pa1.id, physicianFK = phy1.id)
        val sche2 = Scheduling(id = 2, patientFK = pa2.id, physicianFK = phy2.id)
        val listToInsert = arrayListOf(sche1, sche2)
        daoScheduling.insert(listToInsert)

        val results = daoScheduling.fetchByGender("F")
        assertThat(results).hasSize(1)
    }

    @Test
    fun testing_fetch_by_id() {
        val pa1 = Patient(1, "pa1", 22, "M")
        val pa2 = Patient(2, "pa1", 22, "M")
        daoPatient.insert(listOf(pa1, pa2))

        val s1 = Speciality(1, "s1")
        val s2 = Speciality(2, "s2")
        daoSpeciality.insert(listOf(s1, s2))

        val phy1 = Physician(id = 1, name = "Jaime", specialityPk = s2.id)
        val phy2 = Physician(id = 2, name = "Botao", specialityPk = s1.id)
        daoPhysician.insert(listOf(phy1, phy2))

        val sche1 = Scheduling(id = 1, patientFK = pa1.id, physicianFK = phy1.id)
        val sche2 = Scheduling(id = 2, patientFK = pa2.id, physicianFK = phy2.id)
        val listToInsert = arrayListOf(sche1, sche2)
        daoScheduling.insert(listToInsert)

        val result = daoScheduling.fetch(1)
        assertThat(result.physician.name).isEqualTo(phy1.name)
        assertThat(result.patient.name).isEqualTo(pa1.name)
    }

    @Test
    fun testing_update() {
        val pa1 = Patient(1, "pa1", 22, "M")
        val pa2 = Patient(2, "pa1", 22, "M")
        daoPatient.insert(listOf(pa1, pa2))

        val s1 = Speciality(1, "s1")
        val s2 = Speciality(2, "s2")
        daoSpeciality.insert(listOf(s1, s2))

        val phy1 = Physician(id = 1, name = "Jaime", specialityPk = s2.id)
        val phy2 = Physician(id = 2, name = "Botao", specialityPk = s1.id)
        daoPhysician.insert(listOf(phy1, phy2))

        val sche1 = Scheduling(id = 1, patientFK = pa1.id, physicianFK = phy1.id)
        val sche2 = Scheduling(id = 2, patientFK = pa2.id, physicianFK = phy2.id)
        val listToInsert = arrayListOf(sche1, sche2)
        daoScheduling.insert(listToInsert)

        val forUpdate = Scheduling(
            id = sche1.id,
            physicianFK = phy2.id,
            patientFK = pa2.id,
        )
        daoScheduling.update(forUpdate)

        val result = daoScheduling.fetch(sche1.id)
//        assertThat(result.physician.physician?.name).isEqualTo(phy2.name)
        assertThat(result.patient.name).isEqualTo(pa2.name)
    }

    @Test
    fun testing_delete() {
        val pa1 = Patient(1, "pa1", 22, "M")
        val pa2 = Patient(2, "pa1", 22, "M")
        daoPatient.insert(listOf(pa1, pa2))

        val s1 = Speciality(1, "s1")
        val s2 = Speciality(2, "s2")
        daoSpeciality.insert(listOf(s1, s2))

        val phy1 = Physician(id = 1, name = "Jaime", specialityPk = s2.id)
        val phy2 = Physician(id = 2, name = "Botao", specialityPk = s1.id)
        daoPhysician.insert(listOf(phy1, phy2))

        val sche1 = Scheduling(id = 1, patientFK = pa1.id, physicianFK = phy1.id)
        val sche2 = Scheduling(id = 2, patientFK = pa2.id, physicianFK = phy2.id)
        val listToInsert = arrayListOf(sche1, sche2)
        daoScheduling.insert(listToInsert)

        daoScheduling.delete(sche1)

        val result = daoScheduling.fetch()
        assertThat(result).doesNotContain(sche1)
    }
}