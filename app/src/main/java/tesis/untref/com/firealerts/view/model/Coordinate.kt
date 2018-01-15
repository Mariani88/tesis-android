package tesis.untref.com.firealerts.view.model

data class Coordinate(val degree: Int, val minute: Int, val second: Float, val cardinalPoint: CardinalPoint) {

    fun isSouth() = cardinalPoint == CardinalPoint.SOUTH

    fun isWest() = cardinalPoint == CardinalPoint.WEST
}