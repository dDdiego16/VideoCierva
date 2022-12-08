package com.example.videocierva;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterPelis extends RecyclerView.Adapter<AdapterPelis.ViewHolder> {

    private Context mCtx;
    private List<Pelicula> pelisList;

    public AdapterPelis(Context mCtx, List<Pelicula> pelisList) {
        this.mCtx = mCtx;
        this.pelisList = pelisList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTitulo, textAño, textCateg, textDisp;
        ImageView fotoPeli;

        public ViewHolder(View view) {
            super(view);
            textTitulo = (TextView) view.findViewById(R.id.txtNombrePeli);
            textAño = (TextView) view.findViewById(R.id.txtAñoPeli);
            textCateg = (TextView) view.findViewById(R.id.txtCategoriaPeli);
            textDisp = (TextView) view.findViewById(R.id.txtDisponiblePeli);
            fotoPeli = (ImageView) view.findViewById(R.id.imagenPeli);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.pelis_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Pelicula peli = pelisList.get(position);
        Glide.with(mCtx)
                .load(peli.getFoto())
                .into(viewHolder.fotoPeli);
        viewHolder.textTitulo.setText(peli.getTitulo());
        viewHolder.textAño.setText(String.valueOf(peli.getAño()));
        viewHolder.textCateg.setText(peli.getCategoria());
        if(peli.getDisponible().equals("0")) {
            viewHolder.textDisp.setText("Disponible");
            viewHolder.textDisp.setTextColor(Color.parseColor("#008000"));
        }else{
            viewHolder.textDisp.setText("No Disponible");
            viewHolder.textDisp.setTextColor(Color.parseColor("#FF0000"));
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return pelisList.size();
    }
}
