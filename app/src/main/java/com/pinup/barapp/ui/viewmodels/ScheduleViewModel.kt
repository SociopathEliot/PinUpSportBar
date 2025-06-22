package com.pinup.barapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pinup.barapp.domain.MatchRepository
import com.pinup.barapp.domain.models.Match
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val repository: MatchRepository
) : ViewModel() {

    private val _matches = MutableLiveData<List<Match>>()
    val matches: LiveData<List<Match>> = _matches

    fun loadMatches() {
        viewModelScope.launch {
            _matches.value = repository.getUpcomingMatches()
        }
    }

    fun loadRecentMatches() {
        viewModelScope.launch {
            _matches.value = repository.getRecentMatches()
        }
    }
}
