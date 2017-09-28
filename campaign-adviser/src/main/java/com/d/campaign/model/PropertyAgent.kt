package com.d.campaign.model

import android.content.Context
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

/**
 * Created by GooSeng on 2017-06-23.
 */
class PropertyAgent {

    val context: Context
    val filePath: File
    val excludedCampaignFileName: String =  "Campaign_Excluded.properties"

    constructor(context: Context) {
        this.context = context
        filePath = context.filesDir
    }


    private fun writeProperty(key: String, value: String, path: String) {
        val file = File(filePath, path)

        try {
            if (!file.exists()) {
                file.createNewFile()
            }

            val fos = FileOutputStream(file)

            val props = Properties()
            props.setProperty(key, value)
            props.store(fos, "$key: $value")

            Log.d("clog/PropertyAgent", " key: $key, value: $value")
        } catch (e: IOException) {
            e.printStackTrace()

            Log.e("clog/PropertyAgent", " writeProperty: " + e.message)
            Log.getStackTraceString(e)
        }
    }
    private fun readProperty(key: String, path: String): String {
        val file = File(filePath, path)
        var value: String = ""

        if(file.exists()) {
            try {
                val fis = FileInputStream(file)

                //Property 데이터 읽기
                val props = Properties()
                props.load(fis)
                value = props.getProperty(key, "")  //(key , default value)

                Log.d("clog/PropertyAgent", " key: $key, value: $value")
            } catch (e: IOException) {
                e.printStackTrace()

                Log.e("clog/PropertyAgent", " readProperty: " + e.message)
                Log.getStackTraceString(e)
            }
        }

        return value
    }

    //

    fun getExcludedCampaignList(locationID: String): ArrayList<ExpiredCampaignInfo> {

        val result = ArrayList<ExpiredCampaignInfo>()

        val readResult = readProperty(locationID, excludedCampaignFileName)

        if(readResult.isNotEmpty())
        {
            val campaigns: JSONArray = JSONObject(readResult).getJSONArray("campaigns")

            for (i in 0..campaigns.length() - 1) {
                val camId = (campaigns[i] as JSONObject).getString("campaignID")
                val expirationDateStr = (campaigns[i] as JSONObject).getString("expirationDate")

                val expirationDate: Calendar =  CalenderHelper.Parse(expirationDateStr)

                if(expirationDate.after(Calendar.getInstance())) {
                    result.add(ExpiredCampaignInfo(camId, expirationDate))
                }
            }
        }

        return result
    }
    fun storeExcludedCampaignList(locationID: String, campaigns: ArrayList<ExpiredCampaignInfo>) {
        val jsonArr: JSONArray = JSONArray()

        for (i in 0..campaigns.size - 1) {
            val jsonObj: JSONObject = JSONObject()
            jsonObj.put("campaignID", campaigns[i].campaignId)
            jsonObj.put("expirationDate", CalenderHelper.Parse(campaigns[i].expirationDate))

            jsonArr.put(i, jsonObj)
        }

        val obj = JSONObject()
        obj.put("campaigns", jsonArr)

        writeProperty(locationID, obj.toString(), excludedCampaignFileName)
    }

}