package com.example.volleypokemonmy.Adaptadores;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.volleypokemonmy.Modelos.Pokemon;
import com.example.volleypokemonmy.R;
import com.example.volleypokemonmy.Resultado;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {

    private List<Pokemon> ListaPokemon;

    public PokemonAdapter(List<Pokemon> listaPokemon) {
        ListaPokemon = listaPokemon;
    }

    @NonNull
    @Override
    public PokemonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View Vi = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(Vi);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonAdapter.ViewHolder holder, int position) {
        Pokemon PK = ListaPokemon.get(position);
        holder.llenar(PK);
    }

    @Override
    public int getItemCount() {
        return ListaPokemon.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        String name2;
        String url;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textNombre);
            itemView.setOnClickListener(this);

        }

        public void llenar(Pokemon pk) {
            name.setText(pk.getName());
            this.name2=pk.getName();
            this.url=pk.getUrl();
        }

        @Override
        public void onClick(View view) {
            Intent I = new Intent(view.getContext(), Resultado.class);
            I.putExtra("name", this.name2.toString());
            I.putExtra("url", this.url.toString());
            view.getContext().startActivity(I);
        }
    }
}
