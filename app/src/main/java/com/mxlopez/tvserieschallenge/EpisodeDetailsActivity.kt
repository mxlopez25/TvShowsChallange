package com.mxlopez.tvserieschallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import coil.api.load
import com.mxlopez.tvserieschallenge.utils.Constants

class EpisodeDetailsActivity : AppCompatActivity() {
    private lateinit var tvEpName: TextView
    private lateinit var tvEpSeason: TextView
    private lateinit var tvEpNumber: TextView
    private lateinit var tvEpSummary: TextView
    private lateinit var ivEpImage: ImageView
    private lateinit var btnOk: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episode_details)
        val bundle = intent.extras

        tvEpName = findViewById(R.id.tvEpName)
        tvEpNumber = findViewById(R.id.tvEpNumber)
        tvEpSeason = findViewById(R.id.tvEpSeason)
        tvEpSummary = findViewById(R.id.tvEpSummary)
        ivEpImage = findViewById(R.id.ivEpImage)
        btnOk = findViewById(R.id.btnOk)

        val epSeason = bundle?.getString(Constants.EP_SEASON)?.let { "Season $it" }
        val epNumber = bundle?.getString(Constants.EP_NUMBER)?.let {"Episode $it"}
        val epName = bundle?.getString(Constants.EP_NAME)?.let { "Details $it" }

        supportActionBar!!.title = String.format(resources.getString(R.string.details_label), epName)

        if(bundle != null) {
            tvEpName.text = epName
            tvEpSummary.text =
                bundle.getString(Constants.EP_SUMMARY)
                    ?.let { HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY).toString() }
            tvEpSeason.text = epSeason
            tvEpNumber.text = epNumber
            ivEpImage.load(bundle.getString(Constants.EP_IMAGE))
        }

        btnOk.setOnClickListener {
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}