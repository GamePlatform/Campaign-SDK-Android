package com.adviser.campaign

import android.util.Log

/**
 * Created by Kairos on 2017. 6. 1..
 */

class CampaignsInfo {
    val urls: Array<String>
    var cur: Int

    constructor(urls: Array<String>) {
        this.urls = urls
        this.cur = 0
    }

    fun isDone(): Boolean {
        return cur >= urls.size
    }

    fun getNextURL(): String {
        val url = urls[cur++]
        Log.d("CampaignsInfo", "getNextURL")
        return urls[cur++]
    }
}