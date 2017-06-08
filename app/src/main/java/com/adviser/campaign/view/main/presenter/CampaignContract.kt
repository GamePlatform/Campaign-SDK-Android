package com.adviser.campaign.view.main.presenter

import android.content.Context
import com.adviser.campaign.base.presenter.BasePresenter
import com.adviser.campaign.base.presenter.BaseView
import com.adviser.campaign.webkit.listener.OnCustomJavascriptListener

/**
 * Created by Kairos on 2017. 6. 8..
 */
interface CampaignContract {

    interface View : BaseView<Presenter> {
        fun checkDontWatchDay()
        fun close()
    }

    interface Presenter : BasePresenter<View>, OnCustomJavascriptListener {
        fun getCustomJavascriptListener() : OnCustomJavascriptListener?
    }
}