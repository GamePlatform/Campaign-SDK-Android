package com.adviser.campaign

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import com.adviser.campaign.campaignsdk.R
import com.adviser.campaign.view.main.CampaignActivity
import com.adviser.campaign.view.main.CampaignAdviser

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // Debugger Enable
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      WebView.setWebContentsDebuggingEnabled(true)
    }

    val ca = CampaignAdviser()
    ca.getDeviceId(this)
    ca.setAppId("1")
    ca.loadCampaign(this, "main")

  }
}
