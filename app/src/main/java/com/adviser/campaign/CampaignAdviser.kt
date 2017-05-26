package com.adviser.campaign

import android.webkit.WebView

/**
 * Created by Kairos on 2017. 5. 25..
 */

class CampaignAdviser{

    fun loadCampaign(popup: WebView, locationId: Int) {

        // enable javascript in webview
        popup.settings.javaScriptEnabled = true
        popup.addJavascriptInterface(WebAppInterface(HttpRequestAgent(1)), "android")
        popup.loadUrl("file:///android_asset/popup.html")

    }


}