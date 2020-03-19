package com.example.facebook_authen

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.facebook_authen.R
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class MainActivity : AppCompatActivity() {
    val authen = authen()
    val manager = supportFragmentManager
    val transaction = manager.beginTransaction()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        debugHashKey()
        transaction.replace(R.id.contentContainer, authen,"fragment_authen")
        transaction.addToBackStack("fragment_authen")
        transaction.commit()

        val ShowData = data_realtime()
        transaction.replace(R.id.show, ShowData,"fragment_ShowData")
        transaction.addToBackStack("fragment_ShowData")

    }

    //
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val MainChart = MainChart()
//        val manager = supportFragmentManager
//        val transaction = manager.beginTransaction()
//        transaction.replace(R.id.contentContainer, MainChart,"fragment_MainChart")
//        transaction.addToBackStack("fragment_MainChart")
//        transaction.commit()
//
//    }

    //

    private fun debugHashKey() {
        try {
            val info = packageManager.getPackageInfo(
                "com.example.facebook_authen",
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KeyHash:", Base64.getEncoder().encodeToString(md.digest()))
            }
        } catch (e: PackageManager.NameNotFoundException) {
        } catch (e: NoSuchAlgorithmException) {
        }
    }
    override fun onBackPressed() {

        val manager = supportFragmentManager.findFragmentById(R.id.contentContainer)

        if (manager is authen ) {
            finish()
        }else if(manager is  menu){
            finish()
        }
        else{
            super.onBackPressed();
        }

    }
}
