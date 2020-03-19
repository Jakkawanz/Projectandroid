package com.example.facebook_authen

import android.content.Context
import android.media.Image
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_detail.*


class detail : Fragment() {


    var firstname:String ?= null
    var lastname:String ?= null
    var image:String ?= null
    var positions:String ?= null



    fun newInstance(image:String,firstname:String,lastname: String,positions: String): detail {
        val fragment = detail()
        val bundle = Bundle()

        bundle.putString("firstname",firstname);
        bundle.putString("lastname",lastname);
        bundle.putString("image",image);
        bundle.putString("positions",positions);

        fragment.setArguments(bundle)
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if(bundle != null){

            this.firstname = bundle.getString("firstname").toString()
            this.lastname = bundle.getString("lastname").toString()
            this.image = bundle.getString("image").toString()
            this.positions = bundle.getString("positions").toString()


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_detail, container, false)

        //database
        val mRootRef = FirebaseDatabase.getInstance().getReference()

        //อ้างอิงไปที่ path ที่เราต้องการจะจัดการข้อมูล ตัวอย่างคือ users และ messages
        val mUsersRef = mRootRef.child("users")
        val mMessagesRef = mRootRef.child("messages")
        //
        val login: Button = view.findViewById(R.id.chart);
        val realtime = view.findViewById<Button>(R.id.realtime)
        val firstname_view : TextView = view.findViewById(R.id.firstname_view)
        val lastname_view : TextView = view.findViewById(R.id.lastname_view)
        val image_view : ImageView = view.findViewById(R.id.view)
        val positions_view : TextView = view.findViewById(R.id.position_view)
        firstname_view.text = this.firstname
        lastname_view.text = this.lastname
        positions_view.text = this.positions

        Glide.with(activity!!.baseContext)
            .load(image)
            .into(image_view)


        login.setOnClickListener {
            val MainChart = MainChart()
            val fm = fragmentManager
            val transaction: FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.contentContainer, MainChart, "MainChart")
            transaction.addToBackStack("MainChart")
            transaction.commit()
        }
        realtime.setOnClickListener {

            val mMessagesRef2 = mRootRef.child("data")
            val key = mMessagesRef.push().key
            val postValues: HashMap<String, Any> = HashMap()
            postValues["username"] = this.firstname.toString()
            postValues["text"] = this.positions.toString()
            val childUpdates: MutableMap<String, Any> = HashMap()
            childUpdates["$key"] = postValues
            mMessagesRef2.updateChildren(childUpdates)

            //ShowData
            val ShowData = show_data_realtime()
            val fm = fragmentManager
            val transaction: FragmentTransaction = fm!!.beginTransaction()

            transaction.replace(R.id.contentContainer, ShowData,"fragment_ShowData")
            transaction.addToBackStack("fragment_ShowData")
            transaction.commit()
            //

        }

        return view
    }

}
