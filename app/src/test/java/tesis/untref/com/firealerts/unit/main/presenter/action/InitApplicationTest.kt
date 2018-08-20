package tesis.untref.com.firealerts.unit.main.presenter.action

import io.reactivex.Completable
import org.junit.Test
import org.mockito.Mockito.*
import tesis.untref.com.firealerts.alert.infrastructure.message.firebase.FirebaseTopicService
import tesis.untref.com.firealerts.alert.infrastructure.message.firebase.topic
import tesis.untref.com.firealerts.initializer.model.action.InitApplication

class InitApplicationTest {

    private lateinit var initApplication: InitApplication
    private lateinit var firebaseTopicService: FirebaseTopicService
    private lateinit var initializationCompletable: Completable

    @Test
    fun initApplicationShouldSubscribeToTopic(){
        givenAnInitApplicationAction()

        whenInitApplication()

        thenSubscribeToFirebaseTopic()
    }

    private fun thenSubscribeToFirebaseTopic() {
        initializationCompletable.test().await()
        verify(firebaseTopicService, times(1)).subscribeTo(topic)
    }

    private fun givenAnInitApplicationAction() {
        firebaseTopicService = mock(FirebaseTopicService::class.java)
        initApplication = InitApplication(firebaseTopicService)
    }

    private fun whenInitApplication() {
        initializationCompletable = initApplication()
    }
}