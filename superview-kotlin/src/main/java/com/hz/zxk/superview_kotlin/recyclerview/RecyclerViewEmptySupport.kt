package com.hz.zxk.superview_kotlin.recyclerview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 *@Author zhengxiaoke
 *@Date 2019/12/24 17:00
 *@Description 带空数据状态的RecyclerView
 */
class RecyclerViewEmptySupport : RecyclerView {
    private var emptyView: View? = null

    private val emptyObserver: AdapterDataObserver = object : AdapterDataObserver() {
        override fun onChanged() {
            if (adapter != null && emptyView != null) {
                if (adapter?.itemCount == 0) {
                    emptyView?.visibility = View.VISIBLE
                    this@RecyclerViewEmptySupport.visibility = View.GONE
                } else {
                    emptyView?.visibility = View.GONE
                    this@RecyclerViewEmptySupport.visibility = View.VISIBLE
                }
            }
        }
    }


    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun setAdapter(adapter: Adapter<*>?) {
        super.setAdapter(adapter)
        adapter?.registerAdapterDataObserver(emptyObserver)
    }

    fun setEmptyView(emptyView: View) {
        this.emptyView = emptyView
    }
}