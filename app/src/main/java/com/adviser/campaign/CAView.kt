package com.adviser.campaign

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebView

/**
 * Created by Kairos on 2017. 5. 30..
 */

class CAView : WebView {

    var ji: WebAppInterface = WebAppInterface()

    constructor(context: Context?) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun init(): CAView {
        val settings = settings
        settings.javaScriptEnabled = true
        settings.defaultTextEncodingName = "UTF-8"

        settings.cacheMode = WebSettings.LOAD_NO_CACHE

        // TODO fix WebAppInterface() --
        this.addJavascriptInterface(WebAppInterface(), "Campaign")
        this.loadUrl("https://www.smashingmagazine.com/wp-content/uploads/2015/06/10-dithering-opt.jpg")
        //this.loadUrl("file:///android_asset/popup.html")

        Log.d("clog/CAView", "init done")

        return this
    }

}