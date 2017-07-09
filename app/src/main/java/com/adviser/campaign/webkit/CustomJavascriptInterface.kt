package com.adviser.campaign.webkit

import android.util.Log
import android.webkit.JavascriptInterface
import com.adviser.campaign.webkit.listener.OnCustomJavascriptListener

/**
 * Created by Kairos on 2017. 5. 25..
 */

class CustomJavascriptInterface {
  private var listener: OnCustomJavascriptListener? = null

  fun setOnCustomJavascriptListener(listener: OnCustomJavascriptListener) {
    this.listener = listener
  }

  @JavascriptInterface
  fun getImageURL() : String {
    var url = ""
    if (listener != null) {
      url = listener!!.getImageURL()
    }
    Log.d("cl/CustomJavascriptInterface", "getImageURL: $url")
    return url
  }

  @JavascriptInterface
  fun checkDontWatchDay() {
    if (listener != null) {
      listener!!.checkDontWatchDay()
      Log.d("cl/CustomJavascriptInterface", "checkDontWatchDay")
    }
  }

  @JavascriptInterface
  fun close() {
    if (listener != null) {
      listener!!.close()
      Log.d("cl/CustomJavascriptInterface", "close")
    }
  }
}