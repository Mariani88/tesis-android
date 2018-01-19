package tesis.untref.com.firealerts.model


data class Longitude (val degree: Int, val minute: Int, val second: Float, val cardinalPoint: CardinalPoint){

    fun isWest() = cardinalPoint == CardinalPoint.WEST
}