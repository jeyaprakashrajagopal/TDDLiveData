package com.anonymous.taxifinderwithflow

import com.anonymous.taxifinderwithflow.data.VehicleResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class VehicleRepositoryImpl(val apiService: VehicleAPIServiceImpl)
    : VehicleRespository {
    override suspend fun getVehicles(viewBounds: HashMap<String, Any>): Flow<VehicleResult> = apiService.getVehicles(viewBounds)
}
