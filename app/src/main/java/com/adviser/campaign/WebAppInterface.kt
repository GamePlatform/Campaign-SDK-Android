package com.adviser.campaign

import android.app.AlertDialog
import android.util.Log
import android.webkit.JavascriptInterface

/**
 * Created by Kairos on 2017. 5. 25..
 */

class WebAppInterface {

    var agent : HttpRequestAgent
    var dialog : AlertDialog

    constructor(HttpRequestAgent: HttpRequestAgent, dialog: AlertDialog) {
        this.agent = HttpRequestAgent
        this.dialog = dialog
    }

    @JavascriptInterface
    fun getImageUrl(): String? {
        print(agent.urls!![agent.cur])
        // TODO add Exception process
        return agent.urls!![agent.cur++]
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