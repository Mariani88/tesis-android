package tesis.untref.com.firealerts.initializer.model.action

import io.reactivex.Completable
import tesis.untref.com.firealerts.alert.infrastructure.message.firebase.FirebaseTopicService
import tesis.untref.com.firealerts.alert.infrastructure.message.firebase.topic

class InitApplication (private val firebaseTopicService: FirebaseTopicService){

    operator fun invoke(): Completable =
        Completable
                .fromAction { firebaseTopicService.subscribeTo(topic) }
}