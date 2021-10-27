package com.anonymous.taxifinderwithflow

import com.anonymous.taxifinderwithflow.data.VehicleResult
import kotlinx.coroutines.flow.Flow

interface VehicleRespository {
    suspend fun getVehicles(viewBounds: HashMap<String, Any>): Flow<VehicleResult>
}
