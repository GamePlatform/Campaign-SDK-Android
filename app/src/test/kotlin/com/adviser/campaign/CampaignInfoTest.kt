package com.adviser.campaign

import com.adviser.campaign.model.CampaignInfo
import org.junit.Assert.assertEquals
import org.testng.annotations.Test

/**
 * Created by Kairos on 2017. 6. 3..
 */

class CampaignInfoTest {

    @Test
    fun getNextURLTest(){
        val ci = CampaignInfo(arrayOf("test1", "test2"))
        assertEquals(ci.getNextURL(), "test1")
        assertEquals(ci.getNextURL(), "test2")
    }

}