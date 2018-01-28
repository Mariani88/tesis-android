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
import java.util.concurrent.TimeUnit

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
    private lateinit var alertEntity3: AlertEntity
    private val alertId2 = 50L
    private val alertId3 = 54L
    private var sortedStoredEntities: List<AlertEntity>? = null

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

    @Test
    fun removeAllCleanTable(){
        givenStoreAlertEntities()

        whenDeleteAll()

        thenTableIsEmpty()
    }

    @Test
    fun findAllSortedByDateShouldRetrieveAllSortedAlerts(){
        givenStoreThreeAlertEntitiesOneByDay()

        whenFindSortedByDate()

        thenRetrieveSortedAlerts()
    }

    private fun thenRetrieveSortedAlerts() {
        Assert.assertTrue(sortedStoredEntities!!.isNotEmpty())
        Assert.assertEquals(3, sortedStoredEntities!!.size)
        Assert.assertEquals(alertId3, sortedStoredEntities!![0].id)
        Assert.assertEquals(alertId, sortedStoredEntities!![1].id)
        Assert.assertEquals(alertId2, sortedStoredEntities!![2].id)
    }

    private fun whenFindSortedByDate() {
        sortedStoredEntities = inMemoryAlertDao.findAllSortedByDate().blockingFirst()
    }

    private fun givenStoreThreeAlertEntitiesOneByDay() {
        alertEntity = createAlertEntity(alertId, Date(Date().time + TimeUnit.DAYS.toMillis(1)))
        alertEntity2 = createAlertEntity(alertId2, Date())
        alertEntity3 = createAlertEntity(alertId3, Date(Date().time + TimeUnit.DAYS.toMillis(2)))
        inMemoryAlertDao.insertAll(alertEntity, alertEntity2, alertEntity3)
    }

    private fun thenTableIsEmpty() {
        val alertEntities = inMemoryAlertDao.findAll().blockingFirst()
        Assert.assertTrue(alertEntities.isEmpty())
    }

    private fun whenDeleteAll() {
        inMemoryAlertDao.removeAll()
    }

    private fun givenStoreAlertEntities() {
        givenTwoAlertEntities()
        inMemoryAlertDao.insertAll(alertEntity, alertEntity2)
    }

    private fun thenFindAll() {
        val alertEntities = inMemoryAlertDao.findAll().blockingFirst()
        assertContentList(alertEntities)
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
        val storedAlertEntity = inMemoryAlertDao
                .findById(alertId)
                .blockingFirst()
        Assert.assertEquals(alertEntity, storedAlertEntity)
    }

    private fun whenStoreAlertEntity() {
        inMemoryAlertDao.insertAll(alertEntity)
    }

    private fun givenAnAlertEntity(alertId: Long = this.alertId) {
        alertEntity = createAlertEntity(alertId)
    }

    private fun createAlertEntity(alertId: Long = this.alertId, date: Date = Date()): AlertEntity {
        val latitude = LatitudeEntity(degree, minute, second, east)
        val longitude = LongitudeEntity(degree, minute, second, north)
        return AlertEntity(alertId, CoordinateEntity(latitude, longitude), date)
    }
}