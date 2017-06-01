package com.adviser.campaign

import android.app.AlertDialog
import android.app.Dialog
import android.util.Log
import android.webkit.JavascriptInterface

/**
 * Created by Kairos on 2017. 5. 25..
 */

class WebAppInterface {

    var agent : HttpRequestAgent
    var dialog : AlertDialog.Builder

    constructor(HttpRequestAgent: HttpRequestAgent, dialog: AlertDialog.Builder) {
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
//        print("mhg no_more_to_see"+no_more_to_see)
    }

    @JavascriptInterface
    fun close() {
        //TODO
    }
}

class MyService {

}
