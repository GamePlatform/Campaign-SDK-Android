package com.adviser.campaign.view.main

import com.adviser.campaign.base.BasePresenter
import com.adviser.campaign.base.BaseView
import com.adviser.campaign.webkit.listener.OnCustomJavascriptListener

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
    fun getCampaignImageURL(): String
    fun getCampaignId(): String
  }
}