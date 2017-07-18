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

  val infoManager = CampaignInfoManager()
  var currentCampaignInfo: CampaignInfo? = null
  var curView: CampaignDialogContract.View? = null

  // start Activity using Bundle
  companion object{
    fun startActivity(context: Context, locationId: String){
      val extras = Bundle()
      extras.putString(CampaignConst.LOCATION_ID_INTENT_KEY, locationId)

      val intent = Intent(context, CampaignActivity::class.java)
      intent.putExtras(extras)
      context.startActivity(intent)
    }
  }

  // onCreate
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.campaign_activity)

    // load selected location's campaign
    infoManager.loadCampaign(intent.extras.getString(CampaignConst.LOCATION_ID_INTENT_KEY))

    // add campaign dialog
    for (idx in 1..infoManager.getCampaignCount()) {
      loadNextCampaign()

      // create new dialog fragment
      val dialog = CampaignDialogFragment()
      dialog.setPresenter(this)

      // add dialog to fragment manager with campaign id
      fragmentManager.beginTransaction().add(dialog, getCampaignId())
      dialog.showDialog()

      Log.d("cl/CampaignActivity", "onCreate/dialog: $dialog")
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
      return currentCampaignInfo!!.templateNum
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