package tesis.untref.com.firealerts.unit.main.presenter

import io.reactivex.Completable
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import tesis.untref.com.firealerts.initializer.model.action.InitApplication
import tesis.untref.com.firealerts.initializer.presenter.MainPresenter
import tesis.untref.com.firealerts.initializer.view.MainView
import tesis.untref.com.firealerts.unit.rules.RxCustomSchedulersRule

class MainPresenterTest {

    private lateinit var mainPresenter: MainPresenter
    private lateinit var mainView: MainView
    private lateinit var initApplication: InitApplication

    @JvmField
    @Rule
    val customSchedulersRule = RxCustomSchedulersRule()

    @Test
    fun initAppShouldInitAndChangeScreen(){
        givenAnMainPresenter()

        whenInitApplication()

        thenInitAppAndMoveToNextView()

    }

    private fun thenInitAppAndMoveToNextView() {
        verify(initApplication, times(1)).invoke()
        verify(mainView, times(1)).nextView()
    }

    private fun givenAnMainPresenter() {
        mainView = mock(MainView::class.java)
        initApplication = mock(InitApplication::class.java)
        `when`(initApplication.invoke()).thenReturn(Completable.fromAction {  })
        mainPresenter = MainPresenter(mainView, initApplication)
    }

    private fun whenInitApplication() {
        mainPresenter.initAppConfig()
    }
}