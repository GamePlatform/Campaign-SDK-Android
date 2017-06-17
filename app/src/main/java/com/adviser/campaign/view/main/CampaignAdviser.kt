package com.adviser.campaign.view.main

/**
 * Created by Kairos on 2017. 5. 25..
 */

// Campaign Adviser
class CampaignAdviser {

//    fun showCampaignAllInOnce() {
  // dialog setting
//        for (i in 0..campaigns!!.getMax() - 1) {
//            val alert = AlertDialog.Builder(context).create()
//
//            // popup(webview) setting
//            val popup = WebView(context)
//            popup.settings.javaScriptEnabled = true
//            //popup.addJavascriptInterface(CustomJavascriptInterface(campaigns!!, alert), "campaign")
//            //popup.loadUrl("file:///android_asset/popup.html")
//            popup.loadUrl("https://www.smashingmagazine.com/wp-content/uploads/2015/06/10-dithering-opt.jpg")
//
//            alert.setCurView(popup)
//            alert.setCancelable(false)
//            alert.window.setLayout(500,500)
//            alert.show()
//        }
//    }

//    fun showSingleDialog(){
//        var ad = AlertDialog.Builder(context).create()
//        val pop : CampaignWebView = CampaignWebView(context).init()
////        val pop = WebView(context)
////        pop.loadUrl("file:///android_asset/popup.html")
//
//        ad.setCurView(pop)
//        ad.show()
//    }


  fun showCampaignOneByOne() {
    //TODO
  }

  fun showCampaign(location: Int) {
    CampaignDialogFragment().showDialog()
  }

  // Load all Campaign in locationId
//    fun loadCampaign(locationId: Int) {
//        val agent: HttpRequestAgent = HttpRequestAgent(locationId)
//        val campaigns = agent.reqParser()
//        this.campaigns = campaigns
//    }
}
