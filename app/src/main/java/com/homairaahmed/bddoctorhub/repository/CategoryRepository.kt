package com.homairaahmed.bddoctorhub.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.homairaahmed.bddoctorhub.data.Doctor
import com.homairaahmed.bddoctorhub.data.Resource
import com.homairaahmed.bddoctorhub.utils.Constrant.Companion.DEPARTMENT
import com.homairaahmed.bddoctorhub.utils.Constrant.Companion.DOCTOR
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class CategoryRepository @Inject constructor() {

    private val firestoreInstance = FirebaseFirestore.getInstance()


    fun getCategoryDoctor(category : String) = flow<Resource<List<Doctor>>> {

            val categoryCollection = firestoreInstance.collection(DOCTOR).whereEqualTo(DEPARTMENT, category)

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

    fun searchDoctor(doctorName : String) = flow<Resource<List<Doctor>>>{

        val doctorSearch = firestoreInstance.collection(DOCTOR).whereEqualTo("name", doctorName)
        emit(Resource.Loading())
        val snapshot = doctorSearch.get().await()
        val doctor = snapshot.toObjects(Doctor::class.java)
        emit(Resource.Success(doctor))
    }.catch {
        emit(Resource.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)


}
