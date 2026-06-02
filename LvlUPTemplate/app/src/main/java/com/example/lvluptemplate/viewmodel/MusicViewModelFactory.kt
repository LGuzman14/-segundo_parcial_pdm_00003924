package com.example.lvluptemplate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lvluptemplate.data.repository.MusicRepository

class MusicViewModelFactory(
    private val repository: MusicRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MusicViewModel::class.java)) {
            return MusicViewModel(repository) as T
        }

        throw IllegalArgumentException("ViewModel desconocido")
    }
}