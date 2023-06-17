package com.rohan.nepalicalendar.core.date

import java.util.Calendar

object DateUtils {
    val HEADER_SUN = "S"
    val HEADER_MON = "M"
    val HEADER_TUE = "T"
    val HEADER_WED = "W"
    val HEADER_THU = "T"
    val HEADER_FRI = "F"
    val HEADER_SAT = "S"
    val MONTH_NAMES = arrayOf(
        "",
        "Baisakh",
        "Jestha",
        "Ashar",
        "Shrawan",
        "Bhadra",
        "Ashoj",
        "Kartik",
        "Mangshir",
        "Poush",
        "Magh",
        "Falgun",
        "Chaitra"
    )
    val MONTH_NAMES_MAPPED = arrayOf(
        "",
        "Apr / May",
        "May / Jun",
        "Jun / Jul",
        "Jul / Aug",
        "Aug / Sep",
        "Sep / Oct",
        "Oct / Nov",
        "Nov / Dec",
        "Dec / Jan",
        "Jan / Feb",
        "Feb / Mar",
        "Mar / Apr"
    )
    val MONTH_NAMES_NEP = arrayOf(
        "",
        "बैशाख",
        "जेष्ठ",
        "आषाढ",
        "श्रावण",
        "भाद्र",
        "आश्विन",
        "कार्तिक",
        "मंसिर",
        "पौष",
        "माघ",
        "फाल्गुन",
        "चैत्र"
    )
    val NUMBER_NEP = arrayOf("०", "१", "२", "३", "४", "५", "६", "७", "८", "९")

    val NUMBER_ENG: HashMap<String?, Int?> = object : HashMap<String?, Int?>() {
        init {
            put("०", 0)
            put("१", 0)
            put("२", 0)
            put("३", 0)
            put("४", 0)
            put("५", 0)
            put("६", 0)
            put("७", 0)
            put("८", 0)
            put("९", 0)
        }
    }

    val MONTH_NAMES_AD = arrayOf(
        "",
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"
    )
    val WEEK_DAY_NAMES =
        arrayOf("", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")

    val startNepaliYear = 2000

    val startEnglishDate: Date = Date(1943, 4, 14)

    val data = arrayOf(
        intArrayOf(30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31),
        intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31),
        intArrayOf(30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31),
        intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31),
        intArrayOf(31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31),
        intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31),
        intArrayOf(31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30),
        intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31),
        intArrayOf(31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30),
        intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31),
        intArrayOf(31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30),
        intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31),
        intArrayOf(31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31),
        intArrayOf(30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31),
        intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 31, 32, 31, 32, 30, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31),
        intArrayOf(30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31),
        intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31),
        intArrayOf(30, 32, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31),
        intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31),
        intArrayOf(31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30),
        intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31),
        intArrayOf(31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30),
        intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31),
        intArrayOf(31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30),
        intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31),
        intArrayOf(31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30),
        intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31),
        intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 31, 32, 31, 32, 30, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31),
        intArrayOf(30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31),
        intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31),
        intArrayOf(30, 32, 31, 32, 31, 31, 29, 30, 29, 30, 29, 31),
        intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31),
        intArrayOf(31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31),
        intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31),
        intArrayOf(31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30),
        intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31),
        intArrayOf(31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30),
        intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31),
        intArrayOf(31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30),
        intArrayOf(31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30),
        intArrayOf(31, 31, 32, 32, 31, 30, 30, 30, 29, 30, 30, 30),
        intArrayOf(30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30),
        intArrayOf(31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30),
        intArrayOf(31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30),
        intArrayOf(31, 32, 31, 32, 30, 31, 30, 30, 29, 30, 30, 30),
        intArrayOf(30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30),
        intArrayOf(31, 31, 32, 31, 31, 31, 30, 30, 29, 30, 30, 30),
        intArrayOf(30, 31, 32, 32, 30, 31, 30, 30, 29, 30, 30, 30),
        intArrayOf(30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30),
        intArrayOf(30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30)
    )

    // Starting Nepali year that this database storing date till.
    val endNepaliYear: Int =
        startNepaliYear + data.size

    /**
     * Get {@return number of days} in given {@param year} and {@param month}.
     */
    fun getNumDays(year: Int, month: Int): Int {
        return data.get(year - startNepaliYear)
            .get(month - 1)
    }

    /**
     * Get {@return number of years} that this database stores.
     */
    fun getNumYears(): Int {
        return data.size
    }

    /**
     * Convert English date to Nepali date.
     *
     * @param engDate English date to convert from.
     * @return Corresponding Nepali date.
     */
    fun getNepaliDate(engDate: Date?): Date? {
        var days: Int = startEnglishDate.getDaysTill(engDate!!)
        for (i in 0 until getNumYears()) {
            for (j in 0..11) {
                days -= if (days > data.get(i)
                        .get(j)
                ) data.get(i)
                    .get(j) else return Date(
                    i + startNepaliYear,
                    j + 1,
                    days
                )
            }
        }
        return null
    }

    /**
     * Convert Nepali date to English date.
     *
     * @param nepDate Nepali date to convert from.
     * @return Corresponding English date.
     */
    fun getEnglishDate(nepDate: Date): Date? {
        var days = 0
        val year: Int =
            nepDate.year - startNepaliYear
        for (i in 0..year) {
            if (i >= data.size) break
            for (j in 0..11) {
                if (i == year && j == nepDate.month - 1) {
                    days += nepDate.day - 1
                    val c: Calendar = startEnglishDate.calendar
                    c.add(Calendar.DATE, days)
                    return Date(c)
                } else days += data.get(i)
                    .get(j)
            }
        }
        return null
    }

    fun getDayName(dayOfWeek: Int): String? {
        return if (dayOfWeek < 1 || dayOfWeek > 7) {
            ""
        } else WEEK_DAY_NAMES.get(dayOfWeek)
    }

    fun getMonthName(monthNumber: Int): String? {
        return if (monthNumber < 1 || monthNumber > 12) MONTH_NAMES_NEP.get(
            0
        ) else MONTH_NAMES_NEP.get(monthNumber)
    }

    fun getMonthNameAd(monthNumber: Int): String? {
        return if (monthNumber < 1 || monthNumber > 12) MONTH_NAMES_AD.get(
            0
        ) else MONTH_NAMES_AD.get(monthNumber)
    }

    fun getMonthNumber(monthName: String): Int {
        var result = 0
        for (i in MONTH_NAMES_NEP.indices) {
            if (MONTH_NAMES_NEP.get(i) == monthName) {
                result = i
                break
            }
        }
        return result
    }

    fun getMonthNumberAd(monthName: String): Int {
        var result = 0
        for (i in MONTH_NAMES_AD.indices) {
            if (MONTH_NAMES_AD.get(i) == monthName) {
                result = i
                break
            }
        }
        return result
    }

    fun getNextMonthName(monthName: String): String? {
        var month = getMonthNumber(monthName) + 1
        if (month > 12) {
            month = 1
        }
        return getMonthName(month)
    }

    fun getNextAdMonthName(monthName: String): String? {
        var month = getMonthNumberAd(monthName) + 1
        if (month > 12) {
            month = 1
        }
        return getMonthNameAd(month)
    }

    /**
     * Provides name of the next month in BS.
     *
     * @param monthName
     * @return
     */
    fun getPreviousMonthName(monthName: String): String? {
        var month = getMonthNumber(monthName) - 1
        if (month < 1) {
            month = 12
        }
        return getMonthName(month)
    }

    fun getPreviousAdMonthName(monthName: String): String? {
        var month = getMonthNumberAd(monthName) - 1
        if (month < 1) {
            month = 12
        }
        return getMonthNameAd(month)
    }

    fun resetToFirstDayOfBs(date: Date): Date? {
        date.day = 1
        return date.convertToEnglish().convertToNepali()
    }
}