package com.adviser.campaign

import android.app.AlertDialog
import android.content.Context
import android.webkit.WebView

/**
 * Created by Kairos on 2017. 5. 25..
 */

// Campaign Adviser
class CampaignAdviser{

    var alertList = mutableListOf<AlertDialog?>()

    fun showCampaignAllInOnce() {
        //TODO
    }

    fun showCampaignOneByOne() {
        //TODO
    }

    // Load all Campaign in locationId
    fun loadCampaign(context: Context, locationId: Int) {
        val agent: HttpRequestAgent = HttpRequestAgent(locationId)
        val ci = agent.reqParser()

//        while (!ci.isDone()) {
//            alertList.add(makeSingleCampaignDialog(context, ci))
//        }
        makeSingleCampaignDialog(context, ci)!!.show()
    }

    // campaign make dialog
    fun makeSingleCampaignDialog(context: Context, campaignsInfo: CampaignsInfo): AlertDialog? {
        // dialog setting
        val alert = AlertDialog.Builder(context).create()

        // popup(webview) setting
        val popup = WebView(context)
        popup.settings.javaScriptEnabled = true
        popup.addJavascriptInterface(WebAppInterface(campaignsInfo, alert), "campaign")
        popup.loadUrl("file:///android_asset/popup.html")

        alert.setView(popup)
        //alert.show()
        return alert
    }
}