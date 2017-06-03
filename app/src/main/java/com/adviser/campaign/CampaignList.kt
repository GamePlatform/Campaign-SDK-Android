package com.adviser.campaign

import android.util.Log

/**
 * Created by Kairos on 2017. 6. 3..
 */

class CampaignList {
    private var list = arrayListOf<CampaignInfo>()
    private var cur: Int = 0

    fun init() {
        list.clear()
        cur = 0
    }

    fun add(ci: CampaignInfo) {
        list.add(ci)
        Log.d("clog/CampaignList", "add: $ci")
    }

    fun getNext(): CampaignInfo? {
        var ci: CampaignInfo? = null
        if (!isDone()) {
            ci = list[cur++]
        }
        Log.d("clog/CampaignList", "getNext: $ci")
        return ci
    }

    fun getMax(): Int {
        return list.size
    }

    fun isDone(): Boolean {
        val result = (cur >= list.size)
        Log.d("clog/CampaignList", "isDone: $result")
        return result
    }
}