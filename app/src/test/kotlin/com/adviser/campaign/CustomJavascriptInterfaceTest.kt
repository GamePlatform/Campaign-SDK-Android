package com.adviser.campaign

import com.adviser.campaign.model.HttpRequestAgent
import com.adviser.campaign.webkit.CustomJavascriptInterface
import org.junit.Assert.assertEquals
import org.testng.annotations.BeforeTest
import org.testng.annotations.Test

/**
 * Created by Kairos on 2017. 5. 27..
 */
class CustomJavascriptInterfaceTest {

  var wai: CustomJavascriptInterface? = null

  @BeforeTest
  fun setUp() {
    val agent = HttpRequestAgent(1)
  }

  @Test
  fun testGetImageUrl() {
    assertEquals(wai!!.getURL(), "http://wallpaper-gallery.net/images/image/image-13.jpg")
  }

}