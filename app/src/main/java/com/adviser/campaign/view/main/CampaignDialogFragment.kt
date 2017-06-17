package com.adviser.campaign.view.main

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.util.Log
import com.adviser.campaign.constant.CampaignConst
import com.adviser.campaign.webkit.CampaignWebView
import com.adviser.campaign.webkit.CustomJavascriptInterface

/**
 * Created by Kairos on 2017. 6. 8..
 */
class CampaignDialogFragment : DialogFragment(), CampaignDialogContract.View {
  private var presenter: CampaignDialogContract.Presenter? = null

  override fun setPresenter(presenter: CampaignDialogContract.Presenter) {
    Log.d("clog/CampaignDialogFragment", "setPresenter: $presenter")
    this.presenter = presenter
  }

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    Log.d("clog/CampaignDialogFragment", "onCreateDialog Start")
    val url: String = presenter!!.getCampaignImageURL() //TODO PRIMARY
    Log.d("clog/CampaignDialogFragment", "imageURL: $url")

    val jsInterface = CustomJavascriptInterface()
    jsInterface.setOnCustomJavascriptListener(presenter!!.getCustomJavascriptListener())

    val cwv = CampaignWebView(activity).init().setUrl(CampaignConst.POPUP_HTML_URL)
    cwv.addJavascriptInterface(jsInterface, "campaign")
    cwv.loadUrl("javascript:Document.findElementById(\"img_view\").src = $url")

    val builder = AlertDialog.Builder(activity)
        .setView(cwv)
        .setCancelable(false)

    Log.d("clog/CampaignDialogFragment", "onCreateDialog Complete")
    return builder.create()
  }

  fun showDialog() {
    val id = presenter!!.getCampaignId()
    Log.d("clog/CampaignDialogFragment", "showDialog: $id")
    this.show(fragmentManager, id)
  }

  override fun setImageURL(imageURL: String) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun checkDontWatchDay() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun close() {
    this.dismiss()
  }
}