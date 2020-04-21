package com.hz.zxk.superview_kotlin.recyclerview.horizontalPageRecycleView

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.ViewCompat
import androidx.core.view.get
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.hz.zxk.superview_kotlin.R
import kotlin.math.ceil

/**
@author zhengxiaoke
@date 2020/4/20 10:48 AM
 */
class HorizontalPageRecycleView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleId: Int = 0
) : LinearLayout(context, attrs, defStyleId) {
    var rows = 1
    var columns = 4
    var datas: MutableList<Any>? = null
    private var views: MutableList<View>
    private lateinit var pageAdapter: HorizontalViewPagerAdapter
    private var recyclerAdapter: Adapter<Any>? = null
    private lateinit var viewPager2: ViewPager2

    init {
        attrs?.let {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.HorizontalPageRecycleView)
            try {
                rows = ta.getInt(R.styleable.HorizontalPageRecycleView_row, rows)
                columns = ta.getInt(R.styleable.HorizontalPageRecycleView_column, columns)
            } finally {
                ta.recycle()
            }
        }
        views = ArrayList()
        initViewPager2()
    }

    private fun initViewPager2() {
        viewPager2 = ViewPager2(context)
        val lp = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        viewPager2.layoutParams = lp
        pageAdapter = HorizontalViewPagerAdapter(0)
        viewPager2.adapter = pageAdapter
        addView(viewPager2)
    }

    private fun initRecycleView(): RecyclerView {
        val view = RecyclerView(context)
        val lp = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        view.layoutParams = lp
        view.layoutManager = GridLayoutManager(context, columns)
        return view
    }

    fun create(datas: MutableList<Any>) {
        views.clear()
        viewPager2.currentItem = 0
        this.datas = datas;
        val pages = ceil(datas.size / (rows * columns).toDouble()).toInt()
        pageAdapter.refrshPage(pages)
    }

    fun refresh(datas: MutableList<Any>) {
        create(datas)
    }

    fun setRecyclerViewAdapter(adapter: Adapter<Any>) {
        this.recyclerAdapter = adapter;
    }

    fun getViewPager(): ViewPager2 {
        return viewPager2
    }


    inner class HorizontalViewPagerAdapter(var pages: Int) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        private var views: MutableList<RecyclerView> = arrayListOf()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

            return object : RecyclerView.ViewHolder(initRecycleView()) {}
        }

        fun getItem(position: Int): RecyclerView {
            return views[position]
        }

        fun refrshPage(pages: Int) {
            this.pages = pages
            notifyDataSetChanged()
        }

        override fun getItemCount(): Int {
            return pages
        }


        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            views.add(holder.itemView as RecyclerView)
            datas?.let {
                val recyclerView = holder.itemView as RecyclerView
                var subIndex = position * (rows * columns) + (rows * columns);
                if (subIndex > it.size) {
                    subIndex = it.size
                }
                val adapter = HorizontalRecycleViewAdapter(
                    position,
                    it.subList(position * (rows * columns), subIndex)
                )
                recyclerView.adapter = adapter
            }

        }
    }

    inner class HorizontalRecycleViewAdapter(var index: Int, private var datas2: MutableList<Any>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return object :
                RecyclerView.ViewHolder(recyclerAdapter!!.onCreateView(parent, viewType)) {

            }
        }

        fun addData(data: Any) {
            datas2.add(data)
            notifyDataSetChanged()
        }


        override fun getItemCount(): Int {
            return if (recyclerAdapter == null) 0 else datas2.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            recyclerAdapter!!.onBindView(
                holder.itemView,
                (index * columns) + position,
                datas2[position]
            )
        }

    }


    abstract class Adapter<T> {
        abstract fun onCreateView(parent: ViewGroup, itemType: Int): View

        abstract fun onBindView(view: View, position: Int, data: T)
    }

}