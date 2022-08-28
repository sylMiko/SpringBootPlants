package com.client.playersfront.view;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.client.playersfront.model.Model;
import com.client.playersfront.R;

import java.util.List;

public class PlayersView extends RecyclerView.Adapter<PlayersView.ModelHolder> {

    private List<Model> list;
    private ItemClickListener listener;

    public PlayersView(List<Model> list, ItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ModelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewitem, parent, false);
        return new ModelHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelHolder holder, @SuppressLint("RecyclerView") int position) {
        Model model = list.get(position);
        holder.firstName.setText(model.getFirstName());
        holder.surname.setText(model.getSecondName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                listener.onItemClick(list.get(position));
            }
        });
    }

    public interface ItemClickListener {
        void onItemClick(Model model);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ModelHolder extends RecyclerView.ViewHolder {

        TextView firstName, surname;

        public ModelHolder(@NonNull View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.item_name);
            surname = itemView.findViewById(R.id.item_surname);
        }
    }
}
