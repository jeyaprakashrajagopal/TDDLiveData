package com.anonymous.taxifinderwithflow

import com.anonymous.taxifinderwithflow.data.VehicleListResponse
import com.anonymous.taxifinderwithflow.data.VehicleResult
import com.anonymous.taxifinderwithflow.data.VehicleViewmodel
import com.anonymous.taxifinderwithflow.utils.getValueForTest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class VehicleViewmodelTest : BaseUnittest() {

    private val vehicleData = VehicleListResponse(listOf())
    private val viewBounds = hashMapOf<String, Any>()
    val repository = mock<VehicleRespository>()
    lateinit var viewmodel : VehicleViewmodel

    @Before
    fun setUp() {
    }

    @Test
    fun testGetVehicles() {
        runBlockingTest {
            `when`(repository.getVehicles(viewBounds)).then {
                flow<VehicleResult> {
                    emit(VehicleResult.Success(vehicleData))
                }
            }

        viewmodel = VehicleViewmodel(repository)
        viewmodel.getVehicles(viewBounds).getValueForTest()

        verify(repository, times(1)).getVehicles(viewBounds)
        }




    }
}