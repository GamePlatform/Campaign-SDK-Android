package com.adviser.campaign.view.main

import android.content.Context

/**
 * Created by Kairos on 2017. 5. 25..
 */

// Campaign Adviser
class CampaignAdviser {

  private val campaignActivity = CampaignActivity
  private var appId = ""

  fun setAppId(appId : String) {
    this.appId = appId

  }

  fun loadCampaign(context: Context, locationId: String) {
    campaignActivity.startActivity(context = context, locationId = locationId)
  }

}