package com.adviser.campaign

import android.app.AlertDialog
import android.content.Context
import android.webkit.WebView

/**
 * Created by Kairos on 2017. 5. 25..
 */

// Campaign Adviser
class CampaignAdviser{

    var context: Context? = null
    var campaigns: CampaignList? = null

    constructor(context: Context) {
        this.context = context
    }

    fun showCampaignAllInOnce() {
        // dialog setting
        for (i in 0..campaigns!!.getMax() - 1) {
            val alert = AlertDialog.Builder(context).create()

            // popup(webview) setting
            val popup = WebView(context)
            popup.settings.javaScriptEnabled = true
            popup.addJavascriptInterface(WebAppInterface(campaigns!!, alert), "campaign")
            popup.loadUrl("file:///android_asset/popup.html")

            alert.setView(popup)
            alert.setCancelable(false)
            alert.show()
        }
    }

    fun showCampaignOneByOne() {
        //TODO
    }

    // Load all Campaign in locationId
    fun loadCampaign(locationId: Int) {
        val agent: HttpRequestAgent = HttpRequestAgent(locationId)
        val campaigns = agent.reqParser()
        this.campaigns = campaigns
    }

}