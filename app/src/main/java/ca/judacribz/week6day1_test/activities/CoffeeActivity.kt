package ca.judacribz.week6day1_test.activities

import android.os.Bundle
import android.view.View.GONE
import androidx.appcompat.app.AppCompatActivity
import ca.judacribz.week6day1_test.R
import ca.judacribz.week6day1_test.activities.MainActivity.Companion.EXTRA_COFFEE_ID
import ca.judacribz.week6day1_test.model.datasource.remote.CoffeeHelper
import ca.judacribz.week6day1_test.model.datasource.remote.CoffeeObserver
import ca.judacribz.week6day1_test.model.events.CoffeeEvent
import com.bumptech.glide.Glide
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_coffee.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.text.SimpleDateFormat
import java.util.*

class CoffeeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coffee)
        val coffeeId: String? = intent.getStringExtra(EXTRA_COFFEE_ID)

        CoffeeHelper.getObsService().getCoffee(coffeeId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(CoffeeObserver())
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun getCoffee(event: CoffeeEvent) {
        val coffee = event.coffee

        tvTitle.text = coffee.name
        tvParagraph.text = coffee.desc


        val url = coffee.imageUrl
        if (!url.isEmpty()) {
            Glide.with(this).load(url).into(ivCoffee)
        } else {
            ivCoffee.visibility = GONE
        }
        val str = coffee.lastUpdatedAt
        val date = getDate(str.substring(0, str.indexOf(" ")))
        tvUpdated.text = "Updated $date"
    }

    private fun getDate(lastUpdatedAt: String): String {
        val date = inDateFormat.parse(lastUpdatedAt)
        return outDateFormat.format(date!!)
    }

    companion object {
        private val inDateFormat = SimpleDateFormat("yyyy-M-d", Locale.US)
        private val outDateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.US)
    }
}
