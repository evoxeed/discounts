package com.discount.dev;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.discount.dev.Retrofit.Models.Partners;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements Filterable {

    private List<Partners> mPartners;
    private List<Partners> mFilteredPartners;
    private static final int LIMIT = 200;
    private RecyclerView recyclerView;

    public Adapter(ArrayList<Partners> partnersArrayList) {
        this.mPartners = partnersArrayList;
        this.mFilteredPartners = partnersArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View partView = inflater.inflate(R.layout.item_partner, parent, false);
        ViewHolder holder = new ViewHolder(partView);

        partView.findViewById(R.id.more).setOnClickListener(v -> {
            final int position = holder.getAdapterPosition();
            Partners partner = mFilteredPartners.get(position);
            holder.description.setText(partner.getInfo());
            holder.more.setVisibility(View.GONE);
        });

        // Return a new holder instance
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Partners partner = mFilteredPartners.get(position);
        // Set item views based on your views and data model
        TextView nameTextView = holder.name;
        nameTextView.setText(partner.getName());

        StringBuilder allAddresses = new StringBuilder();
        for (Partners.Address address : partner.getAddresses()) {
            TextView addressTextView = holder.addresses;
            TextView phontTextView = holder.phone;
//            Log.e("adress", String.valueOf(address));
            allAddresses.append(address.getStreet()).append(" ").append(address.getHouse()).append(address.getStreet() == null ? "" : "\n");
            addressTextView.setText(allAddresses);
            phontTextView.setText(address.getContactPhone());
        }

        TextView infoTextView = holder.description;
        infoTextView.setText(formattedInfo(partner.getInfo()));

        Picasso.get().load(partner.getLogo()).into(holder.logo);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public int getItemCount() {
        return mFilteredPartners.size();
    }

    private String formattedInfo(String source) {
        if (source.length() >= LIMIT) {
            return source.substring(0, LIMIT - 1) + "...";
        }
        return source;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private final Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String search = constraint.toString();
            if (TextUtils.isEmpty(search)) {
                mFilteredPartners = new ArrayList<>(mPartners);
            } else {
                List<Partners> resultList = new ArrayList<>();
                for (Partners item : mPartners) {
                    if (item.getName().toLowerCase().contains(search.toLowerCase())) {
                        resultList.add(item);
                    }
                }
                mFilteredPartners = resultList;
            }

            FilterResults results = new FilterResults();
            results.values = mFilteredPartners;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            new Handler().postDelayed(() -> {
                if (recyclerView != null) {
                    recyclerView.getRecycledViewPool().clear();
                }
                notifyDataSetChanged();
            }, 100);
        }
    };

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView addresses;
        public TextView phone;
        public TextView description;
        public ImageView logo;
        public TextView more;

        public ViewHolder(View itemView) {
            super(itemView);
            addresses = itemView.findViewById(R.id.partner_addresses);
            phone = itemView.findViewById(R.id.partner_contact_phone);
            name = itemView.findViewById(R.id.partner_name);
            description = itemView.findViewById(R.id.partner_info);
            logo = itemView.findViewById(R.id.logo);
            more = itemView.findViewById(R.id.more);
        }
    }
}