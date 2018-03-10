package tesis.untref.com.firealerts.alert.infrastructure.sqlite.entity

import android.arch.persistence.room.ColumnInfo
import tesis.untref.com.firealerts.alert.model.AlertAddress

data class AlertAddressEntity (

        @ColumnInfo(name = "street")
        var street: String?,

        @ColumnInfo(name = "subThoroughfare")
        var subThoroughfare: String?,

        @ColumnInfo(name = "locality")
        var locality: String?,

        @ColumnInfo(name = "province")
        var province: String?,

        @ColumnInfo(name = "country")
        var country: String?){

    constructor(): this(null, null, null, null, null)

    constructor(alertAddress: AlertAddress): this(alertAddress.street, alertAddress.subThoroughfare,
            alertAddress.locality, alertAddress.province, alertAddress.country)

    fun toAddress(): AlertAddress = AlertAddress(street!!, subThoroughfare!!, locality!!, province!!, country!!)
}