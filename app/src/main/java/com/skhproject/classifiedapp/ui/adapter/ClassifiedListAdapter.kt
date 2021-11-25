package com.skhproject.classifiedapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.skhproject.classifiedapp.R
import com.skhproject.classifiedapp.db.entity.Listing
import com.skhproject.classifiedapp.listeners.ClickListener
import com.squareup.picasso.Picasso

class ClassifiedListAdapter( private val listener: ClickListener) :
    RecyclerView.Adapter<ClassifiedListAdapter.ViewHolder>() {

    private var mList: List<Listing> = ArrayList<Listing>()

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_classified, parent, false)


        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = mList[position]
        holder.baseView.setOnClickListener {
            listener.itemClick(item)
        }

        holder.name.text = item.name
        holder.price.text = item.price
        holder.date.text = item.created_at

        Picasso.get().load(item.image_urls_thumbnails.get(0)).into(holder.thumbnail)

        // sets the image to the imageview from our itemHolder class
//        holder.imageView.setImageResource(ItemsViewModel.image)

        // sets the text to the textview from our itemHolder class
//        holder.textView.text = ItemsViewModel.text

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    fun updateContent(list: List<Listing>) {
        mList = list
        notifyDataSetChanged()
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {


        val baseView = itemView.findViewById<CardView>(R.id.parentCL)
        val name = itemView.findViewById<TextView>(R.id.name)
        val price = itemView.findViewById<TextView>(R.id.price)
        val date = itemView.findViewById<TextView>(R.id.date)
        val thumbnail = itemView.findViewById<ImageView>(R.id.thumbnail)
    }
}