package com.example.sameermandahretechbulls;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.security.AccessControlContext;
import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> implements Filterable {

    Context  mainActivity;
    List<Search> movies;
    private List<Search>filteredData = null;
    private ItemFilter mFilter = new ItemFilter();

    public MovieAdapter(Context mainActivity, List<Search> movies) {
        this.mainActivity = mainActivity;
        this.movies = movies;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txt_title.setText(movies.get(position).getTitle());
        holder.txt_year.setText(movies.get(position).getYear());
        holder.txt_type.setText(movies.get(position).getType());
        Picasso.with(mainActivity).load(movies.get(position).getPoster()).into(holder.iv_poster);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_poster;
        TextView txt_title,txt_year,txt_type;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_poster = itemView.findViewById(R.id.iv_poster);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_year = itemView.findViewById(R.id.txt_year);
            txt_type = itemView.findViewById(R.id.txt_type);
        }
    }
    public Filter getFilter() {
        return mFilter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
//            Search search = new Search();

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<Search> list = movies;

            int count = list.size();
            final ArrayList<Search> nlist = new ArrayList<Search>(count);

            Search filterableString ;


            for (int i = 0; i < count; i++) {
                filterableString = list.get(i);
                if (filterableString.getTitle().toLowerCase().contains(filterString)) {
                    nlist.add(filterableString);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredData = (ArrayList<Search>) results.values;
            notifyDataSetChanged();
        }

    }
}
