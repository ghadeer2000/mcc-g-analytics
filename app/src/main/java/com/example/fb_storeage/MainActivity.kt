package com.example.fb_storeage

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private val PICK_IMAGE_REQUEST = 234
    lateinit var btn_pick: View
    lateinit var btn_upload: View
    var imageView: ImageView? = null
    var filePath: Uri? = null
    var firebaseStorage: StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_pick = findViewById(R.id.btn_pick)
        btn_upload = findViewById(R.id.btn_upload)
        imageView = findViewById(R.id.img)

//        firebaseStorage = FirebaseStorage.getInstance().reference
        firebaseStorage = FirebaseStorage.getInstance().reference.child("img/share.png")

        try {
            val localfile = File.createTempFile("share", "png")

            firebaseStorage!!.getFile(localfile).addOnSuccessListener {
                Log.e("TAG", "SuccessImage ")
                var bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
                img.setImageBitmap(bitmap)

            }.addOnFailureListener {
                Log.e("TAG", "FaildImage ")

            }
        }catch (e : Exception){
            Toast.makeText(this,"ff",Toast.LENGTH_LONG).show()
        }

//        btn_pick.setOnClickListener {
//            showFileChooser()
//        }
//
//        btn_upload.setOnClickListener {
//            uploadFile()
//        }


    }
//
//    private fun showFileChooser() {
//        val intent = Intent()
//        intent.type = "image/*"
//        intent.action = Intent.ACTION_GET_CONTENT
//        startActivityForResult(
//            Intent.createChooser(intent, "Select an image"), PICK_IMAGE_REQUEST
//        )
//    }
//
//
//    private fun uploadFile() {
//        val fileRef = firebaseStorage!!.child("images/img")
//        fileRef.putFile(filePath!!)
//            .addOnSuccessListener { Log.e("TAG", "Success: ") }
//            .addOnFailureListener { e -> Log.e("TAG", "Failure: $e") }
//    }
//
//    override fun onActivityResult(
//        requestCode: Int,
//        resultCode: Int,
//        data: Intent?
//    ) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
//            filePath = data.data
//            imageView!!.setImageURI(filePath)
//        }
//    }
//
}