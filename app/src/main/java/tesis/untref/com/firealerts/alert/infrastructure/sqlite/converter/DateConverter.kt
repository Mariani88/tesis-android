package tesis.untref.com.firealerts.alert.infrastructure.sqlite.converter

import android.arch.persistence.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun toDate(timeStamp: Long): Date = Date(timeStamp)

    @TypeConverter
    fun fromDate(date: Date) = date.time
}