package Adapters;


import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import BBDD.Clases.Tareas;
import viewHolder.TareaViewHolder;

public class TareaAdapter extends ListAdapter<Tareas, TareaViewHolder> {



        public TareaAdapter (@NonNull DiffUtil.ItemCallback<Tareas> diffCallback){
                super(diffCallback);
        }


        @NonNull
        @Override
        public TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return TareaViewHolder.create(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull TareaViewHolder holder, int position) {
                Tareas current = getItem(position);
                holder.bind(current);
        }

        public static class tareaDiff extends DiffUtil.ItemCallback<Tareas>{

                @Override
                public boolean areItemsTheSame(@NonNull Tareas oldItem, @NonNull Tareas newItem) {
                        return oldItem == newItem;
                }

                @Override
                public boolean areContentsTheSame(@NonNull Tareas oldItem, @NonNull Tareas newItem) {
                        return oldItem.getID() == (newItem.getID());
                }
        }


}
