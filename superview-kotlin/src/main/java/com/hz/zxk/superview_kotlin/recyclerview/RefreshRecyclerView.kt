package com.hz.zxk.superview_kotlin.recyclerview

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hz.zxk.superview_kotlin.R
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener

/**
 *@Author zhengxiaoke
 *@Date 2019/12/24 20:04
 *@Description 实现下拉刷新/上拉加载，数据为空时显示缺省页
 */
class RefreshRecyclerView(context: Context?, attrs: AttributeSet?) :
    SmartRefreshLayout(context, attrs) {
    private val FINISH_REFRESH = 0x01
    private val FINISH_LOADMORE = 0X02

    private var mEmptyViewResId = 0
    private var mRecyclerViewBackground: Drawable? = null

    private lateinit var mParentLayout: RelativeLayout
    lateinit var mRecyclerView: RecyclerViewEmptySupport
        private set
    var mEmptyView: View? = null
        set(value) = value?.let { mRecyclerView.setEmptyView(it) }!!

    private var isRefresh = true;
    private var mListener: OnLoadDataListener? = null

    constructor(context: Context?) : this(context, null)

    init {
        init(attrs)
    }

    private val mRefreshHandler = Handler { msg ->
        when (msg.what) {
            FINISH_REFRESH -> finishRefresh()
            FINISH_LOADMORE -> finishLoadMore()
        }
        false
    }

    private val finishRefreshLoadMoreObserver: RecyclerView.AdapterDataObserver =
        object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                when (isRefresh) {
                    true -> finishRefresh()
                    false -> finishLoadMore()
                }
            }

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                when (isRefresh) {
                    true -> finishRefresh()
                    false -> finishLoadMore()
                }
            }
        }

    private fun init(attrs: AttributeSet?) {
        if (attrs != null) {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.RefreshRecyclerView)
            try {
                mEmptyViewResId = ta.getResourceId(R.styleable.RefreshRecyclerView_rf_empty_view, 0)
                mRecyclerViewBackground =
                    ta.getDrawable(R.styleable.RefreshRecyclerView_rf_recyclerview_background)
            } finally {
                ta.recycle()
            }
        }
        //初始化父layout
        initParentView()
        //初始化RecyclerView
        initRecyclerView()
        //设置空数据时的view
        initEmptyView()
        //设置刷新/下拉加载监听
        setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                isRefresh = true
                mRefreshHandler.sendEmptyMessageDelayed(FINISH_REFRESH, 5000)
                mListener?.load(this@RefreshRecyclerView, true)
            }

            override fun onLoadMore(refreshLayout: RefreshLayout) {
                isRefresh = false
                mRefreshHandler.sendEmptyMessageDelayed(FINISH_LOADMORE, 5000)
                mListener?.load(this@RefreshRecyclerView, false)
            }
        })
    }


    private fun initParentView() {
        mParentLayout = RelativeLayout(context)
        mParentLayout.layoutParams =
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        addView(mParentLayout)
    }

    private fun initRecyclerView() {
        mRecyclerView = RecyclerViewEmptySupport(context)
        mRecyclerView.background = mRecyclerViewBackground
        val layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
        mRecyclerView.layoutParams = layoutParams
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mParentLayout.addView(mRecyclerView)
    }

    private fun initEmptyView() {
        if (mEmptyViewResId != 0) {
            mEmptyView = LayoutInflater.from(context).inflate(mEmptyViewResId, mParentLayout, false)
            if (mEmptyView != null) {
                mEmptyView?.visibility = View.GONE
                mRecyclerView.setEmptyView(mEmptyView!!)
                mParentLayout.addView(mEmptyView)
            }
        }

    }

    /**
     * 设置RecyclerView的背景
     */
    fun setRecyclerViewBackground(drawable: Drawable) {
        mRecyclerView.background = drawable
    }

    /**
     * 设置RecyclerView的背景
     */
    fun setRecyclerViewBackgroundColor(color: Int) {
        mRecyclerView.setBackgroundColor(color)
    }

    fun setAdapter(adapter: RecyclerView.Adapter<*>?) {
        if (adapter != null) {
            adapter.registerAdapterDataObserver(finishRefreshLoadMoreObserver)
            mRecyclerView.adapter = adapter
        }
    }


    fun setLayoutManager(layoutManager: RecyclerView.LayoutManager) {
        mRecyclerView.layoutManager = layoutManager
    }

    fun addItemDecoration(itemDecoration: RecyclerView.ItemDecoration) {
        mRecyclerView.addItemDecoration(itemDecoration)
    }

    fun addOnLoadDataListener(listener: OnLoadDataListener) {
        this.mListener = listener
    }

    public interface OnLoadDataListener {
        /**
         * @param isRefresh true:刷新，false:加载更多
         */
        fun load(refreshLayout: RefreshRecyclerView, isRefresh: Boolean)
    }
}