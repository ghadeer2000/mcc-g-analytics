package com.example.fb_storeage;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.TimerTask;

public class t  extends AppCompatActivity {
    StorageReference firebaseStorage;
    TimerTask timerTask;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        firebaseStorage = FirebaseStorage.getInstance().getReference().child("images/ba.jpg");

//
//        try {
//            final File localfile = File.createTempFile("ba","jpg");
//            firebaseStorage.getFile(localfile).addOnSuccessListener
//
//        } catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//        try {
//
//        }catch (IOException e){
//
//        }
        startTimer();


    }

    private void startTimer() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                }
            });
            }
        };
    }
}

//    override fun onStart() {
//    super.onStart()
//        if (startTimer == false){
//            startTimer()
//        }
//}

//    override fun onPause() {
//        super.onPause()
//        timerTask!!.cancel()
//        startTimer = true
//        Log.e("Time", "$cat_timer ")
//       var  time_Cat2 =System.currentTimeMillis()

//        time_Cat = time_Cat2 - time_Cat
//        Log.e("Time", "${time_Cat2 - time_Cat }")
//    }

//    private fun startTimer() {
//        timerTask = object : TimerTask() {
//            override fun run() {
//                runOnUiThread {
//                    DoubleTime++
//                    cat_timer.setText(getTimerText())
//                }
//
//            }
//        }
//        timer!!.scheduleAtFixedRate(timerTask,0,1000)
//
//    }
//
//    private fun getTimerText(): String {
//        var rounded = Math.round(DoubleTime).toInt()
//        var secound = ((rounded % 86400) % 3600) % 60
//        var miunte = ((rounded % 86400) % 3600)  / 60
//        var houres = ((rounded % 86400) / 3600)
//
//        return formatTimer(secound,miunte,houres)
//        }
//
//private fun formatTimer(secound: Int, miunte: Int, houres: Int): String {
//
//        return String.format("%02d",houres) + " : " +String.format("%02d",miunte)+ " : "+String.format("%02d",secound)
//
//        Toast.makeText(this,"${cat_timer}",Toast.LENGTH_LONG).show()
//        }



//         time_Cat=System.currentTimeMillis()

//        timerTask!!.cancel()
//        if (timerTask != null){
//            timerTask!!.cancel()
//        DoubleTime =0.0
//        startTimer = false
//        }
//        startTimer()

//        timer = Timer()

//    var timer: Timer? = null
//        var startTimer = false
//        var DoubleTime = 0.0
//        var time_Cat : Long =0

//    lateinit var timerTask: TimerTask
