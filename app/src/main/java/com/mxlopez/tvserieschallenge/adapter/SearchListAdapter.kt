package com.mxlopez.tvserieschallenge.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.mxlopez.tvserieschallenge.DetailsActivity
import com.mxlopez.tvserieschallenge.R
import com.mxlopez.tvserieschallenge.models.SearchedShow
import com.mxlopez.tvserieschallenge.models.Show
import com.mxlopez.tvserieschallenge.utils.Constants

class SearchListAdapter(
    private val list: List<SearchedShow>?,
    private val fav: MutableList<Show>?,
    private val addToFav: (Show) -> Unit,
    private val removeFromFav: (Show) -> Unit): RecyclerView.Adapter<SearchListAdapter.ViewHolder>() {
    private lateinit var ctx: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.main_list_cell, parent, false)
        ctx = parent.context
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val show = list?.get(position)!!
        val isFavorite = fav?.find { show.show?.name == it.name } != null

        holder.seriesName.text = show.show?.name ?: "N/A"
        holder.seriesImage.load(show.show?.image?.medium)

        if (isFavorite) {
            holder.favButton.setImageResource(R.drawable.ic_baseline_star_24)
        } else {
            holder.favButton.setImageResource(R.drawable.ic_baseline_star_border_24)
        }

        holder.cellBody.setOnClickListener {
            val intent = Intent(ctx, DetailsActivity::class.java)
            Constants.selectedShow = show.show
            ContextCompat.startActivity(ctx, intent, null)
        }

        holder.favButton.setOnClickListener {
            holder.favButton.visibility = View.INVISIBLE
            holder.favButton.setImageResource(R.drawable.ic_process_50)
            if (!isFavorite) {
                addToFav(show.show!!)
                Toast.makeText(ctx, "Added ${show.show?.name} to Favorites", Toast.LENGTH_SHORT).show()
                holder.favButton.setImageResource(R.drawable.ic_baseline_star_24)
            } else {
                removeFromFav(show.show!!)
                Toast.makeText(ctx, "Removed ${show.show?.name} from Favorites", Toast.LENGTH_SHORT)
                    .show()
                holder.favButton.setImageResource(R.drawable.ic_baseline_star_border_24)
            }
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var seriesImage: ImageView = itemView.findViewById(R.id.ivSeriesImage)
        var seriesName: TextView = itemView.findViewById(R.id.tvSeriesName)
        var cellBody: ConstraintLayout = itemView.findViewById(R.id.clCellBody)
        var favButton: ImageButton = itemView.findViewById(R.id.ibFavButton)
    }
}