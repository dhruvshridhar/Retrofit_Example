package in.blogspot.tecnopandit.retrofit_example;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.blogspot.tecnopandit.retrofit_example.databinding.RecyclerviewrowBinding;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private static ArrayList<Users> users;

    public MyAdapter(Context context, ArrayList<Users> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerviewrowBinding recyclerviewrowBinding= DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),R.layout.recyclerviewrow,parent,false
        );
        MyViewHolder myViewHolder=new MyViewHolder(recyclerviewrowBinding);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Users u =users.get(position);
        holder.recyclerviewrowBinding.setUser(u);
        holder.recyclerviewrowBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private RecyclerviewrowBinding recyclerviewrowBinding;

        public MyViewHolder(@NonNull RecyclerviewrowBinding itemView) {
            super(itemView.getRoot());
            recyclerviewrowBinding=itemView;
            recyclerviewrowBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String d =users.get(getAdapterPosition()).getName();
                    Intent intent=new Intent(recyclerviewrowBinding.getRoot().getContext(),UserDetails.class);
                    intent.putExtra("selected",d);
                    recyclerviewrowBinding.getRoot().getContext().startActivity(intent);
                    //Toast.makeText(recyclerviewrowBinding.getRoot().getContext(),d,Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
