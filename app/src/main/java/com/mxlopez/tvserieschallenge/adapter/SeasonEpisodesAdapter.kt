package com.mxlopez.tvserieschallenge.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mxlopez.tvserieschallenge.R
import com.mxlopez.tvserieschallenge.models.Episode
import com.mxlopez.tvserieschallenge.models.Season

class SeasonEpisodesAdapter(
    val context: Context,
    val seasons: MutableList<Season>,
    val episodes: MutableList<Episode>
) : RecyclerView.Adapter<SeasonEpisodesAdapter.ViewHolder>() {
    private lateinit var episodeAdapter: EpisodeAdapter
    private lateinit var ctx: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.season_episodes_cell, parent, false)
        ctx = parent.context
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val season = seasons[position]
        holder.tvSeasonNumber.text = "Season #${seasons[position].number}"

        val episodes = episodes.filter { it.season == seasons[position].number }

        episodeAdapter = EpisodeAdapter(ctx, episodes)
        holder.rvEpisodes.layoutManager = LinearLayoutManager(ctx)
        holder.rvEpisodes.adapter = episodeAdapter

    }

    override fun getItemCount(): Int {
        return seasons.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSeasonNumber: TextView = itemView.findViewById(R.id.tvSeriesNumber)
        val rvEpisodes: RecyclerView = itemView.findViewById(R.id.rvEpisodes)
    }
}