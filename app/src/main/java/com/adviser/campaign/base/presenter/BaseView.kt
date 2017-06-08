package com.adviser.campaign.base.presenter

/**
 * Created by Kairos on 2017. 6. 8..
 */
interface BaseView<in P> {

    // Presenter On
    fun onPresenter(presenter: P?)
}