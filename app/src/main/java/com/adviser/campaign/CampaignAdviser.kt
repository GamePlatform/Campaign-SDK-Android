package com.adviser.campaign

import android.app.AlertDialog
import android.content.Context
import android.webkit.WebView

/**
 * Created by Kairos on 2017. 5. 25..
 */

// Campaign Adviser
class CampaignAdviser{

    fun loadCampaign(popup: WebView, locationId: Int) {

        popup.settings.javaScriptEnabled = true
        val agent: HttpRequestAgent = HttpRequestAgent(1)
        agent.reqParser()
        popup.addJavascriptInterface(agent, "campaign")
        popup.loadUrl("file:///android_asset/popup.html")
    }

    fun loadDynamicCampaign(context: Context, locationId: Int) {

        // popup(webview) setting
        val popup = WebView(context)
        popup.settings.javaScriptEnabled = true
        val agent: HttpRequestAgent = HttpRequestAgent(1)
        agent.reqParser()
        popup.addJavascriptInterface(agent, "campaign")
        popup.loadUrl("file:///android_asset/popup.html")

        // dialog setting
        val dialog = AlertDialog.Builder(context)
        dialog.setView(popup)
        dialog.show()

    }

}