package com.rohan.nepalicalendar

import android.util.Log
import com.rohan.nepalicalendar.core.LocalizationHelper
import com.rohan.nepalicalendar.core.LocalizationHelper.weekNameInEnglish
import com.rohan.nepalicalendar.core.LocalizationHelper.weekNameInNepali
import com.rohan.nepalicalendar.core.date.Date
import com.rohan.nepalicalendar.core.date.DateUtils.getEnglishDate
import com.rohan.nepalicalendar.core.date.DateUtils.getNepaliDate
import com.rohan.nepalicalendar.core.date.DateUtils.getNumDays
import com.rohan.nepalicalendar.enum.CalendarType
import com.rohan.nepalicalendar.enum.LocalizationType
import com.rohan.nepalicalendar.model.DateModel
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar

object CalendarController {
    private var month: Int = 0  //month value: 1 to 12
    private var year: Int = 0

    var currentMonthDateList = arrayListOf<DateModel>()
        get() = field
        set(dateList) {
            field.clear()
            field.addAll(dateList)
        }

    fun getDateList(
        calendarType: CalendarType,
        localizationType: LocalizationType, month: Int, year: Int
    ): Pair<ArrayList<DateModel>, String> {
        currentMonthDateList.clear()
        this.month = month
        this.year = year
        return when (calendarType) {
            CalendarType.AD -> {
                val (dateList, title) = englishCalendarController(localizationType)
                currentMonthDateList = dateList
                Pair(dateList, title)
            }
            else -> {
                val (dateList, title) = nepaliCalendarController(localizationType)
                currentMonthDateList = dateList
                Pair(dateList, title)
            }
        }

    }


    fun nepaliCalendarController(localizationType: LocalizationType): Pair<ArrayList<DateModel>, String> {

        val dateList = weekNameDateModel(localizationType)

        val todayNepaliDate = getNepaliDate(Date(Calendar.getInstance()))
        val nepaliDate = Date(year, month, 1)

        val monthYearTitle = when (localizationType) {
            LocalizationType.ENGLISH_US -> {
                "${LocalizationHelper.nepaliMonthNameInEngFont(month)}, $year"
            }
            else -> {
                LocalizationHelper.toNepaliDigit(
                    "${
                        LocalizationHelper.nepaliMonthNameInNepaliFont(
                            month
                        )
                    }, $year"
                )
            }
        }

        val nepaliFirstMonthDayInEnglish =
            getEnglishDate(Date(nepaliDate.year, nepaliDate.month, 1))
        val numberOfDaysInMonth = getNumDays(nepaliDate.year, nepaliDate.month)
        val weekNumberOfFirstDayOfNepaliMonth = nepaliFirstMonthDayInEnglish!!.weekDayNum

        for (i in 1 until weekNumberOfFirstDayOfNepaliMonth) {
            dateList.add(DateModel(""))
        }

        var thisDayWeekNumber = weekNumberOfFirstDayOfNepaliMonth

        for (i in 1..numberOfDaysInMonth) {
            nepaliDate.day = i
            val convertedAdDate = getEnglishDate(nepaliDate)
            val formattedEnglishDate =
                "${convertedAdDate!!.year}-${convertedAdDate!!.month}-${convertedAdDate.day}"
            val adDate = SimpleDateFormat("yyyy-MM-dd").parse(formattedEnglishDate)
            val displayDay =
                if (localizationType == LocalizationType.ENGLISH_US) i.toString() else LocalizationHelper.toNepaliDigit(
                    i.toString()
                )
            val dateModel = DateModel(
                displayDay,
                formattedAdDate = formattedEnglishDate,
                formattedBsDate = "${nepaliDate.year}-${nepaliDate.month}-${nepaliDate.day}",
                adDate = adDate,
                isAd = false,
                todayWeekDay = thisDayWeekNumber++,
                localizedFormattedDate = "$displayDay $monthYearTitle"
            )

            if (todayNepaliDate!!.year == year && todayNepaliDate!!.month == month && todayNepaliDate.day == i) {
                dateModel.hasEvent = true
                dateModel.isToday = true
                dateModel.eventName = "Today"
                dateModel.eventColorCode = "#044b8b"
            }


            dateList.add(dateModel)

        }

        return Pair(dateList, monthYearTitle)


    }


    fun englishCalendarController(localizationType: LocalizationType): Pair<ArrayList<DateModel>, String> {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month.minus(1))
        calendar.set(Calendar.DAY_OF_MONTH,1)
        val monthNum = calendar.get(Calendar.MONTH).plus(1)
        val year = calendar.get(Calendar.YEAR)
        val monthYearTitle = when (localizationType) {
            LocalizationType.ENGLISH_US -> {
                "${LocalizationHelper.englishMonthNameInEnglishFont(monthNum)}, $year"
            }
            else -> {
                LocalizationHelper.toNepaliDigit(
                    "${
                        LocalizationHelper.englishMonthNameInNepaliFont(
                            monthNum
                        )
                    }, $year"
                )
            }
        }

        Log.d("d","data is $monthYearTitle")


        val dateList = weekNameDateModel(localizationType)

        val daysInThisMonth = calendar.getActualMaximum(Calendar.DATE)

        val weekNumberOfFirstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK)

        for (i in 1 until weekNumberOfFirstDayOfMonth) {
            dateList.add(DateModel(""))
        }

        var thisDayWeekNumber = weekNumberOfFirstDayOfMonth

        for (i in 1..daysInThisMonth) {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val thisDateString = "$year-${month}-$i"
            val date = sdf.parse(thisDateString)

            val dispDay =
                if (localizationType == LocalizationType.ENGLISH_US) i.toString() else LocalizationHelper.toNepaliDigit(
                    i.toString()
                )
            val dateModel =
                DateModel(
                    dispDay, formattedAdDate = thisDateString,
                    adDate = date,
                    isAd = true,
                    todayWeekDay = thisDayWeekNumber++,
                    localizedFormattedDate = "$dispDay $monthYearTitle"
                )
            if (sdf.format(Calendar.getInstance().time) == sdf.format(date)) {
                dateModel.hasEvent = true
                dateModel.isToday = true
                dateModel.eventName = "Today"
                dateModel.eventColorCode = "#76BF4E"
            }
            dateList.add(dateModel)

        }

        return Pair(dateList, monthYearTitle)


    }


    fun weekNameDateModel(localizationType: LocalizationType): ArrayList<DateModel> {
        return when (localizationType) {
            LocalizationType.ENGLISH_US -> {
                arrayListOf<DateModel>(
                    DateModel(weekNameInEnglish(1)),
                    DateModel(weekNameInEnglish(2)),
                    DateModel(weekNameInEnglish(3)),
                    DateModel(weekNameInEnglish(4)),
                    DateModel(weekNameInEnglish(5)),
                    DateModel(weekNameInEnglish(6)),
                    DateModel(weekNameInEnglish(7)),
                )
            }
            else -> {
                arrayListOf(
                    DateModel(weekNameInNepali(1)),
                    DateModel(weekNameInNepali(2)),
                    DateModel(weekNameInNepali(3)),
                    DateModel(weekNameInNepali(4)),
                    DateModel(weekNameInNepali(5)),
                    DateModel(weekNameInNepali(6)),
                    DateModel(weekNameInNepali(7)),
                )
            }
        }

    }


}