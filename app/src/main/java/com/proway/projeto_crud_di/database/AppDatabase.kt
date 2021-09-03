package com.proway.projeto_crud_di.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.proway.projeto_crud_di.database.dao.PatientDao
import com.proway.projeto_crud_di.model.Patient
import com.proway.projeto_crud_di.model.Physician
import com.proway.projeto_crud_di.model.Scheduling
import com.proway.projeto_crud_di.model.Speciality

/**
 * Classe abstrata para criar nosso database local
 * Devemos passar na annotation @Database os parametros:
 * @param entities Array com a lista de classes com a annotation @Entity
 * @param version Int versao do banco (Quando qualquer coisa for alterada em relaçao
 * ao banco este numero deve ser incrementado)
 */
@Database(
    entities = [
        Patient::class,
        Speciality::class,
        Physician::class,
        Scheduling::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    /**
     * funcao declarada para o Room implementar automaticamente nosso DAO
     */
    abstract fun getPatientDao(): PatientDao

    companion object {

        /**
         * Singleton que irá gerar nossa classe AppDatabse com tudo implementado pelo Room.
         */
        fun getDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "crud_di_app_db"
            )
                .allowMainThreadQueries()
                .build()
        }
    }

}
