package com.adviser.campaign.model

/**
 * Created by Kairos on 2017. 6. 1..
 */

class CampaignInfo {
    val id: Int
    val url: String

    constructor(id: Int, url: String) {
        this.id = id
        this.url = url
    }

    override fun toString(): String {
        return "id: $id, url: $url"
    }
}