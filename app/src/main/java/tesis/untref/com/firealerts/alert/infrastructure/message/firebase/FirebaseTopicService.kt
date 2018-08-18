package tesis.untref.com.firealerts.alert.infrastructure.message.firebase

import com.google.firebase.messaging.FirebaseMessaging

class FirebaseTopicService {

    fun subscribeTo(topic: String) = FirebaseMessaging.getInstance().subscribeToTopic(topic)
}