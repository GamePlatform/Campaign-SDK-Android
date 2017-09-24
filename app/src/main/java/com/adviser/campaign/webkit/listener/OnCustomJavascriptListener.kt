package com.adviser.campaign.webkit.listener

/**
 * Created by Kairos on 2017. 6. 7..
 */
interface OnCustomJavascriptListener {
  fun getImageURL(id: String) : String
  fun getExpireTitle() : String
  fun checkDontWatchDay(id: String)
  fun close(id: String)
}