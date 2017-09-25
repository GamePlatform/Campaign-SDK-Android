package com.d.campaign.model

import android.content.Context
import android.util.Log

/**
 * Created by Kairos on 2017. 6. 11..
 */
//데이터 계산 및 처리
class CampaignInfoManager {

  private var agent = HttpRequestAgent()
  private var propertyAgent: PropertyAgent
  private var locationId: String = ""
  private var list = arrayListOf<CampaignInfo>()
  private var cur: Int = 0
  private var expiredCampaigns = arrayListOf<ExpiredCampaignInfo>()
  private var lastOrder: Int = 0

  constructor(context: Context) {
    propertyAgent = PropertyAgent(context)
  }

  fun loadCampaign(locationId: String) {
    Log.d("cl/CampaignInfoManager", "loadCampaign/Start")
    this.locationId = locationId

    expiredCampaigns = propertyAgent.getExcludedCampaignList(locationId)
    this.list = agent.getCampaignList(locationId, expiredCampaigns)
    this.list.sortByDescending { it.order } //TODO
    this.lastOrder = this.list.first().order
    Log.d("cl/CampaignInfoManager", "loadCampaign/Complete")
  }

  fun reset() {
    cur = 0;
  }

  fun getLastOrder(): Int {
    return lastOrder
  }

  fun getNextCampaign(): CampaignInfo? {
    val ci: CampaignInfo? = list[cur++]
    Log.d("cl/CampaignInfoManager", "getNextCampaign/next: $ci")
    return ci
  }

  fun getCampaign(idx: Int): CampaignInfo {
    val campaign = list[idx]
    Log.d("cl/CampaignInfoManager", "getCampaign/idx: $idx, campaign: $campaign")
    return campaign
  }

  fun getCampaign(id: String): CampaignInfo? {
    val campaign = list.find { it.id == id }
    Log.d("cl/CampaignInfoManager", "getCampaign/id: $id, campaign: $campaign")
    return campaign
  }

  fun getCampaignCount(): Int {
    val size = list.size
    Log.d("cl/CampaignInfoManager", "getCampaignCount/next: $size")
    return size
  }

  fun addNewExpiredCampaign(newItem: ExpiredCampaignInfo) {
    expiredCampaigns.add(newItem)
    Log.d("cl/CampaignInfoManager", "addNewExpiredCampaign/newItem: $newItem")
  }

  fun saveExpiredCampaign() {
    Log.d("cl/CampaignInfoManager", "saveExpiredCampaign Start")
    propertyAgent.storeExcludedCampaignList(locationId, expiredCampaigns)
    Log.d("cl/CampaignInfoManager", "saveExpiredCampaign End")
  }

}