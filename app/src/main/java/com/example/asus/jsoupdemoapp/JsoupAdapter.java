package com.example.asus.jsoupdemoapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class JsoupAdapter extends RecyclerView.Adapter<JsoupAdapter.JsoupAdapterHolder> {

    Context context;
    ArrayList<Repo> al;

    public JsoupAdapter(Context context, ArrayList al) {
        this.context = context;
        this.al = al;
    }

    public JsoupAdapterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list, viewGroup, false);

        return new JsoupAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JsoupAdapterHolder jsoupAdapterHolder, int i) {
        Repo repo = al.get(i);
        jsoupAdapterHolder.repoName.setText(repo.getRepoName());
        String con = repo.getRepoContent();
        jsoupAdapterHolder.repoContent.setText(con);
        jsoupAdapterHolder.language.setText("Language: "+repo.getLanguage());
        //jsoupAdapterHolder.star.setText("Star: "+repo.getStar());
    }

    @Override
    public int getItemCount() {

        return al.size();
    }

    class JsoupAdapterHolder extends RecyclerView.ViewHolder {

        TextView repoName, repoContent, language, star;
        public JsoupAdapterHolder(@NonNull View itemView) {
            super(itemView);

            repoName = itemView.findViewById(R.id.repoName);
            repoContent = itemView.findViewById(R.id.repoContent);
            language = itemView.findViewById(R.id.language);
            star = itemView.findViewById(R.id.star);
        }
    }
}
