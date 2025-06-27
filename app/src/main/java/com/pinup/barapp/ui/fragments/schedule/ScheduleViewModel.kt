package com.pinup.barapp.ui.fragments.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pinup.barapp.domain.MatchRepository
import com.pinup.barapp.domain.models.Match
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Month
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val repository: MatchRepository
) : ViewModel() {

    private val _matches = MutableLiveData<List<Match>>()
    val matches: LiveData<List<Match>> = _matches

    fun loadMatches() {
        viewModelScope.launch {
            val currentMonth = LocalDate.now().month
            _matches.value = repository.getMatchesByMonth(currentMonth)        }
    }

    fun loadMatchesForMonth(month: Month) {
        viewModelScope.launch {
            _matches.value = repository.getMatchesByMonth(month)
        }
    }

}