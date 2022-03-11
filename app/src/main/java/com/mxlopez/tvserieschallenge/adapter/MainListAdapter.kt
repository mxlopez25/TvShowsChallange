package com.mxlopez.tvserieschallenge.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.mxlopez.tvserieschallenge.R
import com.mxlopez.tvserieschallenge.models.Show

class MainListAdapter(private val list: List<Show>?) : RecyclerView.Adapter<MainListAdapter.ViewHolder>() {
    private lateinit var ctx: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.main_list_cell, parent, false)
        ctx = parent.context
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.seriesName.text = list?.get(position)!!.name ?: "N/A"
        holder.cellBody.setOnClickListener {
            Toast.makeText(ctx, "${list[position].name}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var seriesImage: ImageView = itemView.findViewById(R.id.ivSeriesImage)
        var seriesName: TextView = itemView.findViewById(R.id.tvSeriesName)
        var cellBody: ConstraintLayout = itemView.findViewById(R.id.clCellBody)

    }
}