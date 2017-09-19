package com.adviser.campaign.view.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.adviser.campaign.campaignsdk.R
import com.adviser.campaign.constant.CampaignConst
import com.adviser.campaign.model.CalenderHelper
import com.adviser.campaign.model.CampaignInfo
import com.adviser.campaign.model.CampaignInfoManager
import com.adviser.campaign.model.ExpiredCampaignInfo
import com.adviser.campaign.webkit.listener.OnCustomJavascriptListener

/**
 * Created by Kairos on 2017. 6. 8..
 */
//투명 액티비티( 팝업창 주변에 있는 것 ), Presenter
class CampaignActivity : AppCompatActivity(), CampaignDialogContract.Presenter {

  var currentCampaignInfo: CampaignInfo? = null
  var curView: CampaignDialogContract.View? = null

  // start Activity using Bundle
  companion object{
    var infoManager: CampaignInfoManager? = null
    fun startActivity(context: Context, locationId: String){

      infoManager = CampaignInfoManager(context)

      try {
        val extras = Bundle()
        extras.putString(CampaignConst.LOCATION_ID_INTENT_KEY, locationId)

        val intent = Intent(context, CampaignActivity::class.java)
        intent.putExtras(extras)
        context.startActivity(intent)

      } catch (e: Exception) {
        e.printStackTrace()

      }
    }
  }

  // onCreate
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.campaign_activity)

    // load selected location's campaign
    infoManager!!.loadCampaign(intent.extras.getString(CampaignConst.LOCATION_ID_INTENT_KEY))

    // add campaign dialog
    try {
      for (idx in 1..infoManager!!.getCampaignCount()) {
        loadNextCampaign()

        // create new dialog fragment
        val dialog = CampaignDialogFragment()
        dialog.setPresenter(this)

        // add dialog to fragment manager with campaign id
        fragmentManager.beginTransaction().add(dialog, getCampaignId())
        dialog.showDialog()

        Log.d("cl/CampaignActivity", "onCreate/dialog: $dialog")
      }
    } catch(e: Exception) {
      e.printStackTrace()
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
    currentCampaignInfo = infoManager!!.getNextCampaign()
  }

  override fun getCampaignImageURL(id: String): String {
//    if (currentCampaignInfo != null) {
//      return currentCampaignInfo!!.url
//    }
    val campaign = infoManager!!.getCampaign(id)
    if(campaign != null)
      return campaign.url
    return ""
  }

  override fun getCampaignTitle(id: String): String {
//    if (currentCampaignInfo != null) {
//      return currentCampaignInfo!!.title
//    }
    val campaign = infoManager!!.getCampaign(id)
    if(campaign != null)
      return campaign.title
    return ""
  }

  override fun getCampaignId(): String {
    if (currentCampaignInfo != null) {
      return currentCampaignInfo!!.id
    }
    return ""
  }


  override fun addExpiredCampaign(id: String) {
//    if(currentCampaignInfo != null) {
//      val expirationDate = CalenderHelper.AddDate(currentCampaignInfo!!.adExpireDay)
//      infoManager!!.addNewExpiredCampaign(ExpiredCampaignInfo(currentCampaignInfo!!.id, expirationDate))
//    }
    val campaign = infoManager!!.getCampaign(id)
    if(campaign != null) {
      val expirationDate = CalenderHelper.AddDate(campaign!!.adExpireDay)
      infoManager!!.addNewExpiredCampaign(ExpiredCampaignInfo(campaign!!.id, expirationDate))
    }
  }

  override fun saveExpiredCampaigns(id: String) {
    val campaign = infoManager!!.getCampaign(id)
    if(campaign!!.order == infoManager!!.getLastOrder())
      infoManager!!.saveExpiredCampaign()
  }

  override fun getTemplateNum(id: String): Int {
//    if(currentCampaignInfo != null){
//      return currentCampaignInfo!!.templateNum
//    }
    val campaign = infoManager!!.getCampaign(id)
    if(campaign != null)
      return campaign.template
    return 0
  }

  // OnCustomJavascriptListener
  override fun getImageURL(id: String): String {
    return getCampaignImageURL(id)
    return ""
  }
  override fun getExpireTitle(): String {
//    return getCampaignTitle()
    return ""
  }
  override fun checkDontWatchDay(id: String) {
    addExpiredCampaign(id)
  }

  override fun close(id: String) {
    saveExpiredCampaigns(id)
//    fragmentManager.popBackStackImmediate()
  }
}