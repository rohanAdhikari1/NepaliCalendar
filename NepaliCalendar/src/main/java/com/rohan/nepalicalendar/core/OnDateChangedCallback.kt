package com.rohan.nepalicalendar.core

import com.rohan.nepalicalendar.core.date.Date

interface OnDateChangedCallback {
    fun onDateChanged(date: Date?)
}