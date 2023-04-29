package com.homairaahmed.bddoctorhub.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.homairaahmed.bddoctorhub.data.Category
import com.homairaahmed.bddoctorhub.data.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DashboardRepository @Inject constructor(){

    private val mPostsCollection = FirebaseFirestore.getInstance().collection("Category")


    fun getAllCategory() = flow<Resource<List<Category>>> {

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
}