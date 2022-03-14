package com.mxlopez.tvserieschallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import com.mxlopez.tvserieschallenge.adapter.SeasonEpisodesAdapter
import com.mxlopez.tvserieschallenge.databinding.ActivityDetailsBinding
import com.mxlopez.tvserieschallenge.models.Episode
import com.mxlopez.tvserieschallenge.models.Season
import com.mxlopez.tvserieschallenge.repository.TvMazeApiRepository
import com.mxlopez.tvserieschallenge.utils.Constants
import kotlinx.coroutines.*

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var seasonList: MutableList<Season>
    private lateinit var episodeList: MutableList<Episode>
    private lateinit var seasonAdapter: SeasonEpisodesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val show = Constants.selectedShow!!

        binding.tvShowName.text = show.name
        binding.ivSeriesMainImage.load(show.image?.original)
        binding.tvShowGenres.text = show.genres?.joinToString(", ")
        binding.tvShowPremiered.text = "Premiered\n${show.premiered}"
        binding.tvShowEnded.text = "Ended\n${show.ended}"
        binding.tvShowSummary.text =
            show?.summary?.let { HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY).toString() }

        supportActionBar!!.title = resources.getString(R.string.details_label)

        val repo = TvMazeApiRepository()
        val r = CoroutineScope(Dispatchers.IO).launch {
            val seasons = async { repo.getShowSeasons(show.id.toString()) }
            val episodes = async { repo.getShowEpisodes(show.id.toString()) }
            seasonList = seasons.await().body()!!
            episodeList = episodes.await().body()!!

            seasonsUpdate()
        }

        CoroutineScope(Dispatchers.IO).launch {
            r.join()
        }
    }

    suspend fun seasonsUpdate() {
        withContext(Dispatchers.Main) {
            binding.tvNumberSeasons.text = "Number of Seasons: ${seasonList.size}"
            binding.tvNumberEpisode.text = "Number of Episodes: ${episodeList.size}"

            seasonAdapter = SeasonEpisodesAdapter(applicationContext, seasonList, episodeList)
            binding.rvSeasons.layoutManager = LinearLayoutManager(applicationContext)
            binding.rvSeasons.adapter = seasonAdapter
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Constants.selectedShow = null
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        Constants.selectedShow = null
    }
}