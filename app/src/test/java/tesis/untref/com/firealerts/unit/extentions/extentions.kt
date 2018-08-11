package tesis.untref.com.firealerts.unit.extentions

import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

fun LocalDateTime.toDate(): Date = Date.from(this.atZone(ZoneId.systemDefault()).toInstant())