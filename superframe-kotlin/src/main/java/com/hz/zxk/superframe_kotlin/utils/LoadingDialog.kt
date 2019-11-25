package com.hz.zxk.superframe_kotlin.utils

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.widget.TextView
import com.hz.zxk.superframe_kotlin.R
import com.hz.zxk.superframe_kotlin.extend.dp2px
import java.lang.Exception

/**
@author zhengxiaoke
@date 2019-11-23 21:17
 */
class LoadingDialog private constructor(){
    private  var dialog: Dialog? =null

    companion object{
        val instance:LoadingDialog by lazy(mode=LazyThreadSafetyMode.SYNCHRONIZED){
            LoadingDialog()
        }
    }

    fun show(context: Context){
        show(context,"加载中……")
    }

    fun show(context: Context,info: String?){
        show(context,info,true,null)
    }

    fun show(context: Context, info:String?, cancelable:Boolean, listener:DialogInterface.OnCancelListener?){
        if(isShow()){
            return
        }
        dialog= Dialog(context, R.style.SuperFrameLoadingDialog)
        dialog!!.setContentView(R.layout.superframe_default_loading)
        dialog!!.setCanceledOnTouchOutside(false)
        dialog!!.setCancelable(cancelable)
        dialog!!.setOnCancelListener(listener)
        val infoView=dialog!!.findViewById<TextView>(R.id.tv_info)
        infoView.text=info
        val attribute= dialog!!.window!!.attributes
        attribute.width=context.dp2px(80)
        attribute.height=context.dp2px(80)
        dialog!!.window!!.attributes=attribute
        dialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog!!.show()
    }

    fun isShow():Boolean{
        if(dialog!=null && dialog!!.isShowing){
            return true
        }
        return false
    }

    fun dimiss(){
        try{
            if(dialog!!.isShowing){
                dialog!!.dismiss()
            }
        }catch (e:Exception){
            e.printStackTrace()
        }finally {
            this.dialog =null
        }

    }
}