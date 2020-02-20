package com.example.jimmy.leaguecalculator;

/**
 * Created by Jimmy on 2016-11-09.
 *
 */

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

class MatchHistoryListItem {
    private String listName;
    private String listWins;
    private String listLP;
    private boolean listStreak;
    private boolean listVeteran;

    public String getListName() {
        return listName;
    }

    public String getListWins() {
        return listWins;
    }

    public String getListLP() {
        return listLP;
    }

    public boolean getListStreak(){
        return listStreak;
    }

    public boolean getListVeteran() {
        return listVeteran;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public void setListWins(String listWins) {
        this.listWins = listWins;
    }

    public void setListLP(String listLP) {
        this.listLP = listLP;
    }

    public void setListStreak(boolean listStreak){
        this.listStreak = listStreak;
    }

    public void setListVeteran(boolean listVeteran) {
        this.listVeteran = listVeteran;
    }
}

public class MatchHistoryListAdapter extends BaseAdapter {
    private ArrayList<MatchHistoryListItem> listData;
    private LayoutInflater layoutInflater;

    public MatchHistoryListAdapter(Context context, ArrayList<MatchHistoryListItem> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_divisions, null);
            holder = new ViewHolder();
            holder.listNameView = (TextView) convertView.findViewById(R.id.ListName);
            holder.listWinsView = (TextView) convertView.findViewById(R.id.ListWins);
            holder.listLPView = (TextView) convertView.findViewById(R.id.ListLP);
            holder.listVeteranView = (ImageView) convertView.findViewById(R.id.ListVeteranView);
            holder.listHotStreakView = (ImageView) convertView.findViewById(R.id.ListHotStreakView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String summonerName = listData.get(position).getListName();
        String summonerWins = listData.get(position).getListWins();
        String summonerLP = listData.get(position).getListLP();
        holder.listNameView.setText(summonerName);
        holder.listWinsView.setText(String.format("%3s",summonerWins));
        holder.listLPView.setText(String.format("%3s",summonerLP));
        holder.listNameView.setTypeface(null, Typeface.BOLD);
        holder.listWinsView.setTypeface(null, Typeface.BOLD);
        holder.listLPView.setTypeface(null, Typeface.BOLD);
        if (summonerName.endsWith("pqnvsk")){
            holder.listNameView.setText(summonerName.substring(0, summonerName.length() - 6));
            holder.listNameView.setTypeface(null, Typeface.BOLD_ITALIC);
            holder.listWinsView.setTypeface(null, Typeface.BOLD_ITALIC);
            holder.listLPView.setTypeface(null, Typeface.BOLD_ITALIC);
        }
        if (listData.get(position).getListVeteran()){
            holder.listVeteranView.setImageResource(R.drawable.veteran);
        } else {
            holder.listVeteranView.setImageResource(R.drawable.transparent);
        }
        if (listData.get(position).getListStreak()){
            Context context = holder.listHotStreakView.getContext();
            int id = context.getResources().getIdentifier("aatrox", "drawable", context.getPackageName());
            holder.listHotStreakView.setImageResource(id);
            holder.listHotStreakView.setImageResource(R.drawable.hotstreak);
        } else {
            holder.listHotStreakView.setImageResource(R.drawable.transparent);
        }

        return convertView;
    }

    static class ViewHolder {
        TextView listNameView;
        TextView listWinsView;
        TextView listLPView;
        ImageView listVeteranView;
        ImageView listHotStreakView;
    }
}

