package com.borisovskiy.collectionsmaps.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.borisovskiy.collectionsmaps.R;
import com.borisovskiy.collectionsmaps.dto.CalculationDTO;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewAdapterMaps extends RecyclerView.Adapter<RecyclerViewAdapterMaps.CalculationViewHolder> {

    private List<CalculationDTO> calculationDTOItems;

    @NonNull
    @Override
    public CalculationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recview_item, parent, false);
        return new CalculationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CalculationViewHolder holder, int position) {
        CalculationDTO item = calculationDTOItems.get(position);
        holder.bind(item);
        holder.setProgressBarVisibility(item);
    }

    public void showProgressBar() {
        for (int i = 0; i < calculationDTOItems.size(); i++) {
            calculationDTOItems.get(i).setLoading(true);
        }
        notifyDataSetChanged();
    }

    public void hideProgressBar(String tag) {
        for (int i = 0; i < calculationDTOItems.size(); i++) {
            if (tag.equals(calculationDTOItems.get(i).getTag())) {
                calculationDTOItems.get(i).setLoading(false);
                notifyItemChanged(i);
                break;
            }
        }
    }

    public void hideProgressBar() {
        for (int i = 0; i < calculationDTOItems.size(); i++) {
            calculationDTOItems.get(i).setLoading(false);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (calculationDTOItems != null)
            return calculationDTOItems.size();
        else return 0;
    }

    public void updateItem(String tag, long time) {
        for (int i = 0; i < calculationDTOItems.size(); i++) {
            if (tag.equals(calculationDTOItems.get(i).getTag())) {
                calculationDTOItems.get(i).setTime(time);
                notifyItemChanged(i);
                break;
            }
        }
    }

    public void setItems(List<CalculationDTO> calculationDTOItems) {
        this.calculationDTOItems = calculationDTOItems;
        notifyDataSetChanged();
    }

    class CalculationViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.recview_item_tv)
        TextView recyclerViewItemTextView;
        @BindView(R.id.progressbar)
        ProgressBar progressBar;

        private CalculationViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(CalculationDTO item) {
            Context context = recyclerViewItemTextView.getContext();
            recyclerViewItemTextView.setText(context.getString(item.getLabelResourceId(), item.getTimeText()));
        }

        private void setProgressBarVisibility(CalculationDTO item) {
            if (item.getIsLoading()) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.INVISIBLE);
            }
        }
    }
}