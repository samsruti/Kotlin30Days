package com.samsruti.kotlin30days.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.samsruti.kotlin30days.R
import kotlinx.android.synthetic.main.activity_main.*

class StatsCardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    init {
        inflate(context, R.layout.card_layout_stats, this)
    }

}