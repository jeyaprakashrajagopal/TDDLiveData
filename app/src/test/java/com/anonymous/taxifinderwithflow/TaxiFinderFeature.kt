package com.anonymous.taxifinderwithflow

import androidx.lifecycle.Observer
import com.anonymous.taxifinderwithflow.data.VehicleListResponse
import com.anonymous.taxifinderwithflow.data.VehicleResult
import com.anonymous.taxifinderwithflow.data.VehicleViewmodel
import com.anonymous.taxifinderwithflow.utils.captureValues
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.inOrder
import org.mockito.kotlin.whenever

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(MockitoJUnitRunner::class)
class TaxiFinderFeature : BaseUnittest() {


    @Mock
    private lateinit var lifeCycleObserver: Observer<VehicleResult>

    @Mock
    private lateinit var apiService: VehicleAPIServiceImpl

    private val showLoading = VehicleResult.Loading(true)
    private val hideLoading = VehicleResult.Loading(false)
    private val vehicleData = VehicleListResponse(listOf())
    private val success = VehicleResult.Success(vehicleData)
    val viewBounds = hashMapOf<String, Any>()

    private lateinit var viewmodel : VehicleViewmodel


    val testCoroutineDispatcher = TestCoroutineDispatcher()

    lateinit var repository : VehicleRepositoryImpl
    @Before
    fun setUp() {
        Dispatchers.setMain(testCoroutineDispatcher)
        repository = VehicleRepositoryImpl(apiService)
        viewmodel = VehicleViewmodel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    @Test
    fun testGetTaxisAroundTheHamburgRegion() = runBlockingTest {

        whenever(repository.getVehicles(viewBounds)).then {
            flow<VehicleResult> {
                emit(success)
            }
        }

        viewmodel.liveData.observeForever(lifeCycleObserver)

        runBlockingTest {

        assertEquals(success, repository.getVehicles(viewBounds).first())
        }
    }
}