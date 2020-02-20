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
import android.widget.TextView;
import java.util.ArrayList;

class ChampionAttributesListItem {
    private String championAttribute;

    public String getChampionAttribute() {
        return championAttribute;
    }

    public void setchampionAttribute(String championAttribute) {
        this.championAttribute = championAttribute;
    }

    private String championAttributeRank;

    public String getChampionAttributeRank() {
        return championAttributeRank;
    }

    public void setchampionAttributeRank(String championAttributeRank) {
        this.championAttributeRank = championAttributeRank;
    }

}

public class ChampionInfoListAdapter extends BaseAdapter {
    private ArrayList<ChampionAttributesListItem> championAttributesListItem;
    private LayoutInflater layoutInflater;

    public ChampionInfoListAdapter(Context context, ArrayList<ChampionAttributesListItem> championAttributesListItem) {
        this.championAttributesListItem = championAttributesListItem;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return championAttributesListItem.size();
    }

    @Override
    public Object getItem(int position) {
        return championAttributesListItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.champion_info, null);
            holder = new ViewHolder();
            holder.championAttributeListView = (TextView) convertView.findViewById(R.id.championAttributeListText);
            holder.championAttributeRankListText = (TextView) convertView.findViewById(R.id.championAttributeRankListText);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String attributes = championAttributesListItem.get(position).getChampionAttribute();
        String attributesRanks = championAttributesListItem.get(position).getChampionAttributeRank();
        holder.championAttributeListView.setText(attributes);
        holder.championAttributeListView.setTypeface(null, Typeface.BOLD);
        holder.championAttributeListView.setAllCaps(false);
        holder.championAttributeRankListText.setText(attributesRanks);
        holder.championAttributeRankListText.setTypeface(null, Typeface.BOLD);
        holder.championAttributeRankListText.setAllCaps(false);
        return convertView;
    }

    static class ViewHolder {
        TextView championAttributeListView;
        TextView championAttributeRankListText;
    }
}

