package com.example.webwerks1.myapp.tvseries;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.webwerks1.myapp.Constants;
import com.example.webwerks1.myapp.R;

import java.util.List;

/**
 * Created by webwerks1 on 29/6/16.
 */
public class SeasonAdapter extends ArrayAdapter<Season> {

    Context mContext;
    LayoutInflater layoutInflater;
    List<Season> seasonList;

    public SeasonAdapter(Context context, List<Season> objects) {
        super(context, R.layout.tv_seasons_single_row, objects);

        mContext=context;
        layoutInflater=LayoutInflater.from(mContext);
        seasonList=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SeasonViewHolder holder;
        if(convertView==null){
            convertView = layoutInflater.inflate(R.layout.tv_seasons_single_row,parent,false);
            holder = new SeasonViewHolder();
            holder.imageView= (ImageView) convertView.findViewById(R.id.imgSeasonSingleRow);
            holder.txtseasonNameSingleRow=(TextView)convertView.findViewById(R.id.txtseasonNameSingleRow);
            holder.txtEpisodeNumber=(TextView)convertView.findViewById(R.id.txtEpisodeNumber);
            holder.txtAirDateSingleRow=(TextView)convertView.findViewById(R.id.txtAirDateSingleRow);
            convertView.setTag(holder);
        }
        else {
            holder = (SeasonViewHolder) convertView.getTag();
        }

        Glide.with(mContext).load(Constants.POSTER_BASE_URL+seasonList.get(position).getPoster_path()).into(holder.imageView);
        holder.txtAirDateSingleRow.setText(seasonList.get(position).getAir_date()+"");
        holder.txtEpisodeNumber.setText(seasonList.get(position).getEpisode_count()+"");
        holder.txtseasonNameSingleRow.setText(seasonList.get(position).getSeason_number()+"");
        return convertView;
    }

    @Override
    public int getCount() {
        return seasonList.size();
    }

    class SeasonViewHolder{
        ImageView imageView;
        TextView txtseasonNameSingleRow,txtAirDateSingleRow,txtEpisodeNumber;
    }
}
