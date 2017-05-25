package com.adviser.campaign

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.adviser.campaign.campaignsdk.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var ca : CampaignAdviser = CampaignAdviser()
        ca.loadCampaign(popup)

    }
}
