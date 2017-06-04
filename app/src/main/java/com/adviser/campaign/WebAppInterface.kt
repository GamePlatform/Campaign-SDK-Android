package com.adviser.campaign

import android.app.AlertDialog
import android.util.Log
import android.webkit.JavascriptInterface

/**
 * Created by Kairos on 2017. 5. 25..
 */

class WebAppInterface {

    var campaigns: CampaignList
    var dialog : AlertDialog

    constructor(campaigns: CampaignList, dialog: AlertDialog) {
        this.campaigns = campaigns
        this.dialog = dialog
        //test
    }

    @JavascriptInterface
    fun getURL(): String? {
        val url = campaigns.getNext()!!.url
        Log.d("clog/WebAppInterface", "getURL URL:" + url)
        return url
    }

    @JavascriptInterface
    fun noMoreToSee(no_more_to_see : Boolean) {
        //TODO
        Log.d("clog/WebAppInterface", "no_more_to_see: " + no_more_to_see)
        dialog.dismiss()
    }

    @JavascriptInterface
    fun close() {
        Log.d("clog/WebAppInterface", "close")
        dialog.dismiss()
    }
}