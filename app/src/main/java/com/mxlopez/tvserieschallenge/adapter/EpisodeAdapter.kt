package com.mxlopez.tvserieschallenge.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.mxlopez.tvserieschallenge.R
import com.mxlopez.tvserieschallenge.models.Episode

class EpisodeAdapter(val context: Context, val episodes: List<Episode>): RecyclerView.Adapter<EpisodeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.episode_cell, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ep = episodes[position]
        holder.tvEpisodeName.text = "${ep.name} -> ${ep.number}"
    }

    override fun getItemCount(): Int {
        return episodes.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvEpisodeName: TextView = itemView.findViewById(R.id.tvEpisodeName)
        val clEpisodeCell: ConstraintLayout = itemView.findViewById(R.id.clEpisodeCell)
    }
}