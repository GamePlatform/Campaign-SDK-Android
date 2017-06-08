package com.adviser.campaign.webkit

import android.util.Log
import android.webkit.JavascriptInterface
import com.adviser.campaign.webkit.listener.OnCustomJavascriptListener

/**
 * Created by Kairos on 2017. 5. 25..
 */

class CustomJavascriptInterface {
    private var listener : OnCustomJavascriptListener? = null

    fun setOnCustomJavascriptListener(listener: OnCustomJavascriptListener) {
        this.listener = listener
    }

    @JavascriptInterface
    fun getURL() {
        if(listener != null) {
            listener!!.onGetURL()
            Log.d("clog/CustomJavascriptInterface", "getURL")
        }

    }

    @JavascriptInterface
    fun checkDontWatchDay() {
        if(listener != null) {
            listener!!.checkDontWatchDay()
            Log.d("clog/CustomJavascriptInterface", "checkDontWatchDay")
        }
    }

    @JavascriptInterface
    fun close() {
        if(listener != null) {
            listener!!.close()
            Log.d("clog/CustomJavascriptInterface", "close")
        }
    }
}