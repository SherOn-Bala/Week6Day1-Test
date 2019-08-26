package ca.judacribz.week6day1_test.model.datasource.remote;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CoffeeHelper {

    private static final String BASE_URL = "https://demo6983184.mockable.io/";

    public static Retrofit getCoffeeInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static CoffeeService getService() {
        return getCoffeeInstance().create(CoffeeService.class);
    }

    public static ObservableCoffeeService getObsService() {
        return getCoffeeInstance().create(ObservableCoffeeService.class);
    }

}
