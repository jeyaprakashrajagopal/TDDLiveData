package com.anonymous.taxifinderwithflow

import com.anonymous.taxifinderwithflow.data.VehicleListResponse
import com.anonymous.taxifinderwithflow.data.VehicleResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class VehicleAPIServiceImpl(val apiService: VehicleAPIService) {
    suspend fun getVehicles(viewBounds: HashMap<String, Any>): Flow<VehicleResult> {
        return flow<VehicleResult> {
            emit(VehicleResult.Success(apiService.getVehicles(viewBounds)))
        }.catch { e ->
            emit(VehicleResult.Failure(e.message.toString()))
        }
    }
}