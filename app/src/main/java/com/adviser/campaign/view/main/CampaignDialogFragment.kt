package com.adviser.campaign.view.main

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import com.adviser.campaign.view.main.presenter.CampaignContract
import com.adviser.campaign.webkit.CampaignWebView

/**
 * Created by Kairos on 2017. 6. 8..
 */
class CampaignDialogFragment : DialogFragment(), CampaignContract.View {

    private var presenter : CampaignContract.Presenter? = null

    override fun onPresenter(presenter: CampaignContract.Presenter?) {
        this.presenter = presenter
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val url: String = arguments.getString("url")

        val cwv = CampaignWebView(activity).init().setUrl(url)

        return AlertDialog.Builder(activity)
                .setView(cwv)
                .setCancelable(false)
                .create()
    }

    override fun checkDontWatchDay() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun close() {
        this.dismiss()
    }

    fun showDialog(tag: String) {
        this.show(fragmentManager, tag)
    }
}