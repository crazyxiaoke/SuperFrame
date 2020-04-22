package com.hz.zxk.demo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hz.zxk.demo.R
import com.hz.zxk.superframe_kotlin.base.BaseLazyFragment
import com.hz.zxk.superview_kotlin.recyclerview.HorizontalPageRecycleView
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.item_function.view.*

/**
@author zhengxiaoke
@date 2019-11-24 12:04
 */
class MainFragment : BaseLazyFragment() {
    override fun lazyInit() {

    }

    override fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun init(savedInstanceState: Bundle?) {
        val list: MutableList<Any> = ArrayList();
        for (i in 0..10) {
            list.add("Hi$i")
        }
        val adapter = MyAdapter() as HorizontalPageRecycleView.Adapter<Any>
        horizontal_viewpager.setRecyclerViewAdapter(adapter)
        horizontal_viewpager.create(list)

        button.setOnClickListener {
            list.clear()
            for (i in 11..21) {
                list.add("Hell$i")
            }
            horizontal_viewpager.refresh(list)
        }

        add.setOnClickListener {
            horizontal_viewpager.add("HH")
        }

    }

    inner class MyAdapter : HorizontalPageRecycleView.Adapter<String>() {
        override fun onCreateView(parent: ViewGroup, itemType: Int): View {
            return LayoutInflater.from(context).inflate(
                R.layout.item_function,
                parent,
                false
            )


        }


        override fun onBindView(view: View, position: Int, data: String) {
            view.tv_function_name.text = data
        }
    }

}