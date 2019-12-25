package com.hz.zxk.demo.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hz.zxk.demo.R
import com.hz.zxk.superframe_kotlin.base.BaseLazyFragment
import com.hz.zxk.superview_kotlin.recyclerview.RefreshRecyclerView
import kotlinx.android.synthetic.main.fragment_shop.*

/**
@author zhengxiaoke
@date 2019-11-24 12:11
 */
class ShopFragment : BaseLazyFragment() {
    var names: MutableList<String> = arrayListOf()
    private var adapter: MyAdapter? = null
    override fun lazyInit() {

    }

    override fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    override fun init(savedInstanceState: Bundle?) {
        adapter = MyAdapter(context!!)
        refreshLayout.setAdapter(adapter)

//        for (i: Int in 1..10)
//            names.add("测试")
        adapter?.refreshData(names)

        refreshLayout.addOnLoadDataListener(object : RefreshRecyclerView.OnLoadDataListener {
            override fun load(refreshLayout: RefreshRecyclerView, isRefresh: Boolean) {
                if (isRefresh) {
                    for (i: Int in 1..10)
                        names.add("测试")
                    adapter?.refreshData(names)
                } else {
                    var addNames: MutableList<String> = arrayListOf()
                    for (i: Int in 11..20)
                        addNames.add("测试${i}")
                    adapter?.addData(addNames)
                }
            }
        });

    }

    private class MyAdapter private constructor() : RecyclerView.Adapter<ViewHolder>() {
        private var datas: MutableList<String>? = null
        private lateinit var context: Context

        constructor(context: Context) : this() {
            this.context = context
        }

        fun refreshData(list: MutableList<String>) {
            this.datas = list;
            notifyDataSetChanged()
        }

        fun addData(list: MutableList<String>) {
            if (this.datas != null) {
                this.datas?.addAll(list)
                notifyItemRangeInserted(this.datas!!.size, list.size)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.item_product,
                    parent,
                    false
                )
            )
        }

        override fun getItemCount(): Int {
            return datas?.size ?: 0
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.nameView.text = "${datas?.get(position)}-${position}"
        }

    }

    private class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nameView: TextView = view.findViewById(R.id.name)

    }
}