package com.adviser.campaign.view.main

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
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
    val url: String = presenter!!.getCampaignImageURL(this.tag) //TODO PRIMARY
    Log.d("cl/DialogFragment", "imageURL: $url")

    val temp_num: Int = presenter!!.getTemplateNum(this.tag)

    val jsInterface = CustomJavascriptInterface(this.tag)
    jsInterface.setOnCustomJavascriptListener(presenter!!.getCustomJavascriptListener())

    var view = activity.layoutInflater.inflate(R.layout.dialog_complete, null)

    when(temp_num) {
      1->{view = activity.layoutInflater.inflate(R.layout.dialog_complete, null)}
      2->{view = activity.layoutInflater.inflate(R.layout.dialog_onlyclose, null)}
      3->{view = activity.layoutInflater.inflate(R.layout.dialog_bottom, null)}
      else->{view = activity.layoutInflater.inflate(R.layout.dialog_complete, null)}
    }

    val ca_web_view = view.findViewById(R.id.ca_web_view) as CampaignWebView
    ca_web_view.init()
    ca_web_view.url=CampaignConst.POPUP_HTML_URL
    ca_web_view.addJavascriptInterface(jsInterface, "campaign")
    ca_web_view.loadUrl("javascript:(function() { document.getElementById(\"img_view\").src = $url; })()");
//    ca_web_view.loadUrl("javascript:var x = document.getElementById('img_view').src = $url")

    val notShow = view.findViewById(R.id.check)
    val notShow_cb: CheckBox?
    if(notShow != null) notShow_cb = notShow as CheckBox
    else                notShow_cb = null
    notShow_cb?.text = presenter!!.getCampaignTitle(this.tag)

    val close_btn = view.findViewById(R.id.close)
    close_btn.setOnClickListener{
      if(notShow_cb != null && notShow_cb?.isChecked) {
        checkDontWatchDay()
      }
      close()
//      this.dismiss()
    }

    val builder = AlertDialog.Builder(activity)
        .setView(view)
        .setCancelable(false)

    Log.d("cl/DialogFragment", "onCreateDialog Complete")
    return builder.create()
  }

  fun showDialog() {
    val id = presenter!!.getCampaignId()
    this.show(fragmentManager, id)
    Log.d("cl/DialogFragment", "showDialog: $id")
  }

  override fun setImageURL(imageURL: String) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun checkDontWatchDay() {
    presenter!!.checkDontWatchDay(this.tag)
  }

  override fun close() {
    val id = presenter!!.getCampaignId()
    Log.d("cl/DialogFragment", "close: $id")
//    val fr: CampaignDialogFragment = fragmentManager.getFragment(Bundle(), id) as CampaignDialogFragment
//    fr.close()
    presenter!!.close(this.tag)
    this.dismiss()
  }
}
