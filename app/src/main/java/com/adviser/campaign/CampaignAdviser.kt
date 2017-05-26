package com.adviser.campaign

import android.webkit.WebView
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by Kairos on 2017. 5. 25..
 */

class CampaignAdviser{

    fun loadCampaign(popup: WebView, locationId: Int) {

        // enable javascript in webview
        popup.settings.javaScriptEnabled = true
        popup.addJavascriptInterface(WebAppInterface(RequestTemp.reqToServer()!!), "android")
        popup.loadUrl("file:///android_asset/popup.html")

    }

    // HTTP GET request using URL string
    // return request body
    private fun GET(url : String?) : String?{
        var result : StringBuilder = StringBuilder()

        // Null String check
        if (!url.isNullOrEmpty()){
            var url : URL = URL(url)
            var con : HttpURLConnection = url.openConnection() as HttpURLConnection

            try {
                var input: InputStream = BufferedInputStream(con.inputStream)
                var reader: BufferedReader = BufferedReader(InputStreamReader(input))

                // read http request line
                var line: String = reader.readLine()
                while (line != null) {
                    result.append(line)
                    line = reader.readLine()
                }
            } catch ( e : Exception) {
                e.printStackTrace()
            } finally {
                con.disconnect()
            }
        }

        return result.toString()
    }

}