package com.example.facebook_authen


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.database.FirebaseDatabase

/**
 * A simple [Fragment] subclass.
 */
class data_realtime : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_data_realtime, container, false)
        // Inflate the layout for this fragment

        //ButtonShowData
//        val ShowData: Button = view.findViewById(R.id.showdata)
//        //
//
//        val btn1 = view.findViewById<Button>(R.id.btn1)
//        val btn2 = view.findViewById<Button>(R.id.btn2)
//        val btn3 = view.findViewById<Button>(R.id.btn3)
//        val btn4 = view.findViewById<Button>(R.id.btn4)

        //ประกาศตัวแปร DatabaseReference รับค่า Instance และอ้างถึง path ที่เราต้องการใน database
        val mRootRef = FirebaseDatabase.getInstance().getReference()

        //อ้างอิงไปที่ path ที่เราต้องการจะจัดการข้อมูล ตัวอย่างคือ users และ messages
        val mUsersRef = mRootRef.child("users")
        val mMessagesRef = mRootRef.child("messages")

//        btn1.setOnClickListener {
//            //setValue() เป็นการ write หรือ update ข้อมูล ไปยัง path ที่เราอ้างถึงได้ เช่น users/<user-id>/<username>
//            mUsersRef.child("id-60160156").setValue("Jakkawan")
//        }
//        btn2.setOnClickListener {
//            val friendlyMessage = FriendlyMessage("60160156", "HAHAHA1");
//            mMessagesRef.push().setValue(friendlyMessage);
//        }
//        btn3.setOnClickListener {
//
//            // push เป็นการ generate $postid ของ object ชื่อ posts ออกมาก่อนเพื่อใช้ใน // /user-posts/$userid/$postid
//            val key = mMessagesRef.push().key
//            val postValues: HashMap<String, Any> = HashMap()
//            postValues["username"] = "60160156"
//            postValues["text"] = "HAHAHA2"
//
//            val childUpdates: MutableMap<String, Any> = HashMap()
//
//            childUpdates["/messages/$key"] = postValues
//
//            childUpdates["/user-messages/Jakkawan/$key"] = postValues
//
//            mMessagesRef.updateChildren(childUpdates)
//
//        }
//        btn4.setOnClickListener {
//
//            val mMessagesRef2 = mRootRef.child("data")
//
//            val key = mMessagesRef.push().key
//            val postValues: HashMap<String, Any> = HashMap()
//            postValues["username"] = "60160156"
//            postValues["text"] = "HAHAHA3"
//
//            val childUpdates: MutableMap<String, Any> = HashMap()
//
//            childUpdates["$key"] = postValues
//
//            mMessagesRef2.updateChildren(childUpdates)
//
//        }
//
//        //ShowData
//        ShowData.setOnClickListener {
////            val show_data_realtime = show_data_realtime()
////            val fm = fragmentManager
////            val transaction: FragmentTransaction = fm!!.beginTransaction()
////            transaction.replace(R.id.contentContainer, show_data_realtime, "MainChart")
////            transaction.addToBackStack("MainChart")
////            transaction.commit()
//
//            val ShowData = show_data_realtime()
//            val fm = fragmentManager
//            val transaction: FragmentTransaction = fm!!.beginTransaction()
//
//            transaction.replace(R.id.contentContainer, ShowData,"fragment_ShowData")
//            transaction.addToBackStack("fragment_ShowData")
//            transaction.commit()
//
//        }
        //

        return view

    }

    data class FriendlyMessage(
        var username: String? = "",
        var text: String? = ""
    )

}
