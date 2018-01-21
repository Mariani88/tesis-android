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
import tesis.untref.com.firealerts.model.CardinalPoint
import java.io.IOException
import java.util.*

@RunWith(AndroidJUnit4::class)
class AlertDaoTest {

    private var inMemoryDatabase: AlertDataBase? = null
    private lateinit var inMemoryAlertDao: AlertDao
    private val degree = 30
    private val minute = 40
    private val second = 23f
    private val east = CardinalPoint.EAST.name
    private val north = CardinalPoint.NORTH.name
    private val alertId = 3L
    private lateinit var alertEntity: AlertEntity
    private lateinit var alertEntity2: AlertEntity
    private val alertId2 = 50L

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
    fun insertAlertShouldStoreIt() {
        givenAnAlertEntity()
        whenStoreAlertEntity()
        thenFindIt()
    }

    @Test
    fun findAllShouldReturnAllAlerts() {
        givenTwoAlertEntities()

        whenInsertAll()

        thenFindAll()
    }

    private fun thenFindAll() {
        inMemoryAlertDao.findAll().subscribe { assertContentList(it) }
    }

    private fun assertContentList(alertEntities: List<AlertEntity>) {
        Assert.assertEquals(2, alertEntities.size)
        Assert.assertTrue(alertEntities.any { it.id == alertEntity.id })
        Assert.assertTrue(alertEntities.any { it.id == alertEntity2.id })
    }

    private fun whenInsertAll() {
        inMemoryAlertDao.insertAll(alertEntity, alertEntity2)
    }

    private fun givenTwoAlertEntities() {
        alertEntity = createAlertEntity(alertId)
        alertEntity2 = createAlertEntity(alertId2)
    }

    private fun thenFindIt() {
        inMemoryAlertDao.findById(alertId).subscribe({ Assert.assertEquals(it.toAlert().id, alertId) })
    }

    private fun whenStoreAlertEntity() {
        inMemoryAlertDao.insertAll(alertEntity)
    }

    private fun givenAnAlertEntity(alertId: Long = this.alertId) {
        alertEntity = createAlertEntity(alertId)
    }

    private fun createAlertEntity(alertId: Long = this.alertId): AlertEntity {
        val latitude = LatitudeEntity(degree, minute, second, east)
        val longitude = LongitudeEntity(degree, minute, second, north)
        return AlertEntity(alertId, CoordinateEntity(latitude, longitude), Date())
    }
}