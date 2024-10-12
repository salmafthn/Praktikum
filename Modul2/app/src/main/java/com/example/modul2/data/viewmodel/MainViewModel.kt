package com.example.modul2.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modul2.data.entity.Github
import com.example.modul2.data.entity.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {
    private val ProfileRepository = ProfileRepository()

    private val _user = MutableStateFlow<Github?>(null)
    val user: StateFlow<Github?> = _user

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun getGithubProfile(user: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val profile = ProfileRepository.getProfile(user)
                _user.value = profile
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
                _user.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }

}