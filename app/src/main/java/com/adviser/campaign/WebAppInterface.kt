package com.adviser.campaign

import android.webkit.JavascriptInterface

/**
 * Created by Kairos on 2017. 5. 25..
 */

class WebAppInterface {

    var agent : HttpRequestAgent

    constructor(HttpRequestAgent: HttpRequestAgent) {
        this.agent = HttpRequestAgent
    }

    @JavascriptInterface
    fun getImageUrl(): String? {
        print(agent.urls!![agent.cur])
        // TODO add Exception process
        return agent.urls!![agent.cur++]
    }

    @JavascriptInterface
    fun noMoreToSee() {
        //TODO
    }
}