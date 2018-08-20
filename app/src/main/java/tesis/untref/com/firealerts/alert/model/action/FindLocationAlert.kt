package tesis.untref.com.firealerts.alert.model.action

import com.google.android.gms.maps.model.LatLng
import io.reactivex.Flowable
import tesis.untref.com.firealerts.alert.model.AlertRepository
import tesis.untref.com.firealerts.alert.model.service.CoordinatesAdapterService

class FindLocationAlert(private val alertRepository: AlertRepository,
                        private val coordinatesAdapterService: CoordinatesAdapterService) {

    fun find(alertId: Long): Flowable<LatLng> {
        return alertRepository.findById(alertId)
                .map { coordinatesAdapterService.toDecimalDegreeCoordinate(it.coordinate) }
    }
}