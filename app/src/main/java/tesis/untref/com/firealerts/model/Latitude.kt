package tesis.untref.com.firealerts.model


data class Latitude (val degree: Int, val minute: Int, val second: Float, val cardinalPoint: CardinalPoint){

    fun isSouth() = cardinalPoint == CardinalPoint.SOUTH
}