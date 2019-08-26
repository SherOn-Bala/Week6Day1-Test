package ca.judacribz.week6day1_test.model.datasource.remote;

import java.util.List;

import ca.judacribz.week6day1_test.model.Coffee;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ObservableCoffeeService {
    @GET("coffees")
    Observable<List<Coffee>> getCoffees();

    @GET("coffees/{coffeeId}")
    Observable<Coffee> getCoffee(@Path("coffeeId") String coffeeId);
}
