package ca.judacribz.week6day1_test.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import ca.judacribz.week6day1_test.adapters.CoffeeAdapter
import ca.judacribz.week6day1_test.model.Coffee
import ca.judacribz.week6day1_test.model.datasource.remote.CoffeeHelper
import ca.judacribz.week6day1_test.model.datasource.remote.CoffeesObserver
import ca.judacribz.week6day1_test.model.events.CoffeesEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainActivity : AppCompatActivity(), CoffeeAdapter.ItemClickListener {

    var adapter: CoffeeAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ca.judacribz.week6day1_test.R.layout.activity_main)

        val layoutManager = LinearLayoutManager(this)
        rvCoffees.layoutManager = layoutManager
        rvCoffees.addItemDecoration(
            DividerItemDecoration(
                rvCoffees.context,
                layoutManager.orientation
            )
        )


        getCoffeeData()
        srlRefresh.setOnRefreshListener {
            getCoffeeData()
        }
    }

    private fun getCoffeeData() {
        CoffeeHelper.getObsService().coffees
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(CoffeesObserver())
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
    fun getCoffees(event: CoffeesEvent) {
        if (adapter == null) {
            adapter = CoffeeAdapter(event.coffees, this)
            rvCoffees.adapter = adapter
        } else {
            adapter!!.setData(event.coffees)
            srlRefresh.isRefreshing = false
        }
    }

    override fun onItemClicked(coffee: Coffee?) {
        val intent = Intent(this, CoffeeActivity::class.java)
        intent.putExtra(EXTRA_COFFEE_ID, coffee?.id)
        startActivity(intent)
    }

    companion object {
        const val EXTRA_COFFEE_ID: String = "ca.judacribz.week6day1_test.activities.EXTRA_COFFEE_ID"
    }
}
