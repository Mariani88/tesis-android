package tesis.untref.com.firealerts.alert.presenter

import com.google.firebase.messaging.FirebaseMessaging
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import tesis.untref.com.firealerts.MainActivity
import tesis.untref.com.firealerts.message.infrastructure.topic
import java.util.concurrent.TimeUnit

class MainPresenter (private val mainActivity: MainActivity){

    fun initAppConfig() {
        Completable
                .fromAction { FirebaseMessaging.getInstance().subscribeToTopic(topic) }
                .delay(3000L, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { mainActivity.nextView() }
    }
}