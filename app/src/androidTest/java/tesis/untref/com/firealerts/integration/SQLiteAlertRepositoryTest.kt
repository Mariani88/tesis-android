package tesis.untref.com.firealerts.integration

import android.support.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.runner.RunWith
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import org.junit.After
import org.junit.Assert
import org.junit.Test
import tesis.untref.com.firealerts.infrastructure.sqlite.dao.AlertDao
import tesis.untref.com.firealerts.infrastructure.sqlite.dao.AlertDataBase
import tesis.untref.com.firealerts.infrastructure.sqlite.entity.AlertEntity
import tesis.untref.com.firealerts.infrastructure.sqlite.entity.CoordinateEntity
import tesis.untref.com.firealerts.infrastructure.sqlite.repository.SQLiteAlertRepository
import tesis.untref.com.firealerts.model.CardinalPoint
import java.io.IOException
import java.util.*

@RunWith(AndroidJUnit4::class)
class SQLiteAlertRepositoryTest {

    private lateinit var inMemoryDatabase: AlertDataBase
    private lateinit var inMemoryAlertDao: AlertDao
    private lateinit var sqLiteAlertRepository: SQLiteAlertRepository
    private val degree = 30
    private val minute = 40
    private val second = 23f
    private val east = CardinalPoint.EAST.name
    private val north = CardinalPoint.NORTH.name
    private val alertId = 3L

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getTargetContext()
        inMemoryDatabase = Room.inMemoryDatabaseBuilder(context, AlertDataBase::class.java).build()
        inMemoryAlertDao = inMemoryDatabase.alertDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        inMemoryDatabase.close()
    }

    @Test
    fun insertAlertShouldStoreIt(){
        val latitude = CoordinateEntity(degree, minute, second, east)
        val longitude = CoordinateEntity(degree, minute, second, north)
        val alertEntity = AlertEntity(alertId, latitude, longitude, Date())
        inMemoryAlertDao.insertAll(alertEntity)
        val storedAlertEntity = inMemoryAlertDao.findById(alertId)
        Assert.assertEquals(alertId, storedAlertEntity.toAlert().id)
    }
}