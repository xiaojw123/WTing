package com.ml.lib_base.message

import androidx.annotation.Nullable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.lang.reflect.Field
import java.lang.reflect.Method


class LiveDataBus {


    private var bus: MutableMap<String?, BusMutableLiveData<Any?>?>? =
        null

    private fun LiveDataBus() {
        bus = HashMap()
    }

    private object SingletonHolder {
        val DEFAULT_BUS: LiveDataBus? = LiveDataBus()
    }

    fun get(): LiveDataBus? {
        return SingletonHolder.DEFAULT_BUS
    }

    fun <T> with(key: String?, type: Class<T>): MutableLiveData<T?>? {
        if (!bus!!.containsKey(key)) {
            bus!![key] = BusMutableLiveData()
        }
        return bus!![key] as MutableLiveData<T?>?
    }

    fun with(key: String?): MutableLiveData<Any?>? {
        return with<Any>(key, Any::class.java)
    }

    private class ObserverWrapper<T>(observer: Observer<T?>?) : Observer<T?> {
        private val observer: Observer<T?>?
        override fun onChanged(@Nullable t: T?) {
            if (observer != null) {
                if (isCallOnObserve) {
                    return
                }
                observer.onChanged(t)
            }
        }

        private val isCallOnObserve: Boolean
            private get() {
                val stackTrace =
                    Thread.currentThread().stackTrace
                if (stackTrace != null && stackTrace.size > 0) {
                    for (element in stackTrace) {
                        if ("android.arch.lifecycle.LiveData" == element!!.className && "observeForever" == element.methodName) {
                            return true
                        }
                    }
                }
                return false
            }

        init {
            this.observer = observer
        }
    }


    inner class BusMutableLiveData<T> : MutableLiveData<T>() {

        var observerMap = HashMap<Observer<in T>, Observer<in T>>()

        override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
            super.observe(owner, observer)
            try {
                hook(observer)
            } catch (e: Exception) {
                e.printStackTrace()
            }


        }

        override fun observeForever(observer: Observer<in T?>) {
            if (!observerMap.containsKey(observer)) {


                observerMap.put(observer, LiveDataBus.ObserverWrapper(observer))
            }

            super.observeForever(observer)
        }


        override fun removeObserver(observer: Observer<in T>) {
            var realObserver: Observer<*>? = null
            realObserver = if (observerMap.containsKey(observer)) {
                observerMap.remove(observer)
            } else {
                observer
            }
            super.removeObserver(observer)
        }

        fun hook(observer: Observer<in T>) {
            //get wrapper's version
            val classLiveData = LiveData::class.java
            val fieldObservers: Field = classLiveData.getDeclaredField("mObservers")
            fieldObservers.setAccessible(true)
            val objectObservers: Any = fieldObservers.get(this)
            val classObservers: Class<*> = objectObservers.javaClass
            val methodGet: Method = classObservers.getDeclaredMethod("get", Any::class.java)
            methodGet.setAccessible(true)
            val objectWrapperEntry: Any = methodGet.invoke(objectObservers, observer)
            var objectWrapper: Any? = null
            if (objectWrapperEntry is Map.Entry<*, *>) {
                objectWrapper = objectWrapperEntry.value
            }
            if (objectWrapper == null) {
                throw NullPointerException("Wrapper can not be bull!")
            }
            val classObserverWrapper: Class<*>? = objectWrapper.javaClass.superclass
            val fieldLastVersion: Field = classObserverWrapper!!.getDeclaredField("mLastVersion")
            fieldLastVersion.setAccessible(true)
            //get livedata's version
            //get livedata's version
            val fieldVersion: Field = classLiveData.getDeclaredField("mVersion")
            fieldVersion.setAccessible(true)
            val objectVersion: Any = fieldVersion.get(this)
            //set wrapper's version
            //set wrapper's version
            fieldLastVersion.set(objectWrapper, objectVersion)


        }


    }

}