package com.rohan.nepalicalendar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.rohan.nepalicalendar.core.date.Date
import com.rohan.nepalicalendar.core.date.DateUtils.getNepaliDate
import com.rohan.nepalicalendar.databinding.LayoutHorizontalCalendarBinding
import com.rohan.nepalicalendar.enum.CalendarType
import com.rohan.nepalicalendar.enum.LocalizationType
import com.rohan.nepalicalendar.model.DateModel
import com.rohan.nepalicalendar.model.EventModel
import java.util.ArrayList
import java.util.Calendar

class HorizontalNepaliCalendarView: LinearLayout {
    private var calendarType: CalendarType = CalendarType.AD
    private var language: LocalizationType = LocalizationType.ENGLISH_US
    private lateinit var binding: LayoutHorizontalCalendarBinding
    private var eventList: ArrayList<EventModel> = arrayListOf()
    private var monthChangeListener: MonthChangeListener? = null
    private var dateClickListener: DateClickListener? = null
    private var currentMonthDateList = arrayListOf<DateModel>()


    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        loadUi(context, attrs)
    }


    private fun loadUi(context: Context, attrs: AttributeSet?) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_horizontal_calendar, this, true)

    }


    /**
     * For showing English (Ad) or nepali (BS) calendar
     * @param CalendarType -> CalendarType.AD for english calendar, CalendarType.BS for nepal(bs) calendar
     */
    fun setCalendarType(type: CalendarType): HorizontalNepaliCalendarView {
        this.calendarType = type
        return this
        val a = CalendarType.AD
    }


    /**
     * For setting language of calendar
     * @param LocalizationType -> LocalizationType.ENGLISH_US for english language, LocalizationType.NEPALI_NP for nepali language
     */
    fun setLanguage(lan: LocalizationType): HorizontalNepaliCalendarView {
        this.language = lan
        return this
    }


    fun setOnMonthChangeListener(listener: MonthChangeListener): HorizontalNepaliCalendarView {
        monthChangeListener = listener
        return this
    }

    /**
     * set a callback which is invoked when date is clicked
     */
    fun setOnDateClickListener(listener: DateClickListener): HorizontalNepaliCalendarView {
        dateClickListener = listener
        return this
    }


    private val horizontalCalendarAdapter: HorizontalCalendarAdapter by lazy {
        HorizontalCalendarAdapter(dateClickListener)
    }


    var currentMonth = 0
    var currentYear = 0
    var originalCurrentMonth = 0
    var originalCurrentYear = 0
    private fun todayMonthYear(calendarInstance: Calendar): Pair<Int, Int> {
        val calendarInstance = Calendar.getInstance()
        when (calendarType) {
            CalendarType.AD -> {
                currentMonth = calendarInstance.get(Calendar.MONTH).plus(1)
                currentYear = calendarInstance.get(Calendar.YEAR)
            }
            else -> {
                val todayNepaliDate = getNepaliDate(Date(calendarInstance))
                currentMonth = todayNepaliDate!!.month
                currentYear = todayNepaliDate.year
            }
        }
        return Pair(currentMonth, currentYear)
    }

    private val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }
    val calendar = Calendar.getInstance()

    private fun initCalendar() {
        val todayMonthYrs = todayMonthYear(calendar)
        currentMonth = todayMonthYrs.first
        currentYear = todayMonthYrs.second

        originalCurrentYear = currentYear
        originalCurrentMonth = currentMonth

        setAdapter(currentMonth, currentYear, MONTH_CHANGE_CURRENT)

        binding.rvHorizontalCalendar.apply {
            adapter = horizontalCalendarAdapter
            layoutManager = linearLayoutManager
        }

    }


    fun setNextMonthDate() {
        if (currentMonth == 12) {
            currentMonth = 1
            currentYear += 1
        } else {
            currentMonth += 1
        }
        setAdapter(currentMonth, currentYear, MONTH_CHANGE_NEXT)
    }

    fun setPreviousMonthDate() {
        if (currentMonth == 1) {
            currentMonth = 12
            currentYear -= 1
        } else {
            currentMonth -= 1
        }
        setAdapter(currentMonth, currentYear, MONTH_CHANGE_PREVIOUS)
    }


    private fun setAdapter(currentMonth: Int, currentYear: Int, monthChangeStatus: Int) {
        val (dateList, title) = CalendarController.getDateList(
            calendarType,
            language,
            currentMonth,
            currentYear
        )
        currentMonthDateList.clear()
        currentMonthDateList.addAll(dateList)

        val finalDateList = currentMonthDateList.filter { it.todayWeekDay != 0 }

        var scrollToPos = 0
        if (currentMonth == originalCurrentMonth && currentYear == originalCurrentYear) {
            val currentDayIndex = finalDateList.indexOfFirst { it.isToday }
            horizontalCalendarAdapter.addItem(
                finalDateList,
                MONTH_CHANGE_CURRENT,
                language,
                currentDayIndex
            )
            if (currentDayIndex > 3) {
                scrollToPos = currentDayIndex - 3
            }
        } else {
            horizontalCalendarAdapter.addItem(finalDateList, monthChangeStatus, language)
            scrollToPos = when (monthChangeStatus) {
                0 -> finalDateList.size - 1
                else -> 0

            }
        }
        linearLayoutManager.scrollToPositionWithOffset(scrollToPos, 0)

    }

    /**
     * Build calendar with given configuration
     */
    fun build() {
        initCalendar()
    }


    companion object {
        const val MONTH_CHANGE_PREVIOUS = 0
        const val MONTH_CHANGE_CURRENT = 1
        const val MONTH_CHANGE_NEXT = 2

    }


}