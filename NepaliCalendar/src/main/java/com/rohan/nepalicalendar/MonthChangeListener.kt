package com.rohan.nepalicalendar

import com.rohan.nepalicalendar.model.DateModel

interface MonthChangeListener {
    fun onMonthChange(startDateOfThisMonth: DateModel, endDateOfThisMonth:DateModel, adYear:Int, adMonth: Int)
}