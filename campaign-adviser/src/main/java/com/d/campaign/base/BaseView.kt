package com.d.campaign.base

/**
 * Created by Kairos on 2017. 6. 8..
 */
interface BaseView<in P> {

  // Set Presenter
  fun setPresenter(presenter: P)
}