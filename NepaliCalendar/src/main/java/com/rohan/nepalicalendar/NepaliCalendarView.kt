package com.rohan.nepalicalendar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.rohan.nepalicalendar.core.date.Date
import com.rohan.nepalicalendar.core.date.DateUtils.getNepaliDate
import com.rohan.nepalicalendar.databinding.LayoutCalendarWithEventBinding
import com.rohan.nepalicalendar.enum.CalendarType
import com.rohan.nepalicalendar.enum.LocalizationType
import com.rohan.nepalicalendar.model.DateModel
import com.rohan.nepalicalendar.model.EventModel
import java.text.DecimalFormat
import java.util.ArrayList
import java.util.Calendar

class NepaliCalendarView : LinearLayout {
    private var calendarType: CalendarType = CalendarType.AD
    private var language: LocalizationType = LocalizationType.ENGLISH_US
    private lateinit var binding: LayoutCalendarWithEventBinding
    private var eventList: ArrayList<EventModel> = arrayListOf()
    private var monthChangeListener: MonthChangeListener? = null
    private var dateClickListener: DateClickListener? = null
    private var currentMonthDateList = arrayListOf<DateModel>()


    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        loadUi(context, attrs)
    }


    private fun loadUi(context: Context, attrs: AttributeSet?) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_calendar_with_event, this, true)
    }


    /**
     * For showing English (Ad) or nepali (BS) calendar
     * @param CalendarType -> CalendarType.AD for english calendar, CalendarType.BS for nepal(bs) calendar
     */
    fun setCalendarType(type: CalendarType): NepaliCalendarView {
        this.calendarType = type
        return this
    }

    /**
     * For setting language of calendar
     * @param LocalizationType -> LocalizationType.ENGLISH_US for english language, LocalizationType.NEPALI_NP for nepali language
     */
    fun setLanguage(lan: LocalizationType): NepaliCalendarView {
        this.language = lan
        return this
    }

    /**
     * set a callback which is invoked when month is changed
     */
    fun setOnMonthChangeListener(listener: MonthChangeListener): NepaliCalendarView {
        monthChangeListener = listener
        return this
    }

    /**
     * set a callback which is invoked when date of a month is clicked
     */
    fun setOnDateClickListener(listener: DateClickListener): NepaliCalendarView {
        dateClickListener = listener
        return this
    }


    private val calAdapter: EventCalendarAdapter by lazy {
        EventCalendarAdapter(dateClickListener)
    }

    private fun todayMonthYear(calendarInstance: Calendar): Pair<Int, Int> {
        val calendarInstance = Calendar.getInstance()
        var currentMonth = 0
        var currentYear = 0
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

    private fun initCalendar() {
        val calendar = Calendar.getInstance()
        val todayMonthYrs=todayMonthYear(calendar)
        var currentMonth = todayMonthYrs.first
        var currentYear = todayMonthYrs.second

        setAdapter(currentMonth, currentYear,true)

        binding.rvCalendar.apply {
            adapter = calAdapter
        }

        binding.ivArrowLeft.setOnClickListener {
            if (currentMonth == 1) {
                currentMonth = 12
                currentYear -= 1
            } else {
                currentMonth -= 1
            }
            setAdapter(currentMonth, currentYear, true)
        }

        binding.ivArrowRight.setOnClickListener {
            if (currentMonth == 12) {
                currentMonth = 1
                currentYear += 1
            } else {
                currentMonth += 1
            }
            setAdapter(currentMonth, currentYear, true)
        }
        with(binding) {
            tvToday.setOnClickListener {
                var today=  todayMonthYear(calendar)
                currentMonth = today.first
                currentYear = today.second
                setAdapter(currentMonth, currentYear, true)

            }
        }
    }


    private fun setAdapter(currentMonth: Int, currentYear: Int, isMonthChange: Boolean = false) {
        val (dateList, title) = CalendarController.getDateList(
            calendarType,
            language,
            currentMonth,
            currentYear
        )

        currentMonthDateList.clear()
        currentMonthDateList.addAll(dateList)
        binding.tvDate.text = title

        val validDateList=dateList.filter { it.todayWeekDay!=0}
        if (isMonthChange) {
            monthChangeListener?.onMonthChange(validDateList.first(),validDateList.last(),currentYear, currentMonth)
        }
        setEvent(eventList)  //set date in adapter + set event if available


    }


    /**
     * Set event to the calendar
     * @param eventList arraylist of [EventModel]
     */
    val decFormat = DecimalFormat("00")
    fun setEvent(eventList: ArrayList<EventModel>): NepaliCalendarView {
        this.eventList = eventList
        if (currentMonthDateList.isNotEmpty()) {
            for (event in eventList) {
                val fromDate = event.FromDate.substringBefore("T").split("-")
                if (fromDate.size != 3) {
                    continue
                }
                val from_y = fromDate[0].toInt()
                val from_m = fromDate[1].toInt()
                val from_d = fromDate[2].toInt()

                val toDate = event.toDate.substringBefore("T").split("-")
                if (toDate.size != 3) {
                    continue
                }
                val to_y = toDate[0].toInt()
                val to_m = toDate[1].toInt()
                val to_d = toDate[2].toInt()

                val fromDateLong="$from_y${decFormat.format(from_m)}${decFormat.format(from_d)}".toLong()
                val toDateLong="$to_y${decFormat.format(to_m)}${decFormat.format(to_d)}".toLong()

                for (dateModel in currentMonthDateList) {

                    val date = dateModel.formattedAdDate.split("-")

                    if (date.size != 3) {
                        continue
                    }
                    val date_y = date[0].toInt()
                    val date_m = date[1].toInt()
                    val date_d = date[2].toInt()

                    val currentDateLong="$date_y${decFormat.format(date_m)}${decFormat.format(date_d)}".toLong()
                    if(currentDateLong in fromDateLong..toDateLong){
                        dateModel.hasEvent = true
                        dateModel.eventColorCode = event.colorCode
                        dateModel.isHoliday = event.isHolidayEvent
                    }
                }

            }
            calAdapter.addItem(currentMonthDateList)
        }
        return this
    }


    /**
     * Build calendar with given configuration
     */
    fun build() {
        initCalendar()
    }


}