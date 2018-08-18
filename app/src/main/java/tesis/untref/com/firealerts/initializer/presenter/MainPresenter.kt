package tesis.untref.com.firealerts.initializer.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import tesis.untref.com.firealerts.initializer.view.MainView
import tesis.untref.com.firealerts.initializer.model.action.InitApplication
import java.util.concurrent.TimeUnit

class MainPresenter (private val mainView: MainView, private val initApplication: InitApplication){

    fun initAppConfig() {
        initApplication()
                .delay(3000L, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { mainView.nextView() }
    }
}