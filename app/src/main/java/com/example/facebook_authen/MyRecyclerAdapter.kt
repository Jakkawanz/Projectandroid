package com.example.facebook_authen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import androidx.fragment.app.FragmentManager
import org.json.JSONArray
import androidx.fragment.app.FragmentTransaction
import com.example.facebook_authen.R
import com.example.facebook_authen.detail

class MyRecyclerAdapter(
    context: Context,
    fm: FragmentManager,
    val dataSource: JSONArray
) : RecyclerView.Adapter<MyRecyclerAdapter.Holder>() {

    private val thiscontext : Context = context
    private val fragment: FragmentManager = fm

    class Holder(view : View) : RecyclerView.ViewHolder(view) {

        private val View = view;
        lateinit var layout : LinearLayout
        lateinit var titleTextView: TextView
        lateinit var titleTextView2: TextView
        lateinit var titleTextView3: TextView
        lateinit var image: ImageView

        fun Holder(){
            layout = View.findViewById<View>(R.id.recyview_layout) as LinearLayout
            titleTextView = View.findViewById<View>(R.id.positions) as TextView
            titleTextView2 = View.findViewById<View>(R.id.firstname) as TextView
            titleTextView3 = View.findViewById<View>(R.id.lastname) as TextView
            image = View.findViewById<View>(R.id.image) as ImageView
        }
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.recy_data, parent, false))
    }

    override fun getItemCount(): Int {
        return dataSource.length()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.Holder()

        holder.titleTextView.setText( dataSource.getJSONObject(position).getString("positions").toString() )
        holder.titleTextView2.setText( dataSource.getJSONObject(position).getString("firstname").toString() )
        holder.titleTextView3.setText( dataSource.getJSONObject(position).getString("lastname").toString() )

        Glide.with(thiscontext)
            .load(dataSource.getJSONObject(position).getString("image").toString())
            .into(holder.image)

        holder.layout.setOnClickListener(){
            Toast.makeText(thiscontext,holder.titleTextView.text.toString(),Toast.LENGTH_SHORT).show()

            val image:String = dataSource.getJSONObject(position).getString("image").toString()
            val firstname:String = dataSource.getJSONObject(position).getString("firstname").toString()
            val lastname:String = dataSource.getJSONObject(position).getString("lastname").toString()
            val positions:String = dataSource.getJSONObject(position).getString("positions").toString()

            val detail = detail().newInstance(image,firstname,lastname,positions)
            val transaction : FragmentTransaction = fragment!!.beginTransaction()
            transaction.replace(com.example.facebook_authen.R.id.contentContainer,detail,"detail")
            transaction.addToBackStack("detail")
            transaction.commit()
        }
    }
}
