package com.anonymous.taxifinderwithflow.data

import androidx.lifecycle.*
import com.anonymous.taxifinderwithflow.VehicleRespository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class VehicleViewmodel(val repository: VehicleRespository) : ViewModel() {
    val liveData = MutableLiveData<VehicleResult>()

    fun getVehicles(viewBounds: HashMap<String, Any>) = liveData<VehicleResult> {
        emitSource(repository.getVehicles(viewBounds).asLiveData())
    }

}
