package com.adviser.campaign.base.presenter

/**
 * Created by Kairos on 2017. 6. 8..
 */
abstract class AbstractPresenter<VIEW : BaseView<*>> : BasePresenter<VIEW> {

    protected var view : VIEW? = null
        private set

    override fun attachView(view: VIEW) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    fun isViewAttached(): Boolean {
        return view != null
    }
}