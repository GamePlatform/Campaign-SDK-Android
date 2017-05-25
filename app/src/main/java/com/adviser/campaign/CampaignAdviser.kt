package com.adviser.campaign

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.webkit.WebView
import com.adviser.campaign.campaignsdk.R.id.popup
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.Buffer

/**
 * Created by Kairos on 2017. 5. 25..
 */

class CampaignAdviser{

    fun loadCampaign(popup : WebView){

        // enable javascript in webview
        popup.settings.javaScriptEnabled = true

        popup.loadUrl("file:///android_asset/popup.html")



    }

    // HTTP GET request using URL string
    // return request body
    private fun GET(url : String?) : String?{
        var result : StringBuilder = StringBuilder()

        if (!url.isNullOrEmpty()){
            var url : URL = URL(url)
            var con : HttpURLConnection = url.openConnection() as HttpURLConnection

            try {
                var input: InputStream = BufferedInputStream(con.inputStream)
                var reader: BufferedReader = BufferedReader(InputStreamReader(input))

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