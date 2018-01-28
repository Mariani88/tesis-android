package tesis.untref.com.firealerts.infrastructure.sqlite.entity

import android.arch.persistence.room.Embedded

data class CoordinateEntity(

        @Embedded
        var latitude: LatitudeEntity?,

        @Embedded
        var longitude: LongitudeEntity?
) {

    constructor() : this(null, null)
}