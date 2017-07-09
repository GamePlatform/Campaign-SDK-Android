package com.adviser.campaign.view.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.adviser.campaign.campaignsdk.R
import com.adviser.campaign.constant.CampaignConst
import com.adviser.campaign.model.CampaignInfo
import com.adviser.campaign.model.CampaignInfoManager
import com.adviser.campaign.webkit.listener.OnCustomJavascriptListener

/**
 * Created by Kairos on 2017. 6. 8..
 */
//투명 액티비티( 팝업창 주변에 있는 것 ), Presenter
class CampaignActivity : AppCompatActivity(), CampaignDialogContract.Presenter {

  private val infoManager = CampaignInfoManager()
  private var currentCampaignInfo: CampaignInfo? = null
  private var curView: CampaignDialogContract.View? = null

  companion object{
    fun startActivity(context: Context, locationId: String){
      val extras = Bundle()
      extras.putString(CampaignConst.LOCATION_ID_INTENT_KEY, locationId)

      val intent = Intent(context, CampaignActivity::class.java)
      intent.putExtras(extras)
      context.startActivity(intent)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.campaign_activity)

    infoManager.loadCampaign(intent.extras.getString(CampaignConst.LOCATION_ID_INTENT_KEY))

    for (idx in 1..infoManager.getCampaignCount()) {
      loadNextCampaign()

      val dialog = CampaignDialogFragment()
      dialog.setPresenter(this)
      fragmentManager.beginTransaction().add(dialog, getCampaignId())
      dialog.showDialog()

      Log.d("clog/CampaignActivity", "onCreate/dialog: $dialog")
    }
  }

  // Presenter
  override fun start() {
    // TODO
  }

  override fun changeView(view: CampaignDialogContract.View) {
    this.curView = view
  }

  override fun getCustomJavascriptListener(): OnCustomJavascriptListener {
    return this
  }

  override fun loadNextCampaign() {
    currentCampaignInfo = infoManager.getNextCampaign()
  }

  override fun getCampaignImageURL(): String {
    if (currentCampaignInfo != null) {
      return currentCampaignInfo!!.url
    }
    return ""
  }

  override fun getCampaignId(): String {
    if (currentCampaignInfo != null) {
      return currentCampaignInfo!!.id
    }
    return ""
  }

  override fun getTemplateNum(): Int {
    if(currentCampaignInfo != null){
      return currentCampaignInfo!!.template_num
    }
    return 0
  }

  // OnCustomJavascriptListener
  override fun getImageURL(): String {
    return getCampaignImageURL()
  }
  override fun checkDontWatchDay() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun close() {
    fragmentManager.popBackStackImmediate()
  }
}