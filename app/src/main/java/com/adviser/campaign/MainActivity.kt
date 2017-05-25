package com.advisor.campaign

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.advisor.campaign.campaignsdk.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        popup.settings.javaScriptEnabled = true
        popup.loadUrl("file:///android_asset/popup.html")

    }
}
