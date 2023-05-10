package com.homairaahmed.bddoctorhub.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.homairaahmed.bddoctorhub.data.Doctor
import com.homairaahmed.bddoctorhub.data.Hospital
import com.homairaahmed.bddoctorhub.data.Icu
import com.homairaahmed.bddoctorhub.data.Resource
import com.homairaahmed.bddoctorhub.utils.Constrant.Companion.DOCTOR
import com.homairaahmed.bddoctorhub.utils.Constrant.Companion.HOSPITAL
import com.homairaahmed.bddoctorhub.utils.Constrant.Companion.HOSPITALCODE
import com.homairaahmed.bddoctorhub.utils.Constrant.Companion.ICU
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


    fun getCategoryDoctor(category : String) = flow<Resource<List<Doctor>>> {

        val categoryCollection = fireStoreInstance.collection(DOCTOR).whereEqualTo(HOSPITALCODE, category)

        // Emit loading state
        emit(Resource.Loading())

        val snapshot = categoryCollection.get().await()
        val doctors = snapshot.toObjects(Doctor::class.java)

        // Emit success state with data
        emit(Resource.Success(doctors))

    }.catch {
        // If exception is thrown, emit failed state along with message.
        emit(Resource.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

    fun getIcu() = flow<Resource<List<Icu>>> {

        val categoryCollection = fireStoreInstance.collection(ICU)

        // Emit loading state
        emit(Resource.Loading())

        val snapshot = categoryCollection.get().await()
        val icu = snapshot.toObjects(Icu::class.java)

        // Emit success state with data
        emit(Resource.Success(icu))

    }.catch {
        // If exception is thrown, emit failed state along with message.
        emit(Resource.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}