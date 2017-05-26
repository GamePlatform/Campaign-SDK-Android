package com.adviser.campaign

import android.webkit.WebView
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

/**
 * Created by Kairos on 2017. 5. 25..
 */

class CampaignAdviser{

    fun loadCampaign(popup: WebView, locationId: Int) {

        // enable javascript in webview
        popup.settings.javaScriptEnabled = true
        val req: String? = GET("http://211.253.28.194:30022/api/campaign/locations/" + locationId)

        popup.addJavascriptInterface(WebAppInterface(req), "android")
        popup.loadUrl("file:///android_asset/popup.html")

    }

    // HTTP GET request using URL string
    // return request body
    private fun GET(url : String?) : String?{
        var result: String = ""

        // Null String check
        if (!url.isNullOrEmpty()){

            thread(start = true, block = {
                val con: HttpURLConnection = URL(url).openConnection() as HttpURLConnection

                try {
                    result = con.inputStream.bufferedReader().readText()
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    con.disconnect()
                }
            }).join()

        }

        return result.toString()
    }

}