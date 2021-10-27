package com.anonymous.taxifinderwithflow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.anonymous.taxifinderwithflow.utils.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

open class BaseUnittest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
}