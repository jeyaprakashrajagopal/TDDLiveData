package com.anonymous.taxifinderwithflow.data

data class VehicleModel (
    val id : Long,
    val coordinate: Coordinates,
    val fleetType: String,
    val heading: Double
) {
    data class Coordinates (
        val latitude: Double,
        val longitude: Double
    )
}