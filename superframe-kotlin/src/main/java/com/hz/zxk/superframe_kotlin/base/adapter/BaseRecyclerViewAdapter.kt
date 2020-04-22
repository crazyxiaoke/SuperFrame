package com.hz.zxk.superframe_kotlin.base.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
@author zhengxiaoke
@date 2020/4/22 9:56 AM
 */
class BaseRecyclerViewAdapter<T, B : ViewDataBinding>(
    context: Context,
    private val layoutId: Int,
    private val variableId: Int = 0,
    private var data: MutableList<T>? = null
) : RecyclerView.Adapter<BaseViewHolder>() {
    private var mInflater: LayoutInflater
    private var mItemClickListener: OnItemClickListener<T>? = null
    private var mItemChildClickListener: OnItemChildClickListener<T, B>? = null

    init {
        if (data == null) {
            data = arrayListOf()
        }
        mInflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding: B = DataBindingUtil.inflate(mInflater, layoutId, parent, false)
        return BaseViewHolder(binding.root, binding, viewType)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val binding: B = holder.binding as B
        if (variableId > 0) {
            binding.setVariable(variableId, data?.get(position))
        }

        holder.itemView.setOnClickListener {
            mItemClickListener?.onClick(this as Adapter, position, data?.get(position))
        }

        if (mItemChildClickListener != null) {
            mItemChildClickListener?.onClick(
                this as Adapter,
                binding,
                position,
                data?.get(position)
            )
        }
    }

    fun getItem(position: Int): T? {
        return data?.get(position)
    }

    fun setOnItemClickListener(listener: OnItemClickListener<T>) {
        this.mItemClickListener = listener
    }

    fun setOnItemChildClickListener(listener: OnItemChildClickListener<T, B>) {
        this.mItemChildClickListener = listener
    }

    interface OnItemClickListener<T> {
        fun onClick(adapter: Adapter, position: Int, data: T?)
    }

    interface OnItemChildClickListener<T, B : ViewDataBinding> {
        fun onClick(adapter: Adapter, binding: B, position: Int, data: T?)
    }
}