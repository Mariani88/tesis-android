package tesis.untref.com.firealerts.infrastructure.sqlite.repository

import android.arch.persistence.room.Room
import android.content.Context
import tesis.untref.com.firealerts.infrastructure.sqlite.dao.AlertDataBase


class SQLiteAlertRepository (context: Context){

    init {
        val db = Room.databaseBuilder(context, AlertDataBase::class.java, "database-name").build()
    }


}