package com.adviser.campaign

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.webkit.WebView

/**
 * Created by Kairos on 2017. 5. 25..
 */

// Campaign Adviser
class CampaignAdviser{

//    fun loadCampaign(popup: WebView, locationId: Int) {
//
//        popup.settings.javaScriptEnabled = true
//        val agent: HttpRequestAgent = HttpRequestAgent(1)
//        agent.reqParser()
//        popup.addJavascriptInterface(agent, "campaign")
//        popup.loadUrl("file:///android_asset/popup.html")
//    }

    fun loadDynamicCampaign(context: Context, locationId: Int) {
        // dialog setting
//        var builder = AlertDialog.Builder(context)
//        builder.setNegativeButton("닫기", DialogInterface.OnClickListener { dialogInterface, i ->  }).show()
        val alert = AlertDialog.Builder(context).create()

        // popup(webview) setting
        val popup = WebView(context)
        val agent: HttpRequestAgent = HttpRequestAgent(locationId)
        agent.reqParser()
        popup.settings.javaScriptEnabled = true
        popup.addJavascriptInterface(WebAppInterface(agent, alert), "campaign")
        popup.loadUrl("file:///android_asset/popup.html")

        alert.setView(popup)
        alert.show()
    }
}