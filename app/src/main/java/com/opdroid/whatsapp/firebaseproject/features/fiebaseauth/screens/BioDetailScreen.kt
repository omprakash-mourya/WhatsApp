package com.opdroid.whatsapp.firebaseproject.features.fiebaseauth.screens

//import androidx.activity.compose.rememberLauncherForActivityResult
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.ImageDecoder
import android.graphics.Paint
import android.graphics.Shader
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.opdroid.whatsapp.R
import com.opdroid.whatsapp.extra.hideProgressDialog
import com.opdroid.whatsapp.extra.showProgressDialog
import com.opdroid.whatsapp.firebaseproject.features.firestoredb.UserResponse
import com.opdroid.whatsapp.firebaseproject.features.firestoredb.ui.FirestoreViewModel
import com.opdroid.whatsapp.firebaseproject.utils.ResultState
import com.opdroid.whatsapp.firebaseproject.utils.showMsg
import com.opdroid.whatsapp.theme.PreferencesManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import kotlin.math.min

@Composable
fun BioDetailScreen(preferencesManager: PreferencesManager,
                    onNavigateNext: () -> Unit,
                    viewModel: FirestoreViewModel = hiltViewModel()
) {
    val currentUser = FirebaseAuth.getInstance().currentUser

    val uid = currentUser?.uid.toString()
    preferencesManager.setUserUid(uid)

    val scope = rememberCoroutineScope()

    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }


    val snackbarHostState = remember { SnackbarHostState() }
    SnackbarHost(
        hostState = snackbarHostState,
        modifier = Modifier.statusBarsPadding()
    )
    val coroutineScope = rememberCoroutineScope()
    var canNavigate by remember { mutableStateOf(false) }

    var username by remember { mutableStateOf("") }
    var bio by remember { mutableStateOf("Hey there! I am using another WhatsApp made by Omprakash.") }

    var profilePictureBitmap by remember {
        mutableStateOf<Bitmap?>(null)
    }
//    var profilePictureUrl by remember {
//        mutableStateOf("")
//    }
    // Create an image picker
    val imagePicker = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri = uri
        profilePictureBitmap = uri?.let { ImageUriToBitmap(context, it) }
    }

    val imagePainter = remember(imageUri) { image(context = context, imageUri = imageUri) }

    val defaultPhoneNumber = preferencesManager.getOwnerCountryCode() + " " + preferencesManager.getOwnerContactNumber().toString()

    Column(modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Spacer(modifier = Modifier.height(20.dp) )
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(100.dp), contentAlignment = Alignment.Center) {
            Image(
                painter = if(imagePainter != null) imagePainter else painterResource(id = R.drawable.account_circle),
                contentDescription = "User Image",
                modifier = Modifier
                    .size(150.dp)
                    .clickable {
                        //data = bitmapToIntArray(image(context = context, imageUri = imageUri))
                        // Implement image picker here
                        // Update imageUri with the selected image
                        imagePicker.launch("image/*")
                    }
            )
        }
//        image(context = context, imageUri = imageUri)

        TextField(
            value = defaultPhoneNumber,
            onValueChange = {},
            label = { Text("Phone Number") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            enabled = false
        )

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        OutlinedTextField(
            value = bio,
            onValueChange = { bio = it },
            label = { Text("Bio") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        Button(onClick = {
            if(username.isNotEmpty()) {
                scope.launch(Dispatchers.IO) {
                    withContext(Dispatchers.Main) {
                        showProgressDialog("Signing in...")
                        preferencesManager.setUserName(username)
                    }
                    val profilePictureUrl =
                        profilePictureBitmap?.let { uploadImage(it, "profilePictures/$username") }.toString()
                    viewModel.insertUser(
                        UserResponse.User(
                            uid = uid,
                            username = username,
                            phoneNumber = defaultPhoneNumber,
                            profilePictureUrl = profilePictureUrl,
                            bio = bio
                        )
                    ).collect{
                        when(it){
                            is ResultState.Success -> {
                                context.showMsg(it.data)
                                canNavigate = true
                                withContext(Dispatchers.Main) {
                                    onNavigateNext()
                                    hideProgressDialog()
                                }

                            }
                            is ResultState.Failure -> {
                                context.showMsg(it.msg.toString())
                                withContext(Dispatchers.Main) {
                                    hideProgressDialog()
                                }
                            }
                            is ResultState.Loading -> {
                                context.showMsg("Loading...")
                            }
                        }
                    }
                }
                if(canNavigate) {
                    onNavigateNext()
                }
                hideProgressDialog()
            } else {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(message = "Enter Username")
                }
            }
        },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {
            Text("Next")
        }
    }
}

//@Composable
fun image(context:Context, imageUri:Uri?): BitmapPainter? {

    return imageUri?.let {

        var bitmap = ImageUriToBitmap(context = context, imageUri = it)

        // Crop the bitmap into a circle
        val size = min(bitmap.width, bitmap.height)
        val outputBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(outputBitmap)
        val paint = Paint()

        val x = (bitmap.width - size) / 2
        val y = (bitmap.height - size) / 2

        val shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.shader = shader
        paint.isAntiAlias = true

//        val rect = Rect(x, y, x + size, y + size)
        val radius = size / 2f
        canvas.drawCircle(radius+x, radius+y, radius, paint)
//        canvas.drawRect(rect, paint)

        bitmap = Bitmap.createBitmap(outputBitmap, x, y, size, size)

        /*Image(
            bitmap = bitmap?.asImageBitmap()!!,
            contentDescription = "",
            modifier = Modifier.size(200.dp)
        )*/
            BitmapPainter(bitmap.asImageBitmap())
    }
}

fun ImageUriToBitmap(context: Context, imageUri: Uri): Bitmap {
    return if (Build.VERSION.SDK_INT < 28) {
        MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
    } else {
        val source = ImageDecoder.createSource(context.contentResolver, imageUri)
        ImageDecoder.decodeBitmap(source)
    }
}

suspend fun uploadImage(bitmap: Bitmap, storagePath: String): String = withContext(Dispatchers.Default){
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
    downloadUrl.toString()
}

fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
    return stream.toByteArray()
}

fun byteArrayToBitmap(byteArray: ByteArray): Bitmap {
    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
}
