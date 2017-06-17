package com.adviser.campaign

import com.adviser.campaign.model.HttpRequestAgent
import org.junit.Assert.assertEquals
import org.testng.annotations.Test


/**
 * Created by Kairos on 2017. 5. 27..
 */
class HttpRequestAgentTest {

  @Test
  fun constructorTest() {
    val hra: HttpRequestAgent = HttpRequestAgent(1)
    assertEquals(hra.reqRootUrl, "http://211.253.28.194:30022/api/campaign/locations/")
    assertEquals(hra.locationId, 1)
  }
}