package com.d.campaign.webkit

import android.util.Log
import android.webkit.JavascriptInterface
import com.d.campaign.webkit.listener.OnCustomJavascriptListener

/**
 * Created by Kairos on 2017. 5. 25..
 */

class CustomJavascriptInterface {
  private var listener: OnCustomJavascriptListener? = null
  private var id: String

  constructor(id: String) {
    this.id = id
  }

  fun setOnCustomJavascriptListener(listener: OnCustomJavascriptListener) {
    this.listener = listener
  }

  @JavascriptInterface
  fun getImageURL() : String {
    var url = ""
    if (listener != null) {
      url = listener!!.getImageURL(id)
    }
    Log.d("cl/CustomJSInterface", "getImageURL: $url")
    return url
  }

  @JavascriptInterface
  fun getTitle() : String {
    var title = ""
    if (listener != null) {
      title = listener!!.getExpireTitle()
    }
    Log.d("clog/CustomJSInterface", "getTitle: $title")
    return title
  }

  @JavascriptInterface
  fun checkDontWatchDay() {
    if (listener != null) {
      listener!!.checkDontWatchDay(id)
      Log.d("cl/CustomJSInterface", "checkDontWatchDay")
    }
  }

  @JavascriptInterface
  fun close() {
    if (listener != null) {
      listener!!.close(id)
      Log.d("cl/CustomJSInterface", "close")
    }
  }
}