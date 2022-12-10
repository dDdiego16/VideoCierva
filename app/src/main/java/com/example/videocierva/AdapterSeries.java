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
import java.util.ArrayList;
import java.util.List;

public class AdapterSeries extends RecyclerView.Adapter<AdapterSeries.ViewHolder> {

    private Context mCtx;
    private List<Serie> seriesList;
    private ArrayList<Serie> seriesFiltradas;

    public AdapterSeries(Context mCtx, List<Serie> seriesList) {
        this.mCtx = mCtx;
        this.seriesList = seriesList;
        seriesFiltradas = new ArrayList<>();
        seriesFiltradas.addAll(seriesList);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTitulo, textAge, txtTemporada, textCateg, textDisp;
        ImageView fotoSerie;

        public ViewHolder(View view) {
            super(view);
            textTitulo = (TextView) view.findViewById(R.id.txtNombreSerie);
            textAge = (TextView) view.findViewById(R.id.txtAñoSerie);
            txtTemporada = (TextView) view.findViewById(R.id.txtTemporadaSerie);
            textCateg = (TextView) view.findViewById(R.id.txtCategoriaSerie);
            textDisp = (TextView) view.findViewById(R.id.txtDisponibleSerie);
            fotoSerie = (ImageView) view.findViewById(R.id.imagenSerie);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.series_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Serie serie = seriesList.get(position);
        Glide.with(mCtx)
                .load(serie.getFoto())
                .into(viewHolder.fotoSerie);
        viewHolder.textTitulo.setText(serie.getTitulo());
        viewHolder.textAge.setText("("+String.valueOf(serie.getAño())+")");
        viewHolder.txtTemporada.setText("Temporada "+String.valueOf(serie.getTemporada()));
        viewHolder.textCateg.setText(serie.getCategoria());
        if (serie.getDisponible().equals("0")) {
            viewHolder.textDisp.setText("Disponible");
            viewHolder.textDisp.setTextColor(Color.parseColor("#008000"));
        } else {
            viewHolder.textDisp.setText("No Disponible");
            viewHolder.textDisp.setTextColor(Color.parseColor("#FF0000"));
        }
    }

    public void filtrado(String txtBuscar) {

        if (txtBuscar.length() == 0) {
            seriesList.clear();
            seriesList.addAll(seriesFiltradas);
        } else {
            seriesList.clear();
            for (Serie c : seriesFiltradas) {
                if (c.getTitulo().toLowerCase().contains(txtBuscar.toLowerCase())) {
                    seriesList.add(c);
                }
            }
        }
        notifyDataSetChanged();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return seriesList.size();
    }
}