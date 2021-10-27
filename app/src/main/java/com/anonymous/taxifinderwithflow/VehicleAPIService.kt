package com.anonymous.taxifinderwithflow

import com.anonymous.taxifinderwithflow.data.VehicleListResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface VehicleAPIService {
    companion object {
        const val BASE_URL = "https://fake-poi-api.mytaxi.com/"
    }

    @GET(".")
    suspend fun getVehicles(@QueryMap viewBounds: HashMap<String, Any>) : VehicleListResponse
}
