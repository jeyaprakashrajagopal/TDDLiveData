package com.anonymous.taxifinderwithflow.data

sealed class VehicleResult {
    data class Loading(val status: Boolean) : VehicleResult()
    data class Success(val vehicleData: VehicleListResponse): VehicleResult()
    data class Failure(val error: String) : VehicleResult()

}
