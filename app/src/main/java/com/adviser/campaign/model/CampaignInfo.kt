package com.adviser.campaign.model

/**
 * Created by Kairos on 2017. 6. 1..
 */

class CampaignInfo {
  val id: String
  val url: String

  constructor(id: String, url: String) {
    this.id = id
    this.url = url
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other?.javaClass != javaClass) return false

    other as CampaignInfo

    if (id != other.id) return false
    if (url != other.url) return false

    return true
  }

  override fun hashCode(): Int {
    var result = id.hashCode()
    result = 31 * result + url.hashCode()
    return result
  }

  override fun toString(): String {
    return "CampaignInfo(id='$id', url='$url')"
  }
}