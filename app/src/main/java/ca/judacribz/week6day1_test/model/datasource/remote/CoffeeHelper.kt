package ca.judacribz.week6day1_test.model.datasource.remote

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object CoffeeHelper {

    private val BASE_URL = "https://demo6983184.mockable.io/"

    private val coffeeInstance: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    val obsService: ObservableCoffeeService = coffeeInstance.create(
        ObservableCoffeeService::class.java
    )
}
