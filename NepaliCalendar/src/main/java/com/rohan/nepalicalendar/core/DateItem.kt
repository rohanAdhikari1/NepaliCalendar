package com.rohan.nepalicalendar.core

class DateItem(
    val date: String,
    var dateEnd: String = "",
    var month: String = "",
    var year: String = "",
    var adDate: String = "",
    var adMonth: String = "",
    var adYear: String = "",
    var isToday: Boolean = false,
    var isSelected: Boolean = false,
    var isClickable: Boolean = false,
    var isHoliday: Boolean = false,
    var hasEvent: Boolean = false,
    var bgColor: Int = -1
) {
    override fun toString(): String {
        val currentData =
            String.format(
                "{ date: %s, dateEnd: %s, month: %s, year: %s, adDate: %s, adMonth: %s, adYear: %s, isToday: %s, isSelected: %s, isClickable: %s, isHoliday: %s }",
                date,
                dateEnd,
                month,
                year,
                adDate,
                adMonth,
                adYear,
                isToday,
                isSelected,
                isClickable,
                isHoliday
            )
        return currentData
        // return "$year-$month-$date"
    }

    data class Meta(
        val year: String = "",
        val month: String = ""
    )
}