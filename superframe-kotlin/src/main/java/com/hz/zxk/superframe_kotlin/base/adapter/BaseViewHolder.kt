package com.hz.zxk.superframe_kotlin.base.adapter

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
@author zhengxiaoke
@date 2020/4/22 9:57 AM
 */
class BaseViewHolder(view: View, var binding: ViewDataBinding?=null, var itemType: Int?=0) :
    RecyclerView.ViewHolder(view)