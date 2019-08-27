package ca.judacribz.week6day1_test.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ca.judacribz.week6day1_test.R
import ca.judacribz.week6day1_test.model.Coffee
import com.bumptech.glide.Glide

class CoffeeAdapter(private var coffees: List<Coffee>?, private val context: Context) :
    RecyclerView.Adapter<CoffeeAdapter.CoffeeHolder>() {
    private val itemClickListener: ItemClickListener

    interface ItemClickListener {
        fun onItemClicked(coffee: Coffee)
    }

    init {
        this.itemClickListener = context as ItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeHolder = CoffeeHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_coffee,
            parent,
            false
        )
    )


    override fun onBindViewHolder(holder: CoffeeHolder, position: Int) =
        holder.bindViews(coffees!![position])


    override fun getItemCount(): Int = coffees!!.size


    fun setData(coffees: List<Coffee>) {
        this.coffees = coffees
        notifyDataSetChanged()
    }

    inner class CoffeeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView
        var tvParagraph: TextView
        var ivCoffee: ImageView
        var url: String? = null

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvParagraph = itemView.findViewById(R.id.tvParagraph)
            ivCoffee = itemView.findViewById(R.id.ivCoffee)

            itemView.setOnClickListener { itemClickListener.onItemClicked(coffees!![adapterPosition]) }
        }

        fun bindViews(coffee: Coffee) {
            tvTitle.text = coffee.name
            tvParagraph.text = coffee.desc

            val url = coffee.imageUrl
            if (url != null) {
                if (url.isNotBlank()) {
                    Glide
                        .with(context)
                        .load(url)
                        .into(ivCoffee)
                } else {
                    ivCoffee.visibility = View.GONE
                }
            }
        }
    }
}
