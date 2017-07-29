package com.adviser.campaign.model

import android.util.Log
import com.adviser.campaign.constant.CampaignConst
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
  private val AppId ="1"
  private val reqRootUrl = CampaignConst.ServerURL+"apps/$AppId/locations/"
  private var resendCount = 0
  private val resendThreshold = CampaignConst.RESEND_THRESHOLD

  fun getCampaignList(locationId: String, expiredCampaigns: List<ExpiredCampaignInfo>): ArrayList<CampaignInfo> {

    val campaigns = arrayListOf<CampaignInfo>()
    while (resendCount < resendThreshold) {
      try {

        var reqURL: String = "$reqRootUrl$locationId/campaigns?ec="
        expiredCampaigns?.forEach { reqURL += "${it.campaignId}&ec=" }


        val responseJson = GET(reqURL)

        if(!responseJson.isNullOrEmpty()) {
          val count = JSONObject(responseJson).getInt("count")
          val campaignArr = JSONObject(responseJson).getJSONArray("campaigns")

          for (i in 0..campaignArr.length() - 1) {
            val id = (campaignArr[i] as JSONObject).getString("campaign_id")
            val order = (campaignArr[i] as JSONObject).getInt("campaign_order")
            val url = (campaignArr[i] as JSONObject).getString("url")
            val adExpireDay = (campaignArr[i] as JSONObject).getInt("ad_expire_day")
            val expireTitle = (campaignArr[i] as JSONObject).getString("title")
            val templateNum = (campaignArr[i] as JSONObject).getInt("template")

            val ci = CampaignInfo(id, order, expireTitle, url, adExpireDay, templateNum)

            Log.v("clog/HttpRequestAgent", "reqParser/loadUrls Campaign: " + ci)
            campaigns.add(ci)
            Log.d("cl/HttpRequestAgent", "reqParser/loadURLs Complete")
          }
        }
      }
      catch (e: Exception) {
        e.printStackTrace()
        Log.d("cl/HttpRequestAgent", "reqParser/loadURLS Resend req")
      }
      finally {
        resendCount++
      }
    }

    // get Campaign List fail
    if (campaigns.size <= 0) {
      val ci1 = CampaignInfo("1", 1, "1", "https://cdn.pixabay.com/photo/2016/04/13/21/32/lamb-1327753_960_720.jpg", 1, 1)
      val ci2 = CampaignInfo("2", 2, "2", "http://livedoor.blogimg.jp/daynew/imgs/1/4/14ed705b.jpg", 1, 2)
      campaigns.add(ci1)
      campaigns.add(ci2)
      Log.d("cl/HttpRequestAgent", "reqParser/loadURLs loadFail")
    }

    return campaigns
  }

  // HTTP GET request using URL string
  // return request body
  fun GET(url: String?): String? {
    Log.d("cl/HttpRequestAgent", "GET/Request Url Start URL: " + url)
    var result: String = ""

    // Null String check
    if (!url.isNullOrEmpty()) {
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
    Log.d("cl/HttpRequestAgent", "GET/Request URL Done URL: " + url)
    return result
  }
}