package ca.judacribz.week6day1_test.model.datasource.remote;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import ca.judacribz.week6day1_test.model.Coffee;
import ca.judacribz.week6day1_test.model.events.CoffeesEvent;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class CoffeesObserver implements Observer<List<Coffee>> {

    private static final String TAG = CoffeesObserver.class.getSimpleName();

    private List<Coffee> coffees;

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(List<Coffee> coffees) {
        this.coffees = coffees;
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {
        EventBus.getDefault().post(new CoffeesEvent(coffees));
    }
}
