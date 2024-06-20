package com.opdroid.whatsapp.firebaseproject.features.firestoredb.repository

import android.graphics.Bitmap
import android.provider.ContactsContract
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.opdroid.whatsapp.firebaseproject.features.firestoredb.FirestoreModelResponse
import com.opdroid.whatsapp.firebaseproject.features.firestoredb.UserResponse
import com.opdroid.whatsapp.firebaseproject.utils.ResultState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class FirestoreDbRepositoryImpl @Inject constructor(
    private val db:FirebaseFirestore
) : FirestoreRepository{

    override fun insert(item: FirestoreModelResponse.FirestoreItem): Flow<ResultState<String>> = callbackFlow{
        trySend(ResultState.Loading)
        db.collection("user")
            .add(item)
            .addOnSuccessListener {
                trySend(ResultState.Success("Data is inserted with ${it.id}"))
            }.addOnFailureListener {
                trySend(ResultState.Failure(it))
            }
        awaitClose {
            close()
        }
    }

    override fun getItems(): Flow<ResultState<List<FirestoreModelResponse>>> =  callbackFlow{
        trySend(ResultState.Loading)
        db.collection("user")
            .get()
            .addOnSuccessListener {
             val items =  it.map { data->
                    FirestoreModelResponse(
                        item = FirestoreModelResponse.FirestoreItem(
                            title = data["title"] as String?,
                            description = data["description"] as String?
                        ),
                        key = data.id
                    )
                }
                trySend(ResultState.Success(items))
            }.addOnFailureListener {
                trySend(ResultState.Failure(it))
            }

        awaitClose {
            close()
        }
    }

    override fun delete(key: String): Flow<ResultState<String>> = callbackFlow{
        trySend(ResultState.Loading)
        db.collection("user")
            .document(key)
            .delete()
            .addOnCompleteListener {
                if(it.isSuccessful)
                    trySend(ResultState.Success("Deleted successfully.."))
            }.addOnFailureListener {
                trySend(ResultState.Failure(it))
            }
        awaitClose {
            close()
        }
    }

    override fun update(item: FirestoreModelResponse): Flow<ResultState<String>> = callbackFlow{
        trySend(ResultState.Loading)
        val map = HashMap<String,Any>()
        map["title"] = item.item?.title!!
        map["description"] = item.item.description!!

        db.collection("user")
            .document(item.key!!)
            .update(map)
            .addOnCompleteListener {
                if(it.isSuccessful)
                    trySend(ResultState.Success("Update successfully..."))
            }.addOnFailureListener {
                trySend(ResultState.Failure(it))
            }
        awaitClose {
            close()
        }
    }

    override fun insertUser(item: UserResponse.User): Flow<ResultState<String>> = callbackFlow{
        trySend(ResultState.Loading)
//        val profilePicturePath = "profilePictures/${item.username}"
//        val profilePictureUrl = item.profilePicture?.let { uploadImage(it, profilePicturePath) }
//
//        val userWithProfilePictureUrl = item.copy(profilePictureUrl = profilePictureUrl)

        db.collection("users")
            .add(item)
            .addOnSuccessListener {
                trySend(ResultState.Success("Data is inserted with ${it.id}"))
            }.addOnFailureListener {
                trySend(ResultState.Failure(it))
            }
        awaitClose {
            close()
        }
    }

    override fun getUser(): Flow<ResultState<List<UserResponse>>> = callbackFlow{
        trySend(ResultState.Loading)
        db.collection("user")
            .get()
            .addOnSuccessListener {
                val items =  it.map { data->
                    UserResponse(
                        user = UserResponse.User(
                            uid = data["uid"] as String,
                            username = data["username"] as String,
                            email = data["email"] as ContactsContract.CommonDataKinds.Email?,
                            phoneNumber = data["phoneNumber"] as String,
                            profilePicture = data["profilePicture"] as Bitmap?,
                            profilePictureUrl = data["profilePictureUrl"] as String,
                            bio = data["bio"] as String,
                            status = data["status"] as String?
                        ),
                        key = data.id
                    )
                }
                trySend(ResultState.Success(items))
            }.addOnFailureListener {
                trySend(ResultState.Failure(it))
            }
        awaitClose {
            close()
        }
    }

    override fun updateUser(item: UserResponse): Flow<ResultState<String>> = callbackFlow{
        trySend(ResultState.Loading)
        val map = HashMap<String,Any>()
        map["uid"] = item.user.uid
        map["username"] = item.user.username
        map["email"] = item.user.email!!
        map["phoneNumber"] = item.user.phoneNumber
        map["profilePicture"] = item.user.profilePicture!!
        map["bio"] = item.user.bio
        map["status"] = item.user.status!!

        db.collection("user")
            .document(item.key!!)
            .update(map)
            .addOnCompleteListener {
                if(it.isSuccessful)
                    trySend(ResultState.Success("Update successfully..."))
            }.addOnFailureListener {
                trySend(ResultState.Failure(it))
            }
        awaitClose {
            close()
        }
    }

    suspend fun uploadImage(bitmap: Bitmap, storagePath: String): String {
        // Convert the bitmap to a ByteArray
        val byteArray = bitmapToByteArray(bitmap)

        // Create a reference to the location where you want to upload the image
        val storageRef = FirebaseStorage.getInstance().reference.child(storagePath)

        // Upload the ByteArray to Firebase Storage
        val uploadTask = storageRef.putBytes(byteArray)
        uploadTask.await() // This will suspend the coroutine until the upload is complete

        // Get the download URL of the image
        val downloadUrl = storageRef.downloadUrl.await()

        // Return the download URL as a String
        return downloadUrl.toString()
    }

    fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }


}