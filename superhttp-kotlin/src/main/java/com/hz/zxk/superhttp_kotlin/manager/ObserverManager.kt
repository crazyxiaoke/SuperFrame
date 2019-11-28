package com.hz.zxk.superhttp_kotlin.manager

import io.reactivex.observers.DisposableObserver

class ObserverManager private constructor() {
    private var maps = linkedMapOf<String, DisposableObserver<*>>()

    companion object {
        val instance: ObserverManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ObserverManager()
        }
    }

    fun add(tag: String?, observer: DisposableObserver<*>) {
        if (tag == null || tag.isEmpty()) {
            return
        }
        maps.keys.forEach {
            if (tag == it) {
                maps[it]?.dispose()
                maps.remove(it)
                maps[tag] = observer
                return
            }
        }
        maps[tag] = observer
    }

    fun remove(tag: String?) {
        if (tag == null || tag.isEmpty())
            return
        if (maps.isNotEmpty()) {
            maps.remove(tag)
        }
    }

    fun cancel(tag: String) {
        if (maps.isEmpty()) {
            return
        }
        val observer = maps[tag]
        if (observer != null && !observer.isDisposed) {
            observer.dispose()
            maps.remove(tag)
        }
    }

    fun cancelAll() {
        if (maps.isEmpty()) {
            return
        }
        maps.keys.forEach {
            cancel(it)
        }
    }

}