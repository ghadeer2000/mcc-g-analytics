package com.example.fb_storeage

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_show__chategory.*
import java.io.File
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class show_Chategory : AppCompatActivity() {
    var firebaseStorage: StorageReference? = null
    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    lateinit var progressDialog: ProgressDialog
    var db: FirebaseFirestore? = null
    var Hour : Int = 0
    var Minute : Int = 0
    var Second : Int = 0
    var endHour : Int = 0
    var endMinute : Int = 0
    var endSecond : Int = 0
    var id :String = "1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show__chategory)
        db = Firebase.firestore
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        var res :String = ""
        var head :String = ""
        var details :String = ""
        var prefix :String = ""
        var sufix :String = ""
        res = intent.getStringExtra("res").toString()
        head = intent.getStringExtra("head").toString()
        details = intent.getStringExtra("del").toString()
        prefix = intent.getStringExtra("prefix").toString()
        sufix = intent.getStringExtra("sufix").toString()

        firebaseStorage = FirebaseStorage.getInstance().reference.child(res)
        txt_head.text = head
        txt_del.text = details

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("uploading image....")
        progressDialog.setCancelable(false)
        progressDialog.show()
        putFile(prefix,sufix,img_view)
        callAnalytic(id,"Cat_Show")



    }
    fun callAnalytic(id: String, name: String) {

        var screenName = "Cateory"
        val trackScreenbundle = Bundle()
        trackScreenbundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
        trackScreenbundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "MainActivity")
        mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, trackScreenbundle)


    }
    private fun putFile (prefix : String ,sufix : String , img : ImageButton){
        try {
            val localfile = File.createTempFile(prefix, sufix)

            firebaseStorage!!.getFile(localfile).addOnSuccessListener {
                Log.e("TAG", "SuccessImage ")
                var bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
                img.setImageBitmap(bitmap)
                progressDialog.dismiss()

            }.addOnFailureListener {
                Log.e("TAG", "FaildImage ")

            }
        }catch (e : Exception){
            Toast.makeText(this,"ff", Toast.LENGTH_LONG).show()
        }
    }
    private fun addScreen(id: String, name: String,  time: String) {
        var user = hashMapOf("id" to id, "name" to name, "time" to time)
        db!!.collection("Screenmaps").add(user).addOnSuccessListener { documentReference ->
            Log.e("TAG", "success")

        }.addOnFailureListener { exception ->
            Log.e("TAG", "Failed $exception")
        }
    }

    override fun onResume() {
        super.onResume()
        val sdf = SimpleDateFormat("hh:mm:ss")
        val currentDate = sdf.format(Date())

        val values = currentDate.split(":".toRegex()).toTypedArray()
        Hour = values[0].toInt()
        Minute = values[1].toInt()
        Second = values[2].toInt()
    }

    override fun onPause() {
        super.onPause()
        val sdf = SimpleDateFormat("hh:mm:ss")
        val currentDate = sdf.format(Date())

        val values = currentDate.split(":".toRegex()).toTypedArray()
        endHour = values[0].toInt()
        endMinute = values[1].toInt()
        endSecond = values[2].toInt()
        var hour =endHour  - Hour
        var minute = endMinute  - Minute
        var second = endSecond - Second

//        Toast.makeText(this,"$hour:$minute:$second",Toast.LENGTH_SHORT).show()
        Log.e("gh","$hour:$minute:$second")
        addScreen(id,"Cat_Show","$hour:$minute:$second")



    }

}