package com.homairaahmed.bddoctorhub.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.homairaahmed.bddoctorhub.data.Category
import com.homairaahmed.bddoctorhub.data.OtherService
import com.homairaahmed.bddoctorhub.data.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DashboardRepository @Inject constructor(){

    fun getAllCategory() = flow<Resource<List<Category>>> {

        val mPostsCollection = FirebaseFirestore.getInstance().collection("Category")
        //val categoryCollection = mPostsCollection.collection("Category")

        // Emit loading state
        emit(Resource.Loading())

        val snapshot = mPostsCollection.get().await()
        val posts = snapshot.toObjects(Category::class.java)

        // Emit success state with data
        emit(Resource.Success(posts))

    }.catch {
        // If exception is thrown, emit failed state along with message.
        emit(Resource.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)


    fun getOtherService() = flow<Resource<List<OtherService>>> {

        val otherServiceCollection = FirebaseFirestore.getInstance().collection("OtherService")
        //val otherServiceCollection = mPostsCollection.collection("OtherService")

        // Emit loading state
        emit(Resource.Loading())

        val snapshot = otherServiceCollection.get().await()
        val otherService = snapshot.toObjects(OtherService::class.java)

        // Emit success state with data
        emit(Resource.Success(otherService))

    }.catch {
        // If exception is thrown, emit failed state along with message.
        emit(Resource.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

}