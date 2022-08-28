package com.shaim.shaimjson;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecyclerAdapter extends RecyclerView.Adapter
        <MyRecyclerAdapter.ViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<ContactModel> contactModels;
    public MyRecyclerAdapter(Context context, ArrayList<ContactModel> contactModels){
        this.context=context;
        this.contactModels=contactModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.from(context).inflate(R.layout.list_data,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      // Picasso.get().load(contactModels.get(position).getImgUrl()).into(holder.imgU);
        holder.txtName.setText(contactModels.get(position).getName());
        holder.txtEmail.setText(contactModels.get(position).getEmail());
        holder.txtStreet.setText(contactModels.get(position).getStreet());
    }


    @Override
    public int getItemCount() {
        return contactModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtName,txtEmail,txtStreet;
        ImageView imgU;

        public ViewHolder (View itemView){

            super(itemView);
            imgU=itemView.findViewById(R.id.img);
            txtName=itemView.findViewById(R.id.txtName);
            txtEmail=itemView.findViewById(R.id.txtEmail);
            txtStreet=itemView.findViewById(R.id.txtStreet);
        }

    }
}
