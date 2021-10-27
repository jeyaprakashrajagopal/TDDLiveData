package com.anonymous.taxifinderwithflow

import com.anonymous.taxifinderwithflow.data.VehicleListResponse
import com.anonymous.taxifinderwithflow.data.VehicleResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import java.lang.Exception
import java.util.HashMap

@RunWith(MockitoJUnitRunner::class)
class VehicleRepositoryImplTest : BaseUnittest() {

    @Mock
    lateinit var apiService :VehicleAPIServiceImpl

    var vehicleData  = VehicleListResponse(listOf())

    private val viewBounds = HashMap<String, Any>()
    private lateinit var repository : VehicleRepositoryImpl
    private lateinit var expected : VehicleResult

    val exception = RuntimeException("Authorization Error")
    val expectedException = VehicleResult.Failure(exception.message.toString())

    @Before
    fun setUp() {
        expected = VehicleResult.Success(vehicleData)
        repository = VehicleRepositoryImpl(apiService)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testInvokeAPIServiceForVehiclesData() = runBlockingTest {
        whenever(apiService.getVehicles(viewBounds)).then {
            return@then flow<VehicleResult> {
                emit(VehicleResult.Success(vehicleData))
            }
        }
        val result = repository.getVehicles(viewBounds)

        result.collect {
            assertEquals(expected, it)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testInvokeAPIServiceForVehiclesDataOnFailure()  {
        runBlockingTest {
            whenever(apiService.getVehicles(viewBounds)).then {
                return@then flow<VehicleResult> {
                    emit(expectedException)
                }
            }
            val result = repository.getVehicles(viewBounds)

            result.collect {
                assertEquals(expectedException, it)
            }
        }

    }
}