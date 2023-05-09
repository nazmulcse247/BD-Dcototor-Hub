package com.homairaahmed.bddoctorhub.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.homairaahmed.bddoctorhub.data.Hospital
import com.homairaahmed.bddoctorhub.data.Resource
import com.homairaahmed.bddoctorhub.utils.Constrant.Companion.HOSPITAL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class OtherServiceRepository @Inject constructor(){

    private val fireStoreInstance = FirebaseFirestore.getInstance()

    fun getHospital() = flow<Resource<List<Hospital>>> {

        val hospitalCollection = fireStoreInstance.collection(HOSPITAL)

        emit(Resource.Loading())

        val snapshot = hospitalCollection.get().await()
        val hospitals = snapshot.toObjects(Hospital::class.java)

        emit(Resource.Success(hospitals))


    }.catch {
        emit(Resource.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}