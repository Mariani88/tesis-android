package tesis.untref.com.firealerts.alert.model

data class AlertAddress (val street: String, val subThoroughfare: String, val locality: String,
                    val province: String, val country: String){

    companion object {
        private const val unAvailable = "unavailable"
        val empty = AlertAddress(unAvailable, unAvailable, unAvailable, unAvailable, unAvailable)
    }

    override fun toString(): String {
        return "calle:$street\n" +
                "direccion: $subThoroughfare\n" +
                "localidad: $locality\n" +
                "provincia: $province\n" +
                "pais: $country\n"
    }
}