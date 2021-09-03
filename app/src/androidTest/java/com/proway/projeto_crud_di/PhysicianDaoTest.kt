package com.proway.projeto_crud_di

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.proway.projeto_crud_di.database.AppDatabase
import com.proway.projeto_crud_di.database.dao.PatientDao
import com.proway.projeto_crud_di.database.dao.PhysicianDao
import com.proway.projeto_crud_di.database.dao.SpecialityDao
import com.proway.projeto_crud_di.model.Patient
import com.proway.projeto_crud_di.model.Physician
import com.proway.projeto_crud_di.model.Speciality
import org.junit.After
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class PhysicianDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var daoPhysician: PhysicianDao
    private lateinit var daoSpeciality: SpecialityDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        daoSpeciality = database.getSpecialityDao()
        daoPhysician = database.getPhysicianDao()
    }

    @After
    fun teardown() {
        database.close()
    }


    @Test
    fun testing_insert() {
        val s1 = Speciality(1, "s1")
        val s2 = Speciality(2, "s2")
        daoSpeciality.insert(listOf(s1, s2))

        val p1 = Physician(name = "Jaime", specialityPk = s2.id)
        val p2 = Physician(name = "Jaime", specialityPk = s1.id)
        val listToInsert = arrayListOf(p1, p2)

        daoPhysician.insert(listToInsert)

        val results = daoPhysician.fetch()
        assertThat(results).hasSize(listToInsert.size)
    }

    @Test
    fun testing_fetch_by_name() {

        val s1 = Speciality(1, "s1")
        val s2 = Speciality(2, "s2")
        daoSpeciality.insert(listOf(s1, s2))

        val p1 = Physician(name = "Jaime", specialityPk = s2.id)
        val p2 = Physician(name = "Botao", specialityPk = s1.id)
        val listToInsert = arrayListOf(p1, p2)

        daoPhysician.insert(listToInsert)
        val results = daoPhysician.fetch("ao")
        assertThat(results).hasSize(1)
    }

    @Test
    fun testing_fetch_by_id() {
        val s1 = Speciality(1, "s1")
        val s2 = Speciality(2, "s2")
        daoSpeciality.insert(listOf(s1, s2))

        val p1 = Physician(id = 1, name = "Jaime", specialityPk = s2.id)
        val p2 = Physician(id = 2, name = "Botao", specialityPk = s1.id)
        val listToInsert = arrayListOf(p1, p2)

        daoPhysician.insert(listToInsert)

        val result = daoPhysician.fetch(2)
        assertThat(result.physician?.name).isEqualTo(p2.name)
        assertThat(result.speciality?.name).isEqualTo(s1.name)
    }

    @Test
    fun testing_update() {
        val s1 = Speciality(1, "s1")
        val s2 = Speciality(2, "s2")
        daoSpeciality.insert(listOf(s1, s2))

        val p1 = Physician(id = 1, name = "Jaime", specialityPk = s2.id)
        val p2 = Physician(id = 2, name = "Botao", specialityPk = s1.id)
        val listToInsert = arrayListOf(p1, p2)

        daoPhysician.insert(listToInsert)

        val forUpdate = Physician(
            id = p2.id,
            name = "Phy2Updated",
            specialityPk = s2.id
        )
        daoPhysician.update(forUpdate)

        val result = daoPhysician.fetch(p2.id)
        assertThat(result.physician?.name).isEqualTo(forUpdate.name)
        assertThat(result.physician?.specialityPk).isEqualTo(forUpdate.specialityPk)
    }

    @Test
    fun testing_delete() {
        val s1 = Speciality(1, "s1")
        val s2 = Speciality(2, "s2")
        daoSpeciality.insert(listOf(s1, s2))

        val p1 = Physician(id = 1, name = "Jaime", specialityPk = s2.id)
        val p2 = Physician(id = 2, name = "Botao", specialityPk = s1.id)
        val listToInsert = arrayListOf(p1, p2)

        daoPhysician.insert(listToInsert)

        daoPhysician.delete(p1)

        val result = daoPhysician.fetch()
        assertThat(result).doesNotContain(p1)
    }
}