package com.homairaahmed.bddoctorhub.viewmodel

import androidx.lifecycle.ViewModel
import com.homairaahmed.bddoctorhub.repository.DashboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val repository: DashboardRepository) : ViewModel() {

    val getAllCategory = repository.getAllCategory()

    val getOtherService = repository.getOtherService()

    val getAllPopularDoctor = repository.getAllPopularDoctor()
}