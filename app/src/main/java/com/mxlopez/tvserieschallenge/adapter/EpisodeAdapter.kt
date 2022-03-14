package com.mxlopez.tvserieschallenge.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mxlopez.tvserieschallenge.EpisodeDetailsActivity
import com.mxlopez.tvserieschallenge.R
import com.mxlopez.tvserieschallenge.models.Episode
import com.mxlopez.tvserieschallenge.utils.Constants
import java.lang.Exception

class EpisodeAdapter(val context: Context, val episodes: List<Episode>): RecyclerView.Adapter<EpisodeAdapter.ViewHolder>() {
    private lateinit var ctx: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.episode_cell, parent, false)
        ctx = parent.context
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvEpisodeName.text = "${episodes[position].name} -> ${episodes[position].number}"

        holder.clEpisodeCell.setOnClickListener {
            try {
                val intent = Intent(ctx, EpisodeDetailsActivity::class.java)
                intent.putExtra(Constants.EP_NAME, episodes[position].name)
                intent.putExtra(Constants.EP_SEASON, episodes[position].season.toString())
                intent.putExtra(Constants.EP_NUMBER, episodes[position].number.toString())
                intent.putExtra(Constants.EP_SUMMARY, episodes[position].summary)
                intent.putExtra(Constants.EP_IMAGE, episodes[position].image?.original.toString())
                ctx.startActivity(intent)
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }

    override fun getItemCount(): Int {
        return episodes.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvEpisodeName: TextView = itemView.findViewById(R.id.tvEpisodeName)
        val clEpisodeCell: ConstraintLayout = itemView.findViewById(R.id.clEpisodeCell)
    }
}