package ca.judacribz.week6day1_test.model.datasource.remote

import ca.judacribz.week6day1_test.model.Coffee
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ObservableCoffeeService {
    @get: GET("coffees")
    val coffee : Observable<List<Coffee>>

    @GET("coffees/{coffeeId}")
    fun getCoffee(@Path("coffeeId") coffeeId: String): Observable<Coffee>
}
