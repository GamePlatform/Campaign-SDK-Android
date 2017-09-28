package com.d.campaign.model

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*



/**
 * Created by GooSeng on 2017-06-23.
 */
class CalenderHelper {
    companion object {
        fun AddDate(value: Int) : Calendar
        {
            val calendar : Calendar = Calendar.getInstance()
            calendar.add(Calendar.DATE, value)
            return calendar
        }

        private val format = SimpleDateFormat("yyyyMMdd HH:mm:ss", Locale.KOREA)

        fun Parse(dateStr: String) : Calendar {
            var date: Date? = null
            try {
                date = format.parse(dateStr)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            val cal: Calendar = Calendar.getInstance()
            cal.time = date
            return cal
        }
        fun Parse(date: Calendar) : String = format.format(date.time)
    }
}