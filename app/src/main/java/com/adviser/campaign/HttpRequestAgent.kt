package com.adviser.campaign

import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

/**
 * Created by Kairos on 2017. 5. 26..
 */
// TODO Change Singletone and Static
class HttpRequestAgent {

    val reqRootUrl = "http://211.253.28.194:30022/api/campaign/locations/"  //TODO load from config file
    val locationId : Int

    constructor(locationId : Int) {
        this.locationId = locationId
    }

    fun reqParser(): CampaignsInfo {
        val urls: Array<String>?

        val images: JSONArray = JSONObject(GET(reqRootUrl + locationId)).getJSONArray("images")
        urls = Array(images.length(), { _ -> "" })

        for (i in 0..images.length() - 1) {
            urls[i] = (images[i] as JSONObject).getString("url")
        }

        Log.d("HttpRequestAgent", "reqParser/loadURLs Complete")
        for(url in urls) {
            Log.v("HttpRequestAgent", "reqParser/loadUrls Image URL: " + url)
        }
        return CampaignsInfo(urls)
    }

    // HTTP GET request using URL string
    // return request body
    private fun GET(url : String?) : String?{
        Log.d("HttpRequestAgent", "GET/Request Url Start URL: " + url)
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
        Log.d("HttpRequestAgent", "GET/Request URL Done URL: " + url)
        return result.toString()
    }
}