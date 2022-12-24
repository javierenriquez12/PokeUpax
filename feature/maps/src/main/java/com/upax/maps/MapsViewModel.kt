package com.upax.maps

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nisuml.mapsdata.domain.usecase.LocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(private val useCase: LocationUseCase) : ViewModel() {
    private var _mapsState = MutableLiveData<MapsState>()
    val mapState: LiveData<MapsState> get() = _mapsState

    fun fetchLocations() {
        viewModelScope.launch {
            val result = with(Dispatchers.IO) {
                useCase.fetchLocations()
            }
            result.collect {
                _mapsState.value = MapsState.Locations(it)
            }
        }
    }
}