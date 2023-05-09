package com.homairaahmed.bddoctorhub.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.homairaahmed.bddoctorhub.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: CategoryRepository) : ViewModel() {

    val categoryName = MutableStateFlow("")
    val otherServiceName = MutableStateFlow("")

    fun getCategoryDoctor() = repository.getCategoryDoctor(categoryName.value)


}