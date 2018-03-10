package tesis.untref.com.firealerts.alert.model

import java.util.*

data class Alert (val id: Long, val coordinate: Coordinate, val date: Date, var alertAddress: AlertAddress) {

    constructor(id: Long, coordinate: Coordinate, date: Date):
            this(id, coordinate, date, AlertAddress.empty)

    fun addAddress(alertAddress: AlertAddress) {
        this.alertAddress = alertAddress
    }
}