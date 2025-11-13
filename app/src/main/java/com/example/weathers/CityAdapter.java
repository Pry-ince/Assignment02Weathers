package com.example.weathers;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.weathers.databinding.ItemCityBinding;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.VH> {

    public interface OnItemClickListener {
        void onItemClick(City city);
    }

    private final List<City> cities;
    private final OnItemClickListener listener;

    public CityAdapter(List<City> cities, OnItemClickListener listener) {
        this.cities = cities;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCityBinding binding = ItemCityBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new VH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        City city = cities.get(position);
        holder.binding.tvCityName.setText(city.getName());
        holder.binding.getRoot().setOnClickListener(v -> listener.onItemClick(city));
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        final ItemCityBinding binding;

        VH(ItemCityBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
