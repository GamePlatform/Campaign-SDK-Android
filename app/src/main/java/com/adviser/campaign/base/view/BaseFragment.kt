package com.adviser.campaign.base.view

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import com.adviser.campaign.base.presenter.BasePresenter
import com.adviser.campaign.base.presenter.BaseView

/**
 * Created by Kairos on 2017. 6. 8..
 */

abstract class BaseFragment<P : BasePresenter<*>>() : Fragment(), BaseView<P> {

    protected var presenter: P? = null
        private set

    override fun onPresenter(presenter: P?) {
        this.presenter = presenter
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(getLayout(), container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    abstract fun getLayout(): Int @LayoutRes

    override fun onDestroy() {
        super.onDestroy()

        presenter?.detachView()
    }
}