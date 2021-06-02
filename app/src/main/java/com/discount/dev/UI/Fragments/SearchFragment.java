package com.discount.dev.UI.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.discount.dev.Adapter;
import com.discount.dev.R;
import com.discount.dev.Retrofit.JsonApi;
import com.discount.dev.Retrofit.Models.Partners;
import com.discount.dev.Retrofit.NetworkService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    private Adapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        JsonApi catApi = NetworkService.getService();
        Call<List<Partners>> partners = catApi.getPartners();
        ArrayList<Partners> partnersArrayList = new ArrayList<>();
        RecyclerView rvPartners = rootView.findViewById(R.id.partner_list);
        partners.enqueue(new Callback<List<Partners>>() {
            @Override
            public void onResponse(Call<List<Partners>> call, Response<List<Partners>> response) {
                List<Partners> partner = response.body();
                partnersArrayList.addAll(partner);
                adapter = new Adapter(partnersArrayList);
                rvPartners.setAdapter(adapter);
                rvPartners.setLayoutManager(new LinearLayoutManager(getContext()));
                search(rootView);
            }

            @Override
            public void onFailure(Call<List<Partners>> call, Throwable t) {
                Log.d("response ", String.valueOf(t));
            }
        });

        return rootView;
    }

    private void search(View view) {
        ((SearchView) view.findViewById(R.id.search)).setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}