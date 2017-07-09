package com.adviser.campaign.webkit

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebView

/**
 * Created by Kairos on 2017. 5. 30..
 */

class CampaignWebView : WebView {

  //constructor
  constructor(context: Context?) : super(context)

  constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
  constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

  // init web view
  fun init(): CampaignWebView {
    val settings = settings
    settings.javaScriptEnabled = true
    settings.defaultTextEncodingName = "UTF-8"

    settings.cacheMode = WebSettings.LOAD_NO_CACHE

    Log.d("cl/CAView", "init done")

    return this
  }

  fun setUrl(url: String): CampaignWebView {
    this.loadUrl(url)
    return this
  }
}