package com.adviser.campaign

import android.webkit.WebView

/**
 * Created by Kairos on 2017. 5. 25..
 */

// Campaign Adviser
class CampaignAdviser{

    fun loadCampaign(popup: WebView, locationId: Int) {

        popup.settings.javaScriptEnabled = true
        popup.addJavascriptInterface(WebAppInterface(HttpRequestAgent(1)), "campaign")
        popup.loadUrl("file:///android_asset/popup.html")

    }

}