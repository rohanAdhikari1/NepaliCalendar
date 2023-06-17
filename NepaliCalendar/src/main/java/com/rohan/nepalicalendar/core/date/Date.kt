package com.rohan.nepalicalendar.core.date

import com.rohan.nepalicalendar.core.date.DateUtils.getDayName
import com.rohan.nepalicalendar.core.date.DateUtils.getEnglishDate
import com.rohan.nepalicalendar.core.date.DateUtils.getNepaliDate
import java.util.Calendar
import java.util.Locale

class Date {
    /// Year this date is contained in.
    var year = 0

    /// Month this date in contained in. Starts at index 1.
    var month = 0

    // Day of the month this date represents. Starts at index 1.
    var day = 0

    /**
     * Create a new Date instance.
     *
     * @param year  Year this date is contained in.
     * @param month Month of the year starting at 1.
     * @param day   Day of the month starting at 1.
     */
    constructor(year: Int, month: Int, day: Int) {
        this.year = year
        this.month = month
        this.day = day
    }

    /**
     * Create a new Date instance from Calendar instance.
     *
     * @param calendar Calendar whose data is used to figure out year, month and day values.
     */
    constructor(calendar: Calendar) {
        year = calendar[Calendar.YEAR]
        month = calendar[Calendar.MONTH] + 1
        day = calendar[Calendar.DAY_OF_MONTH]
    }

    /**
     * Get Calendar instance representing this date, considering that this date is in English.
     *
     * @return Calendar instance corresponding to this English date.
     */
    val calendar: Calendar
        get() {
            val c = Calendar.getInstance()
            c.clear()
            c[year, month - 1] = day
            return c
        }

    /**
     * Convert this date to Nepali date, considering that this one is in English.
     *
     * @return Corresponding Nepali date for this English date.
     */
    fun convertToNepali(): Date {
        return getNepaliDate(this)!!
    }

    /**
     * Convert this date to English date, considering that this one is in Nepali.
     *
     * @return Corresponding English date for this Nepali date.
     */
    fun convertToEnglish(): Date {
        return getEnglishDate(this)!!
    }

    /**
     * Get the number of days from this date to a new date.
     *
     * @param newDate Last date till which number of days is calculated.
     * @return (this - newDate) as number of days
     */
    fun getDaysTill(newDate: Date): Int {
        val a = ((newDate.calendar.timeInMillis
                - calendar.timeInMillis)
                / (24 * 60 * 60 * 1000)).toInt() + 1
        return if (newDate.calendar.timeInMillis >= 504987300000L) {  //due to  15 more minute issue on java calendar for date 1986 january 1, time in millis conversion
            a + 1
        } else {
            a
        }
    }

    val weekDay: String
        get() = getDayName(Date(year, month, day).calendar[Calendar.DAY_OF_WEEK])!!
    val weekDayNum: Int
        get() = Date(year, month, day).calendar[Calendar.DAY_OF_WEEK]
    val readableBsDate: String
        get() = java.lang.String.format(
            Locale.getDefault(),
            "%s %02d, %04d",
            DateUtils.getMonthName(month),
            day,
            year
        )
    val readableAdDate: String
        get() = java.lang.String.format(
            Locale.getDefault(),
            "%s %02d, %04d",
            DateUtils.getMonthNameAd(month),
            day,
            year
        )

    fun pointToDayOneAd() {
        day = 1
    }

    fun clone(): Date {
        return Date(year, month, day)
    }

    /**
     * Convert to simple string representation of this date.
     *
     * @return String in format yyyy-MM-dd.
     */
    override fun toString(): String {
        return String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month, day)
    }
}