package com.adviser.campaign.view.main

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.util.Log
import com.adviser.campaign.campaignsdk.R
import com.adviser.campaign.constant.CampaignConst
import com.adviser.campaign.webkit.CampaignWebView
import com.adviser.campaign.webkit.CustomJavascriptInterface

/**
 * Created by Kairos on 2017. 6. 8..
 */
//팝업 프레임 - 팝업 창 내부 이미지는 웹뷰로 구현
class CampaignDialogFragment : DialogFragment(), CampaignDialogContract.View {
  private var presenter: CampaignDialogContract.Presenter? = null

  override fun setPresenter(presenter: CampaignDialogContract.Presenter) {
    Log.d("cl/DialogFragment", "setPresenter: $presenter")
    this.presenter = presenter
  }

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    Log.d("cl/DialogFragment", "onCreateDialog/Start")
    val url: String = presenter!!.getCampaignImageURL() //TODO PRIMARY
    Log.d("cl/DialogFragment", "imageURL: $url")

    val jsInterface = CustomJavascriptInterface()
    jsInterface.setOnCustomJavascriptListener(presenter!!.getCustomJavascriptListener())

    val view = activity.layoutInflater.inflate(R.layout.dialog_Complete, null)

    val ca_web_view = view.findViewById(R.id.ca_web_view) as CampaignWebView
    ca_web_view.init()
    ca_web_view.url=CampaignConst.POPUP_HTML_URL
    ca_web_view.addJavascriptInterface(jsInterface, "campaign")
    ca_web_view.loadUrl("javascript:Document.findElementById(\"img_view\").src = $url")

    val close_btn = view.findViewById(R.id.close)
    close_btn.setOnClickListener{
      this.dismiss()
    }

    val builder = AlertDialog.Builder(activity)
        .setView(view)
        .setCancelable(false)

    Log.d("clog/DialogFragment", "onCreateDialog Complete")
    return builder.create()
  }

  fun showDialog() {
    val id = presenter!!.getCampaignId()
    this.show(fragmentManager, id)
    Log.d("clog/DialogFragment", "showDialog: $id")
  }

  override fun setImageURL(imageURL: String) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun checkDontWatchDay() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun close() {
    val id = presenter!!.getCampaignId()
    Log.d("clog/DialogFragment", "close: $id")
    val fr: CampaignDialogFragment = fragmentManager.getFragment(Bundle(), id) as CampaignDialogFragment
    fr.close()
  }
}
