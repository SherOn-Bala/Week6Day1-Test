package ca.judacribz.week6day1_test.model.datasource.remote

import android.util.Log
import ca.judacribz.week6day1_test.model.Coffee
import ca.judacribz.week6day1_test.model.events.CoffeesEvent
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.greenrobot.eventbus.EventBus

class CoffeesObserver : Observer<List<Coffee>> {
    private var coffees: List<Coffee>? = null

    override fun onSubscribe(d: Disposable) {
        Log.d(TAG, "onSubscribe: ")
    }

    override fun onNext(coffees: List<Coffee>) {
        Log.d(TAG, "onNext: ")
        this.coffees = coffees
    }

    override fun onError(e: Throwable) {
        Log.e(TAG, "onError: ", e)
    }

    override fun onComplete() {
        Log.d(TAG, "onComplete: ")
        EventBus.getDefault().post(coffees?.let { CoffeesEvent(it) })
    }

    companion object {
        private val TAG = CoffeesObserver::class.java.simpleName
    }
}
