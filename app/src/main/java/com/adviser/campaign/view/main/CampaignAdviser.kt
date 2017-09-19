package com.adviser.campaign.view.main

import android.content.Context
import com.adviser.campaign.model.DeviceUUID

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

  fun getDeviceId(context: Context) {
    val deviceId = DeviceUUID().getDeviceUUID(context)

    //TODO Register REST API Device ID
  }
}