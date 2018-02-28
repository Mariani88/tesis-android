package tesis.untref.com.firealerts.alert.infrastructure.sqlite.repository

import android.arch.persistence.room.Room
import android.content.Context
import tesis.untref.com.firealerts.alert.infrastructure.sqlite.dao.AlertDataBase

class AlertRepositoryProvider {

    companion object {
        private const val DATABASE_NAME = "alerts"
        private var sqLiteAlertRepository: SQLiteAlertRepository? = null

        fun getInstance(context: Context): SQLiteAlertRepository {

            if (sqLiteAlertRepository == null) {
                sqLiteAlertRepository = createRepository(context)
            }

            return sqLiteAlertRepository!!
        }

        private fun createRepository(context: Context): SQLiteAlertRepository {
            return SQLiteAlertRepository(
                    Room.databaseBuilder(context, AlertDataBase::class.java, DATABASE_NAME).build().alertDao()
            )
        }
    }
}