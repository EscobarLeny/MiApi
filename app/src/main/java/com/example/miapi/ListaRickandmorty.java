package com.example.miapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListaRickandmorty extends RecyclerView.Adapter<ListaRickandmortyAdapter.ViewHolder>{
    private RecyclerView recyclerView;
    private ListaRickandmorty listaRickandmorty;
    private ArrayList<Rickandmorty> dataset;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;

        public ViewHolder (@NonNull View itemView){
            super(itemView);
            name=itemView.findViewById(R.id.name);
        }
    }

    public ListaRickandmortyAdapter(Context context){
        this.context = context;
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row,parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public int getItemCount(){
        return  dataset.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Rickandmorty r=dtaset.get(position);
        holder.name.setText(r.getName());

        String url = "https://raw.githubusercontent.com";

        Glide.with(context)
                .load(url)
                .error(R.drawable.error)
                .placeholder(R.drawable.placeholder_);
    }

    public  void add(ArrayList<Rickandmorty> listaRickandmorty){
        dataset.add((listaRickandmorty);
        notifyDataSetChanged();

    }
}
