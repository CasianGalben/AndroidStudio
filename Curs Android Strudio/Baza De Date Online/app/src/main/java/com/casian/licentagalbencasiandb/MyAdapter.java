package com.casian.licentagalbencasiandb;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context mContext;
     List<User> list;
     private static ClickListener mClickListener;

    public MyAdapter(Context context,List<User> list){
        this.mContext = context;
        this.list=list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = list.get(position);
        holder.firstName.setText(user.getFirstname());
        holder.lastName.setText(user.getLastname());
        holder.age.setText(user.getAge());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        TextView firstName, lastName, age;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            firstName= itemView.findViewById(R.id.tvfirstName);
            lastName= itemView.findViewById(R.id.tvlastName);
            age= itemView.findViewById(R.id.tvAge);


        }

        @Override
        public void onClick(View view) {
            mClickListener.onItemClick(getAdapterPosition(),view);
        }

        @Override
        public boolean onLongClick(View view) {
            mClickListener.onItemLongClick(getAdapterPosition(),view);
            return false;
        }
    }

    public void setOnItemClickListener(ClickListener clickListener){
        MyAdapter.mClickListener = clickListener;
    }

    public interface ClickListener{
        void onItemClick(int position,View v);
        void onItemLongClick(int position,View v);
    }

}
