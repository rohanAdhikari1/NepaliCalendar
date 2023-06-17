package com.rohan.nepalicalendar.core

import com.rohan.nepalicalendar.core.date.Date
import com.rohan.nepalicalendar.core.date.DateUtils.getEnglishDate
import com.rohan.nepalicalendar.core.date.DateUtils.getNepaliDate
import java.util.Calendar

object NepaliDateConverter {

    fun convertAdToBs(adYear: Int, adMonth: Int, adDay: Int, showInEnglishLanguage: Boolean = true): String {
        return try {
            val cal = Calendar.getInstance()
            cal.set(adYear, adMonth-1, adDay)
            val nepDate = getNepaliDate(Date(cal))
            if (showInEnglishLanguage) {
                "${nepDate!!.year} ${LocalizationHelper.nepaliMonthNameInEngFont(nepDate!!.month)} ${nepDate.day}" //return eg: 2078 chaitra 22
            } else {
                val monthName = LocalizationHelper.nepaliMonthNameInNepaliFont(nepDate!!.month)
                val nepDate = "${nepDate.year} $monthName ${nepDate.day}"
                LocalizationHelper.toNepaliDigit(nepDate)    //return eg: २०७८ जेठ २२
            }
        } catch (e: Exception) {
            ""
        }

    }

    fun convertBsToAd(
        nepaliYear: Int,
        nepMonth: Int,
        nepDay: Int,
        showInEnglishLanguage: Boolean = true
    ): String {
        return try {
            val engDate = getEnglishDate(Date(nepaliYear, nepMonth, nepDay))
            return if (showInEnglishLanguage) {
                "${engDate!!.day} ${LocalizationHelper.englishMonthNameInEnglishFont(engDate.month)}, ${engDate.year}"
            } else {
                val monthName = LocalizationHelper.englishMonthNameInNepaliFont(engDate!!.month)
                LocalizationHelper.toNepaliDigit("${engDate.day} $monthName, ${engDate.year}")
            }

        } catch (e: Exception) {
            ""
        }
    }


}