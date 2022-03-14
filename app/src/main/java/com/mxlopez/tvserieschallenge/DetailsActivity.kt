package com.mxlopez.tvserieschallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.text.HtmlCompat
import coil.api.load
import com.mxlopez.tvserieschallenge.databinding.ActivityDetailsBinding
import com.mxlopez.tvserieschallenge.utils.Constants

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val show = Constants.selectedShow

        binding.tvShowName.text = show?.name
        binding.ivSeriesMainImage.load(show?.image?.original)
        binding.tvShowGenres.text = show?.genres?.joinToString(", ")
        binding.tvShowPremiered.text = "Premiered\n${show?.premiered}"
        binding.tvShowEnded.text = "Ended\n${show?.ended}"
        binding.tvShowSummary.text =
            show?.summary?.let { HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY).toString() }


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