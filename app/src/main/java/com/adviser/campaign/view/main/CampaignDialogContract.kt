package com.adviser.campaign.view.main

import com.adviser.campaign.base.BasePresenter
import com.adviser.campaign.base.BaseView
import com.adviser.campaign.webkit.listener.OnCustomJavascriptListener

/**
 * Created by Kairos on 2017. 6. 8..
 */
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
    fun getCampaignImageURL(): String
    fun getCampaignId(): String
  }
}