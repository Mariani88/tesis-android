package tesis.untref.com.firealerts.alert.model

data class AlertAddress (val street: String, val subThoroughfare: String, val locality: String,
                    val province: String, val country: String){

    companion object {
        private const val unAvailable = "unavailable"
        val empty = AlertAddress(unAvailable, unAvailable, unAvailable, unAvailable, unAvailable)
    }

    //todo meterle un test a esto
    override fun toString(): String {
        return "street:$street\n" +
                "subThoroughfare: $subThoroughfare\n" +
                "locality: $locality\n" +
                "province: $province\n" +
                "country: $country\n"
    }
}