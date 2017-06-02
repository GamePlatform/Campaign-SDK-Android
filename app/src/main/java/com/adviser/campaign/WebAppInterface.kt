package com.adviser.campaign

import android.app.AlertDialog
import android.util.Log
import android.webkit.JavascriptInterface

/**
 * Created by Kairos on 2017. 5. 25..
 */

class WebAppInterface {

    var campaignsInfo: CampaignsInfo
    var dialog : AlertDialog

    constructor(campaignsInfo: CampaignsInfo, dialog: AlertDialog) {
        this.campaignsInfo = campaignsInfo
        this.dialog = dialog
    }

    @JavascriptInterface
    fun getURL(): String? {
        val url = campaignsInfo.getNextURL()
        Log.d("WebAppInterface","getURL URL:" + url)
        // TODO add Exception process
        return url
    }

    @JavascriptInterface
    fun noMoreToSee(no_more_to_see : Boolean) {
        //TODO
        Log.d("WebAppInterface","no_more_to_see: " + no_more_to_see)
        dialog.dismiss()
    }

    @JavascriptInterface
    fun close() {
        dialog.dismiss()
    }
}