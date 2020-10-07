package com.example.feedbook.ui.viewmodel.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.feedbook.R
import com.example.myfeed.data.Posts
import kotlinx.android.synthetic.main.feed_list.view.*
import java.util.*
import kotlin.collections.ArrayList

class FeedAdapter (private val feed : ArrayList<Posts>) :
    RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var eventName=itemView.findViewById<TextView>(R.id.event_name)
        var image=itemView.findViewById<ImageView>(R.id.imageView)
        var id=itemView.findViewById<TextView>(R.id.ID)
        var date=itemView.findViewById<TextView>(R.id.date)
        var views=itemView.findViewById<TextView>(R.id.views)
        var likes=itemView.findViewById<TextView>(R.id.likes)
        var shares=itemView.findViewById<TextView>(R.id.shares)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_list, parent, false);
        return FeedViewHolder(view);

    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val posts = feed[position]
        holder.eventName.text= posts.event_name
        holder.id.text= posts .id
        var milis=posts.event_date.toString()
        milis=milis+"000"
        holder.date.text=getDate(milis.toLong())
        holder.views.text=posts.views.toString()
        holder.likes.text=posts.likes.toString()
        holder.shares.text=posts.shares.toString()
        var url=feed[position].thumbnail_image

        url=url.substring(0, 4)+"s:"+url.substring(5)  //this is done because of error while fetching data through http
        Log.d("url", "edited url :-  " + url)          //http is made to https for successful image fetching

        Glide.with(holder.itemView.context).load(url).into(holder.image)
    }

    override fun getItemCount(): Int {
       return feed.size
    }

    fun getDate(milis: Long): String{
        var calender : Calendar = Calendar.getInstance()
        calender.timeInMillis=milis
        var date = "" + calender.get(Calendar.DAY_OF_MONTH)+ "-" + calender.get(Calendar.MONTH) + "-" + calender.get(
            Calendar.YEAR
        );
        return date

    }

    fun addData(post : List<Posts>){
     feed.addAll(post)
    }


}
