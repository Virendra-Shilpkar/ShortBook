package com.example.shortbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shortbook.Geter_Seter.ContectList;

import java.util.ArrayList;

public class ContectAdapter extends RecyclerView.Adapter<ContectAdapter.ViewHolder>{
    public ContectAdapter(ArrayList<ContectList> list, Context context) {
        this.list = list;
        this.context = context;
    }

    private ArrayList<ContectList> list=new ArrayList<ContectList>();
    private Context context;

    @NonNull
    @Override
    public ContectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.contect_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContectAdapter.ViewHolder holder, int position) {
        String name=list.get(position).getName();
       String number= list.get(position).getNumber();
       String id=list.get(position).getId();
        holder.name.setText(name);
        holder.number.setText(number);
        holder.sort_name.setText(name.substring(0, 1));
        Toast.makeText(context, id+"", Toast.LENGTH_SHORT).show();
        holder.item_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Contect :"+name.toString()+number.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,number,sort_name;
        RelativeLayout item_list;
        public ViewHolder(View itemview) {
            super(itemview);
            name=itemview.findViewById(R.id.name);
            number=itemview.findViewById(R.id.number);
            sort_name=itemview.findViewById(R.id.sort_name);
            item_list=itemview.findViewById(R.id.list_item);


        }
    }
}
