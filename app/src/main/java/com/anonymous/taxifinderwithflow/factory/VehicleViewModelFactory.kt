package com.anonymous.taxifinderwithflow.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anonymous.taxifinderwithflow.VehicleRepositoryImpl
import com.anonymous.taxifinderwithflow.data.VehicleViewmodel

class VehicleViewModelFactory(val repositoryImpl: VehicleRepositoryImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(VehicleViewmodel::class.java)) {
            return VehicleViewmodel(repositoryImpl) as T
        }
        else return Exception("unknown error") as T
    }
}