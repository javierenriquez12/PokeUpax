package com.upax.pokedex.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nisuml.mapsdata.domain.model.Location
import com.nisuml.mapsdata.domain.usecase.LocationUseCase
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.util.Calendar
import javax.inject.Inject
import kotlin.time.Duration.Companion.minutes

@HiltViewModel
class MainViewModel @Inject constructor(val locationUseCase: LocationUseCase) : ViewModel() {

    private var _stateMain = MutableLiveData<MainModel>()
    val stateModel: LiveData<MainModel> get() = _stateMain
    private val timeMinutes = (2).minutes

    fun start() {
        viewModelScope.launch(Dispatchers.Main) {
            _stateMain.value = MainModel.RequestTimerOnFinished(false)
            delay(timeMinutes)
            _stateMain.value = MainModel.RequestTimerOnFinished(true)
        }
    }

    fun saveLocations(latitude: String, longitude: String) {
        val date = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM)
            .format(Calendar.getInstance().time)
        locationUseCase.locationRepository.addLocation(Location(latitude, longitude, date))
    }
}