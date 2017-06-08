package com.adviser.campaign.view.main.presenter

import com.adviser.campaign.base.presenter.AbstractPresenter
import com.adviser.campaign.webkit.listener.OnCustomJavascriptListener

/**
 * Created by Kairos on 2017. 6. 8..
 */
class CampaignPresenter : AbstractPresenter<CampaignContract.View>(), CampaignContract.Presenter {

    // Abstract Presenter override
    override fun attachView(view: CampaignContract.View) {
        super.attachView(view)
        view.onPresenter(this)
    }

    // CampaignContract.Presenter override
    override fun getCustomJavascriptListener(): OnCustomJavascriptListener? {
        return this
    }


    override fun checkDontWatchDay() {
        view?.checkDontWatchDay()
    }

    override fun close() {
        view?.close()
    }

}