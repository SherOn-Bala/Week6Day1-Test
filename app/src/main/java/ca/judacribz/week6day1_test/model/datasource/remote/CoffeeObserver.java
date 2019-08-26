package ca.judacribz.week6day1_test.model.datasource.remote;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import ca.judacribz.week6day1_test.model.Coffee;
import ca.judacribz.week6day1_test.model.events.CoffeeEvent;
import ca.judacribz.week6day1_test.model.events.CoffeesEvent;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class CoffeeObserver implements Observer<Coffee> {

    private static final String TAG = CoffeeObserver.class.getSimpleName();

    private Coffee coffee;

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: ", e);
    }

    @Override
    public void onComplete() {

        EventBus.getDefault().post(new CoffeeEvent(coffee));
    }
}
