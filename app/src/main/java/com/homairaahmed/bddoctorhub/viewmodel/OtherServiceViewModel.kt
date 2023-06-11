package com.homairaahmed.bddoctorhub.viewmodel

import androidx.lifecycle.ViewModel
import com.homairaahmed.bddoctorhub.repository.OtherServiceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class OtherServiceViewModel @Inject constructor(private val otherServiceRepository: OtherServiceRepository) : ViewModel(){

    val hospitalName = MutableStateFlow("")

    fun getHospital() = otherServiceRepository.getHospital()

    fun getCategoryDoctor() = otherServiceRepository.getCategoryDoctor(hospitalName.value)

    fun getIcu() = otherServiceRepository.getIcu()

    fun getHealthTips() = otherServiceRepository.getHealthTips()

    fun getAmbulance() = otherServiceRepository.getAmbulance()
}