package ca.judacribz.week6day1_test.model.datasource.remote

import android.util.Log
import ca.judacribz.week6day1_test.model.Coffee
import ca.judacribz.week6day1_test.model.events.CoffeeEvent
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.greenrobot.eventbus.EventBus

class CoffeeObserver : Observer<Coffee> {
    private var coffee: Coffee? = null

    override fun onSubscribe(d: Disposable) {
        Log.d(TAG, "onSubscribe: ")
    }

    override fun onNext(coffee: Coffee) {
        Log.d(TAG, "onNext: ")
        this.coffee = coffee
    }

    override fun onError(e: Throwable) {
        Log.e(TAG, "onError: ", e)
    }

    override fun onComplete() {
        Log.d(TAG, "onComplete: ")
        EventBus.getDefault().post(coffee?.let { CoffeeEvent(it) })
    }

    companion object {
        private val TAG = CoffeeObserver::class.java.simpleName
    }
}
