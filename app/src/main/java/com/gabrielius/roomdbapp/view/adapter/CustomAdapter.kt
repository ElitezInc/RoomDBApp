package com.gabrielius.roomdbapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.signature.ObjectKey
import com.gabrielius.roomdbapp.R
import com.gabrielius.roomdbapp.model.CustomModel
import com.gabrielius.roomdbapp.view.CustomListeners
import kotlinx.android.synthetic.main.item_sample.view.*

class CustomAdapter : RecyclerView.Adapter<CustomAdapter.ViewHolder>
{
    /**Main */
    var context : Context
    var customListeners : CustomListeners
    var list : MutableList<CustomModel> = mutableListOf()

    constructor(context : Context, customListeners : CustomListeners) : super()
    {
        this.context = context
        this.customListeners = customListeners
        setHasStableIds(false)
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : ViewHolder
    {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_sample, parent, false))
    }

    override fun onBindViewHolder(holder : ViewHolder, position : Int)
    {
        Glide.with(context).load(
            "https://picsum.photos/300/300"
        ).signature(ObjectKey(System.currentTimeMillis().toString()))
            .into(holder.imageView)

        holder.textView.setText(list[position].name)

        holder.buttonEdit.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view : View) {
                customListeners.onUpdate(list[position], position)
            }
        })

        holder.buttonDelete.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view : View) {
                customListeners.onDelete(list[position], position)
            }
        })
    }

    override fun getItemCount(): Int
    {
        return list.size
    }

    fun setItems(items : List<CustomModel>)
    {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        val buttonDelete = view.button_delete
        val buttonEdit = view.button_edit
        val textView = view.text_view
        val imageView = view.image_view
    }
}