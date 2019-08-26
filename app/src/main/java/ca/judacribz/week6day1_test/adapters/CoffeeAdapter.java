package ca.judacribz.week6day1_test.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import ca.judacribz.week6day1_test.R;
import ca.judacribz.week6day1_test.model.Coffee;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.CoffeeHolder> {
    private List<Coffee> coffees;
    private Context context;
    private ItemClickListener itemClickListener;

    public interface ItemClickListener {
        void onItemClicked(Coffee coffee);
    }

    public CoffeeAdapter(List<Coffee> coffees, Context context) {
        this.coffees = coffees;
        this.context = context;
        this.itemClickListener = (ItemClickListener) context;
    }

    @NonNull
    @Override
    public CoffeeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CoffeeHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_coffee,
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull CoffeeHolder holder, int position) {
        holder.bindViews(coffees.get(position));
    }

    @Override
    public int getItemCount() {
        return coffees.size();
    }

    public void setData(List<Coffee> coffees) {
        this.coffees = coffees;
        notifyDataSetChanged();
    }

    class CoffeeHolder extends RecyclerView.ViewHolder {
        TextView
                tvTitle,
                tvParagraph;
        ImageView ivCoffee;
        String url;

        CoffeeHolder(@NonNull final View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvParagraph = itemView.findViewById(R.id.tvParagraph);
            ivCoffee = itemView.findViewById(R.id.ivCoffee);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClicked(coffees.get(getAdapterPosition()));
                }
            });
        }

        void bindViews(Coffee coffee) {
            tvTitle.setText(coffee.getName());
            tvParagraph.setText(coffee.getDesc());

            if (!(url = coffee.getImageUrl()).isEmpty()) {
                Glide
                        .with(context)
                        .load(url)
                        .into(ivCoffee);
            } else {
                ivCoffee.setVisibility(View.GONE);
            }
        }
    }
}
