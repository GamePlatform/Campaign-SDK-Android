package com.adviser.campaign

import org.junit.Assert.assertEquals
import org.testng.annotations.BeforeTest
import org.testng.annotations.Test

/**
 * Created by Kairos on 2017. 5. 27..
 */
class WebAppInterfaceTest {

    var wai: WebAppInterface? = null

    @BeforeTest
    fun setUp() {
        val agent = HttpRequestAgent(1)
        wai = WebAppInterface(agent)
    }

    @Test
    fun testGetImageUrl() {
        assertEquals(wai!!.getImageUrl(), "http://wallpaper-gallery.net/images/image/image-13.jpg")
    }

}