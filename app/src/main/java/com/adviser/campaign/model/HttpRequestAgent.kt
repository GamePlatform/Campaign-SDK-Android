package com.adviser.campaign.model

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

    fun reqParser(): CampaignList {
        val campaigns: CampaignList = CampaignList()

        val images: JSONArray = JSONObject(GET(reqRootUrl + locationId)).getJSONArray("images")

        for (i in 0..images.length() - 1) {
            val id = i
            val url = (images[i] as JSONObject).getString("url")
            val ci = CampaignInfo(id, url)
            Log.v("clog/HttpRequestAgent", "reqParser/loadUrls Campaign: " + ci)
            campaigns.add(ci)
        }

        Log.d("clog/HttpRequestAgent", "reqParser/loadURLs Complete")
        return campaigns
    }

    // HTTP GET request using URL string
    // return request body
    private fun GET(url : String?) : String?{
        Log.d("clog/HttpRequestAgent", "GET/Request Url Start URL: " + url)
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
        Log.d("clog/HttpRequestAgent", "GET/Request URL Done URL: " + url)
        return result.toString()
    }
}