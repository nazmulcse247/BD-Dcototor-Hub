package com.homairaahmed.bddoctorhub.viewmodel

import androidx.lifecycle.ViewModel
import com.homairaahmed.bddoctorhub.repository.OtherServiceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OtherServiceViewModel @Inject constructor(private val otherServiceRepository: OtherServiceRepository) : ViewModel(){

    fun getHospital() = otherServiceRepository.getHospital()
}