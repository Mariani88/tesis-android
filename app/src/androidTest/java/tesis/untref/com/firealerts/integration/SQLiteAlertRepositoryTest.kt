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
import tesis.untref.com.firealerts.infrastructure.sqlite.entity.LatitudeEntity
import tesis.untref.com.firealerts.infrastructure.sqlite.entity.LongitudeEntity
import tesis.untref.com.firealerts.infrastructure.sqlite.repository.SQLiteAlertRepository
import tesis.untref.com.firealerts.model.CardinalPoint
import java.io.IOException
import java.util.*

@RunWith(AndroidJUnit4::class)
class SQLiteAlertRepositoryTest {

    private var inMemoryDatabase: AlertDataBase? = null
    private lateinit var inMemoryAlertDao: AlertDao
    private lateinit var sqLiteAlertRepository: SQLiteAlertRepository
    private val degree = 30
    private val minute = 40
    private val second = 23f
    private val east = CardinalPoint.EAST.name
    private val north = CardinalPoint.NORTH.name
    private val alertId = 3L
    private lateinit var alertEntity: AlertEntity

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getTargetContext()
        inMemoryDatabase = Room.inMemoryDatabaseBuilder(context, AlertDataBase::class.java).build()
        inMemoryAlertDao = inMemoryDatabase!!.alertDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        inMemoryDatabase?.close()
    }

    @Test
    fun insertAlertShouldStoreIt(){
        givenAnAlertEntity()
        whenStoreAlertEntity()
        thenFindIt()
    }

    private fun thenFindIt() {
        inMemoryAlertDao.findById(alertId).subscribe({Assert.assertEquals(it.toAlert().id, alertId) })
    }

    private fun whenStoreAlertEntity() {
        inMemoryAlertDao.insertAll(alertEntity)
    }

    private fun givenAnAlertEntity() {
        val latitude = LatitudeEntity(degree, minute, second, east)
        val longitude = LongitudeEntity(degree, minute, second, north)
        alertEntity = AlertEntity(alertId, CoordinateEntity(latitude, longitude), Date())
    }
}