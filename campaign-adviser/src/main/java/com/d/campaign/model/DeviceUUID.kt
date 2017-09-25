package com.d.campaign.model

import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings
import android.telephony.TelephonyManager
import java.io.UnsupportedEncodingException
import java.util.*

/**
 * Created by GooSeng on 2017-06-25.
 */
class DeviceUUID {

    private val PREFS_FILE : String = "device_id.xml"
    private val PREFS_DEVICE_ID: String = "device_id"

    private var uuid: UUID? = null

    fun getDeviceUUID(context: Context): String? {
        if(uuid == null) {
//            synchronized(DeviceUUID.javaClass)
            if(uuid == null) {
                val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILE , 0)
                val id: String? = prefs.getString(PREFS_DEVICE_ID, null)
                if(id != null) {
                    //Use the dis previously computed and stored in the prefs file
                    uuid = UUID.fromString(id)
                }
                else {
                    val androidId: String = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
                    //Use the Android ID unless it's broken, in which case fallback on deviceid,
                    //unless it's not available, then fallback on a random number which we store
                    //to a prefs file
                    try {
                        if (!"9774d56d682e549c".equals(androidId)) {
                            uuid = UUID.nameUUIDFromBytes(androidId.toByteArray(Charsets.UTF_8));
                        } else {
                            val deviceId: String? = (context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).deviceId;
                            if(deviceId == null)
                                uuid = UUID.randomUUID()
                            else
                                uuid = UUID.nameUUIDFromBytes(deviceId.toByteArray(Charsets.UTF_8))
                        }
                    } catch (e: UnsupportedEncodingException) {
                        throw RuntimeException(e);
                    }
                    // Write the value out to the prefs file
                    prefs.edit().putString(PREFS_DEVICE_ID, uuid.toString() ).commit();
                }

            }
        }

        return uuid.toString() ?: null
    }


//    fun GetDevicesUUID(context: Context): String {
//
//      val os = System.getProperty("os.version"); // OS version
//
//      val api = android.os.Build.VERSION.SDK_INT      // API Level
//      val model= android.os.Build.MODEL            // Model
//      val device = android.os.Build.DEVICE           // Device
//      val product = android.os.Build.PRODUCT          // Product
//
//      if(api >= 9)
//        return android.provider.Settings.Secure.getString(context.contentResolver, android.provider.Settings.Secure.ANDROID_ID)
//
//      return ""
//    }

        // Need Runtime Permission Check
//    fun GetDevicesUUID(context: Context): String {
//
//
//      try {
//        val tm: TelephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
//
//        val tmDevice: String = tm.deviceId.toString()
//        val tmSerial: String = tm.simSerialNumber.toString()
//        val androidId: String = android.provider.Settings.Secure.getString(context.contentResolver, android.provider.Settings.Secure.ANDROID_ID)
//        val deviceUUID: UUID = UUID(androidId.hashCode() as Long, ((tmDevice.hashCode() as Long) shl 32) or (tmSerial.hashCode() as Long))
//
//        return deviceUUID.toString();
//
//      }
//      catch (e: Exception) {
//        e.printStackTrace()
//      }
//      return ""
//    }
}