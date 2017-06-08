package com.adviser.campaign.base.presenter

/**
 * Created by Kairos on 2017. 6. 8..
 */
interface BasePresenter<in VIEW: BaseView<*>> {

    // View Attach
    fun attachView(view: VIEW)

    // View Detach
    fun detachView()
}