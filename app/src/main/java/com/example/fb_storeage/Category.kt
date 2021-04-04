package com.example.fb_storeage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_category.*
import java.text.SimpleDateFormat
import java.util.*


class Category : AppCompatActivity() {
    private var mFirebaseAnalytics: FirebaseAnalytics? = null
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
        setContentView(R.layout.activity_category)
        db = Firebase.firestore
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
         id = UUID.randomUUID().toString()


        cat_clothes.setOnClickListener {
            var i = Intent(this, cat_Clothes::class.java)
            startActivity(i)

        }
        cat_electronic.setOnClickListener {
            var i = Intent(this, com.example.fb_storeage.cat_electronic::class.java)
            startActivity(i)
        }
        cat_food.setOnClickListener {
            var i = Intent(this, cat_Food::class.java)
            startActivity(i)

        }
        callAnalytic(id,"Cateory")


    }






    fun callAnalytic(id: String, name: String) {
//        val bundle = Bundle()
//        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id)
//        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name)
//        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image")
//        mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)

///////////////////////////////////
//        val Custombundle = Bundle()
//        bundle.putString("id", id)
//        bundle.putString("name", name)
//        mFirebaseAnalytics!!.logEvent("share", Custombundle)

///////////////////////////////////
        var screenName = "Cateory"
        val trackScreenbundle = Bundle()
        trackScreenbundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
        trackScreenbundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "MainActivity")
        mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, trackScreenbundle)


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
        addScreen(id,"Cateory","$hour:$minute:$second")



    }
}