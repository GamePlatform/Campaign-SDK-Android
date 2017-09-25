package com.d.campaign.view.main

import com.d.campaign.base.BasePresenter
import com.d.campaign.base.BaseView
import com.d.campaign.webkit.listener.OnCustomJavascriptListener

/**
 * Created by Kairos on 2017. 6. 8..
 */
//구현해야할 기능들을 간단히 보여줌 View쪽과 Presenter쪽
interface CampaignDialogContract {

  interface View : BaseView<Presenter> {
    fun setImageURL(imageURL: String)
    fun checkDontWatchDay()
    fun close()
  }


  interface Presenter : BasePresenter, OnCustomJavascriptListener {
    fun changeView(view: View)

    fun getCustomJavascriptListener(): OnCustomJavascriptListener

    fun loadNextCampaign()
    fun getCampaignImageURL(id: String): String
    fun getCampaignTitle(id: String): String
    fun getCampaignId(): String
    fun getTemplateNum(id: String): Int

    fun addExpiredCampaign(id: String)
    fun saveExpiredCampaigns(id: String)
  }
}