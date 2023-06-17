package com.rohan.nepalicalendar

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rohan.nepalicalendar.databinding.ItemEnglishCalendarBinding
import com.rohan.nepalicalendar.model.DateModel

class EventCalendarAdapter(val dateClickListener: DateClickListener? = null) :
    RecyclerView.Adapter<EventCalendarAdapter.VH>() {
    private var dateList = arrayListOf<DateModel>()
    var currentSelectionPos = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding: ItemEnglishCalendarBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_english_calendar,
            parent,
            false
        )

        return VH(binding)
    }

    override fun getItemCount(): Int {
        return dateList.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(dateList[position], dateClickListener)
    }

    fun addItem(list: List<DateModel>) {
        dateList.clear()
        dateList.addAll(list)
        currentSelectionPos=-1
        notifyDataSetChanged()
    }


    inner class VH(val binding: ItemEnglishCalendarBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DateModel, dateClickListener: DateClickListener?) {
            with(binding) {
                tv.text = item.displayedDayInCalendar

                if (adapterPosition in 0..6) {
                    tv.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.mero_primary_color
                        )
                    )
                } else {
                    tv.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.mero_light_primary_color
                        )
                    )
                }




                if (item.hasEvent) {
                    tv.apply {
                        setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
                        background = ContextCompat.getDrawable(root.context,
                            R.drawable.rounded_button
                        )
                        val color = try {
                            Color.parseColor(item.eventColorCode!!)
                        } catch (e: Exception) {
                            Color.parseColor("#3326324A")
                        }
                        backgroundTintList= ColorStateList.valueOf(color)

                    }
                } else {
                    tv.background = null
                    tv.backgroundTintList=null

                }

                if (adapterPosition.plus(1) % 7 == 0) {
                    tv.setTextColor(ContextCompat.getColor(binding.root.context,R.color.holiday_sat_color))
                }

                with(binding) {
                    clDate.setOnClickListener {

                        if (adapterPosition>6 && currentSelectionPos != adapterPosition) {
                            dateClickListener?.let {
                                dateClickListener!!.onDateClick(item)
                            }
                            currentSelectionPos = adapterPosition
                            notifyDataSetChanged()
                        }

                    }

                    if (currentSelectionPos == adapterPosition) {
                        binding.flDate.background = ContextCompat.getDrawable(root.context, R.drawable.selected_date_bg)
                    } else {
                        binding.flDate.background = null
                    }

                }


            }
        }
    }
}